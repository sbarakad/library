package com.library.localStorage;

import javax.servlet.http.HttpSession;

import com.library.search.SearchResults;

public interface ICoverImageProxy {
	void deleteCoverImages(HttpSession httpSession);
	void loadCoverImages(SearchResults searchResults, String requestedPageNumber, HttpSession httpSession);
}
