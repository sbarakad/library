package com.library.IDAO;

import java.util.LinkedList;
import java.util.List;

import com.library.businessModels.Book;
import com.library.businessModels.Movie;
import com.library.search.IMovieSearchRequestDetails;

public interface IMovieDAO {
	public Movie getMovieById(int itemID);
	public List<Movie> getMoviesByCategory(String category);
	public Boolean createMovie(Movie movie);
	public Boolean updateMovie(Movie movie);
	public Boolean deleteMovie(Movie movie);
	public LinkedList<Movie> getMoviesBySearchTerms(IMovieSearchRequestDetails searchRequestDetails); 
	
	
}
