package com.library.search;


public class SearchRequestDetails implements IBookSearchRequestDetails, 
											 IMovieSearchRequestDetails, 
											 IMusicSearchRequestDetails {
	
	private String searchTerms = null;
	private int requestedResultsPageNumber = 1;
	
	private boolean extendedSearch = false;
	
	private boolean searchInBooks = false;
	private boolean searchBookTitle = false;
	private boolean searchBookAuthor = false;
	private boolean searchBookCategory = false;
	private boolean searchBookPublisher = false;
	private boolean searchBookDescription = false;
	private boolean searchBookISBN = false;
	
	private boolean searchInMusic = false;
	private boolean searchMusicAlbumName = false;
	private boolean searchMusicArtist = false;
	private boolean searchMusicRecordLabel = false;
	
	private boolean searchInMovies = false;
	private boolean searchMovieTitle = false;
	private boolean searchMovieDirector = false;
	private boolean searchMovieDescription = false;
	
	public boolean isExtendedSearch() {
		return extendedSearch;
	}
	public void setExtendedSearch(boolean extendedSearch) {
		this.extendedSearch = extendedSearch;
	}
	
	@Override
	public String getSearchTerms() {
		return searchTerms;
	}
	public void setSearchTerms(String searchTerms) {
		this.searchTerms = searchTerms;
	}
	public int getRequestedResultsPageNumber() {
		return requestedResultsPageNumber;
	}
	public void setRequestedResultsPageNumber(int requestedSearchResultsPageNumber) {
		this.requestedResultsPageNumber = requestedSearchResultsPageNumber;
	}
	public boolean isSearchInBooks() {
		return searchInBooks;
	}
	public void setSearchInBooks(boolean searchInBooks) {
		this.searchInBooks = searchInBooks;
	}
	@Override
	public boolean isSearchBookTitle() {
		return searchBookTitle;
	}
	public void setSearchBookTitle(boolean searchBookTitle) {
		this.searchBookTitle = searchBookTitle;
	}
	@Override
	public boolean isSearchBookAuthor() {
		return searchBookAuthor;
	}
	@Override
	public boolean isSearchBookCategory() {
		return searchBookCategory;
	}
	public void setSearchBookCategory(boolean searchBookCategory) {
		this.searchBookCategory = searchBookCategory;
	}
	public void setSearchBookAuthor(boolean searchBookAuthor) {
		this.searchBookAuthor = searchBookAuthor;
	}
	@Override
	public boolean isSearchBookPublisher() {
		return searchBookPublisher;
	}
	public void setSearchBookPublisher(boolean searchBookPublisher) {
		this.searchBookPublisher = searchBookPublisher;
	}
	@Override
	public boolean isSearchBookDescription() {
		return searchBookDescription;
	}
	public void setSearchBookDescription(boolean searchBookDescription) {
		this.searchBookDescription = searchBookDescription;
	}
	@Override
	public boolean isSearchBookISBN() {
		return searchBookISBN;
	}
	public void setSearchBookISBN(boolean searchBookISBN) {
		this.searchBookISBN = searchBookISBN;
	}
	public boolean isSearchInMusic() {
		return searchInMusic;
	}
	public void setSearchInMusic(boolean searchInMusic) {
		this.searchInMusic = searchInMusic;
	}
	@Override
	public boolean isSearchMusicAlbumName() {
		return searchMusicAlbumName;
	}
	public void setSearchMusicAlbumName(boolean searchMusicAlbumName) {
		this.searchMusicAlbumName = searchMusicAlbumName;
	}
	@Override
	public boolean isSearchMusicArtist() {
		return searchMusicArtist;
	}
	public void setSearchMusicArtist(boolean searchMusicArtist) {
		this.searchMusicArtist = searchMusicArtist;
	}
	@Override
	public boolean isSearchMusicRecordLabel() {
		return searchMusicRecordLabel;
	}
	public void setSearchMusicRecordLabel(boolean searchMusicRecordLabel) {
		this.searchMusicRecordLabel = searchMusicRecordLabel;
	}
	public boolean isSearchInMovies() {
		return searchInMovies;
	}
	public void setSearchInMovies(boolean searchInMovie) {
		this.searchInMovies = searchInMovie;
	}
	@Override
	public boolean isSearchMovieTitle() {
		return searchMovieTitle;
	}
	public void setSearchMovieTitle(boolean searchMovieTitle) {
		this.searchMovieTitle = searchMovieTitle;
	}
	@Override
	public boolean isSearchMovieDirector() {
		return searchMovieDirector;
	}
	public void setSearchMovieDirector(boolean searchMovieDirector) {
		this.searchMovieDirector = searchMovieDirector;
	}
	@Override
	public boolean isSearchMovieDescription() {
		return searchMovieDescription;
	}
	public void setSearchMovieDescription(boolean searchMovieDescription) {
		this.searchMovieDescription = searchMovieDescription;
	}
	
	public boolean onlyRequestedPageDiffers(SearchRequestDetails otherSearchRequestDetails) {
		if(this.searchTerms.equals(otherSearchRequestDetails.searchTerms) &&
			this.extendedSearch == otherSearchRequestDetails.extendedSearch &&
				
			this.searchInBooks == otherSearchRequestDetails.searchInBooks &&
			this.searchBookAuthor == otherSearchRequestDetails.searchBookAuthor &&
			this.searchBookPublisher == otherSearchRequestDetails.searchBookPublisher &&
			this.searchBookDescription == otherSearchRequestDetails.searchBookDescription &&
			this.searchBookISBN == otherSearchRequestDetails.searchBookISBN &&
				
			this.searchInMusic == otherSearchRequestDetails.searchInMusic &&
			this.searchMusicAlbumName == otherSearchRequestDetails.searchMusicAlbumName &&
			this.searchMusicArtist == otherSearchRequestDetails.searchMusicArtist &&
			this.searchMusicRecordLabel == otherSearchRequestDetails.searchMusicRecordLabel &&
				
			this.searchInMovies == otherSearchRequestDetails.searchInMovies &&
			this.searchMovieTitle == otherSearchRequestDetails.searchMovieTitle &&
			this.searchMovieDirector == otherSearchRequestDetails.searchMovieDirector &&
			this.searchMovieDescription == otherSearchRequestDetails.searchMovieDescription) {
			
			return true;
		}
		return false;
	}
}
