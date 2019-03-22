package com.library.databaseTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.library.DAO.MovieDAO;
import com.library.IDAO.IMovieDAO;
import com.library.businessModels.Movie;

public class MovieTest {

	IMovieDAO iMovieDAO = new MovieDAO();

	@Test
	public void getMovieByIdTest() {
		Movie movie = iMovieDAO.getMovieById(2001);
		assertEquals("Interstellar", movie.getTitle());
	}

	@Test
	public void getMoviesByCategoryTest() {
		List<Movie> listOfMoviesByCategory = iMovieDAO.getMoviesByCategory("Romance");
		assertEquals("Eternal Sunshine of the Spotless Mind", listOfMoviesByCategory.get(0).getTitle());
	}

	@Test
	public void getMoviesByDescriptionTest() {
		List<Movie> listOfMoviesByCategory = iMovieDAO.getMoviesByCategory("Romance");
		assertEquals("Eternal Sunshine of the Spotless Mind", listOfMoviesByCategory.get(0).getTitle());
	}

	@Test
	public void deleteMovieTest() {
		Movie movie = new Movie();
		movie.setItemID(2006);
		assertTrue(iMovieDAO.deleteMovie(movie));
	}
}
