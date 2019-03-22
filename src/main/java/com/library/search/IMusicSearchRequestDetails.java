package com.library.search;

public interface IMusicSearchRequestDetails {
	public String getSearchTerms();
	public boolean isSearchMusicAlbumName();
	public boolean isSearchMusicArtist();
	public boolean isSearchMusicRecordLabel();
}
