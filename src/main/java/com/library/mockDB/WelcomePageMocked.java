package com.library.mockDB;

import java.util.List;

import com.library.DAOFactory.DAOFactory;
import com.library.businessModels.Movie;

public class WelcomePageMocked {
	private DAOFactory factory = null;

	public WelcomePageMocked() {
		factory = new DAOFactory();
		initiateMock();
	}

	public Movie initiateMock() {
		Movie abc = new Movie();
		abc = factory.makeMovieDAO().getMovieById(2001);
		return abc;
	}

}
