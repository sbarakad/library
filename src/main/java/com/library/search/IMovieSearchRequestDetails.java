package com.library.search;

public interface IMovieSearchRequestDetails {
	public String getSearchTerms();
	public boolean isSearchMovieTitle();
	public boolean isSearchMovieDirector();
	public boolean isSearchMovieDescription();
}
