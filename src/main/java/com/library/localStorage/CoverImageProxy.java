package com.library.localStorage;

import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import com.library.DAO.CoverDAO;
import com.library.businessModels.Cover;
import com.library.businessModels.LibraryItem;
import com.library.search.SearchResults;

public class CoverImageProxy implements ICoverImageProxy{
	
	final String separator = File.separator;
	
	private HashMap<Integer, List<String>> itemIdToCoverUrListForMainPage = new HashMap<Integer, List<String>>();
	private HashMap<String, HashMap<String, LinkedList<String>>> sessionIdToRequestedPageResults = 
															new HashMap<String, HashMap<String, LinkedList<String>>>();
									
	private CoverImageProxy() {}
	private static CoverImageProxy coverImageProxy = null;
	
	public static CoverImageProxy instance() {
		if(null == coverImageProxy) {
			coverImageProxy = new CoverImageProxy();
		}
		return coverImageProxy;
	}

	@Override
	public void loadCoverImages(SearchResults searchResults, String requestedPageNumber, HttpSession httpSession) {
		
		String sessionResultsDir = separator +"dynamicContent" + separator +"searchResults" + separator + 
																		httpSession.getId() + separator;
		
		List<LibraryItem> items = new LinkedList<>();
		items.addAll(searchResults.getBookSearchResults());
		items.addAll(searchResults.getMusicSearchResults());
		items.addAll(searchResults.getMovieSearchResults());
		
		if(items.isEmpty()) {
			return;
		}
		
		HashMap<String, LinkedList<String>> resultsForSession = sessionIdToRequestedPageResults.get(httpSession.getId());
		if(null == resultsForSession) {
			resultsForSession = new HashMap<String, LinkedList<String>>();
			sessionIdToRequestedPageResults.put(httpSession.getId(), resultsForSession);
		} else {
			if(resultsForSession.containsKey(requestedPageNumber)) {
				return;
			}
		}
		
		if(!resultsForSession.containsKey(requestedPageNumber)) {
			resultsForSession.put(requestedPageNumber, new LinkedList<String>());
		}
		
		String urlForRequestedPageNumber = sessionResultsDir + requestedPageNumber;
		String baseUrl = System.getProperty("user.dir") + urlForRequestedPageNumber;
		
		CoverDAO coverDao = new CoverDAO();
		byte [] bytes;

		// source: https://www.journaldev.com/2573/spring-mvc-file-upload-example-single-multiple-files
		File dir = new File(baseUrl);
		if (!dir.exists())
			dir.mkdirs();
		
		for(LibraryItem item : items) {
			Cover cover = coverDao.getCoverByID(item.getItemID());
			if(null != cover) {
				try {
					bytes = cover.getCoverBlob().getBytes(1, (int) cover.getCoverBlob().length());
					String imageName = item.getItemID() + "." + cover.getFileExtension();
					String url = baseUrl + separator + imageName;
					resultsForSession.get(requestedPageNumber).add(url);
					File file = new File(url);
				
					BufferedOutputStream stream;
					stream = new BufferedOutputStream(new FileOutputStream(file));
					stream.write(bytes);
					stream.close();
					item.setCoverImageUrl(urlForRequestedPageNumber + separator + imageName);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void deleteCoverImages(HttpSession httpSession) {
		String sessionResultsDir = separator +"dynamicContent" + separator +"searchResults" + separator + 
																			httpSession.getId() + separator;
		try {
			sessionIdToRequestedPageResults.remove(httpSession.getId());
			FileUtils.deleteDirectory(new File(System.getProperty("user.dir") + sessionResultsDir));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
