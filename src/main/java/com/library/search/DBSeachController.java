package com.library.search;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.library.DAO.BookDAO;
import com.library.DAO.MovieDAO;
import com.library.DAO.MusicDAO;
import com.library.businessModels.Book;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;
import com.library.localStorage.CoverImageProxy;
// This class is a Java bean --> singleton by default.
public class DBSeachController implements IDBSearchController {

	
	private final int NUM_OF_SEARCH_RESULTS_IN_DISPLAY_ROW = 10; // should move to the configuration file
	private Map<String, SearchRequestsAndResults> sessionIdToSearchRequestsAndResults = new HashMap<>();
	
	protected class SearchRequestsAndResults {
		SearchRequestDetails searchRequestDetails;
		SearchResults searchResults;
		
		public SearchRequestsAndResults(SearchRequestDetails searchRequestDetails, SearchResults searchResults) {
			this.searchRequestDetails = searchRequestDetails;
			this.searchResults = searchResults;
		}
	}

	
	@Override
	public SearchResults search(SearchRequestDetails searchRequestDetails, HttpSession httpSession) {
		SearchRequestsAndResults searchRequestsAndResults = null;
		String sessionId = httpSession.getId();
		
		boolean isNotFirstSearchForSessionID = sessionIdToSearchRequestsAndResults.containsKey(httpSession.getId());
		boolean isSameSearchCriteriaButDifferentResultsPage = false;
		if(isNotFirstSearchForSessionID) {
			isSameSearchCriteriaButDifferentResultsPage = 
					sessionIdToSearchRequestsAndResults.get(sessionId).searchRequestDetails.onlyRequestedPageDiffers(searchRequestDetails);
		}
		
		if(isNotFirstSearchForSessionID) {
			if(isSameSearchCriteriaButDifferentResultsPage) {
				searchRequestsAndResults = sessionIdToSearchRequestsAndResults.get(httpSession.getId());
				searchRequestsAndResults.searchRequestDetails.setRequestedResultsPageNumber(
						searchRequestDetails.getRequestedResultsPageNumber());
			} else {
				CoverImageProxy.instance().deleteCoverImages(httpSession);
				sessionIdToSearchRequestsAndResults.remove(httpSession.getId());
				searchRequestsAndResults = executeSearchInDb(searchRequestDetails,httpSession);
			}
		} else {
			searchRequestsAndResults = executeSearchInDb(searchRequestDetails, httpSession);
		}
		
		SearchResults resultSet = getResultSetForRequestedPageNumber(searchRequestsAndResults);
		int requestedPageNumber = searchRequestsAndResults.searchRequestDetails.getRequestedResultsPageNumber();
		CoverImageProxy.instance().loadCoverImages(resultSet, Integer.toString(requestedPageNumber), httpSession);
		return resultSet;
	}

	protected SearchRequestsAndResults executeSearchInDb(SearchRequestDetails searchRequestDetails, HttpSession httpSession) {
		SearchResults searchResults = searchInDb(searchRequestDetails);
		SearchRequestsAndResults searchRequestsAndResults = new SearchRequestsAndResults(searchRequestDetails, searchResults);
		sessionIdToSearchRequestsAndResults.put(httpSession.getId(), searchRequestsAndResults);
		return searchRequestsAndResults;
	}

	protected SearchResults searchInDb(SearchRequestDetails searchRequestDetails) {
		SearchResults searchResults = new SearchResults();
		if(searchRequestDetails.isExtendedSearch()) {
			if(searchRequestDetails.isSearchInBooks()) {
				searchResults.setBookSearchResults(new BookDAO().getBooksBySearchTerms(searchRequestDetails));
			}
			if(searchRequestDetails.isSearchInMusic()) {
				searchResults.setMusicSearchResults(new MusicDAO().getMusicBySearchTerms(searchRequestDetails));
			}
			if(searchRequestDetails.isSearchInMovies()) {
				searchResults.setMovieSearchResults(new MovieDAO().getMoviesBySearchTerms(searchRequestDetails));
			}
		} else {
			searchResults.setBookSearchResults(new BookDAO().getBooksBySearchTerms(searchRequestDetails));
			searchResults.setMusicSearchResults(new MusicDAO().getMusicBySearchTerms(searchRequestDetails));
			searchResults.setMovieSearchResults(new MovieDAO().getMoviesBySearchTerms(searchRequestDetails));
		}
		return searchResults;
	}
	
	protected SearchResults getResultSetForRequestedPageNumber(SearchRequestsAndResults searchRequestsAndResults) {
		int pageNumber = searchRequestsAndResults.searchRequestDetails.getRequestedResultsPageNumber();
		SearchResults resultsForPageNumber = new SearchResults();
		
		if(null != searchRequestsAndResults.searchResults.getBookSearchResults()) {
			Iterator<Book> iterator = searchRequestsAndResults.searchResults.getBookSearchResults().iterator();
			for(int i=0; i < (pageNumber -1)*NUM_OF_SEARCH_RESULTS_IN_DISPLAY_ROW && iterator.hasNext(); i++, iterator.next()) {}
			for(int i=0; i < NUM_OF_SEARCH_RESULTS_IN_DISPLAY_ROW && iterator.hasNext(); i++) {
				resultsForPageNumber.getBookSearchResults().add(iterator.next()); 
			}
		}
		
		if(null != searchRequestsAndResults.searchResults.getMusicSearchResults()) {
			Iterator<Music> iterator = searchRequestsAndResults.searchResults.getMusicSearchResults().iterator();
			for(int i=0; i < (pageNumber -1)*NUM_OF_SEARCH_RESULTS_IN_DISPLAY_ROW && iterator.hasNext(); i++, iterator.next()) {}
			for(int i=0; i < NUM_OF_SEARCH_RESULTS_IN_DISPLAY_ROW && iterator.hasNext(); i++) {
				resultsForPageNumber.getMusicSearchResults().add(iterator.next()); 
			}
		}
		
		if(null != searchRequestsAndResults.searchResults.getMovieSearchResults()) {
			Iterator<Movie> iterator = searchRequestsAndResults.searchResults.getMovieSearchResults().iterator();
			for(int i=0; i < (pageNumber -1)*NUM_OF_SEARCH_RESULTS_IN_DISPLAY_ROW && iterator.hasNext(); i++, iterator.next()) {}
			for(int i=0; i < NUM_OF_SEARCH_RESULTS_IN_DISPLAY_ROW && iterator.hasNext(); i++) {
				resultsForPageNumber.getMovieSearchResults().add(iterator.next()); 
			}
		}
				
		return resultsForPageNumber;
	}
}
