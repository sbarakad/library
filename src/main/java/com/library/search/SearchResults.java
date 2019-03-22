package com.library.search;

import java.util.LinkedList;
import java.util.List;

import com.library.businessModels.Book;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;

public class SearchResults {
	private LinkedList<Book> bookSearchResults = new LinkedList<Book>();
	private LinkedList<Music> musicSearchResults = new LinkedList<Music>();
	private LinkedList<Movie> movieSearchResults = new LinkedList<Movie>();

	public LinkedList<Book> getBookSearchResults() {
		return bookSearchResults;
	}
	public void setBookSearchResults(LinkedList<Book> bookSearchResults) {
		this.bookSearchResults = bookSearchResults;
	}
	public List<Music> getMusicSearchResults() {
		return musicSearchResults;
	}
	public void setMusicSearchResults(LinkedList<Music> musicSearchResults) {
		this.musicSearchResults = musicSearchResults;
	}
	public List<Movie> getMovieSearchResults() {
		return movieSearchResults;
	}
	public void setMovieSearchResults(LinkedList<Movie> movieSearchResults) {
		this.movieSearchResults = movieSearchResults;
	}

}
