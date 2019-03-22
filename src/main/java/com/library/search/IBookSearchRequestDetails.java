package com.library.search;

public interface IBookSearchRequestDetails {
	public String getSearchTerms();
	public boolean isSearchBookTitle();
	public boolean isSearchBookAuthor();
	public boolean isSearchBookCategory();
	public boolean isSearchBookPublisher();
	public boolean isSearchBookDescription();
	public boolean isSearchBookISBN();
}
