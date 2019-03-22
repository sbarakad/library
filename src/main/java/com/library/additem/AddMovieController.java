package com.library.additem;

import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IDAO.IMovieDAO;
import com.library.businessModels.Movie;

public class AddMovieController {

	IDAOFactory factory;
	
	public AddMovieController() {

		factory = new DAOFactory();
	}

	public void addMovieRecordInDatabase(Movie movie) {
		IMovieDAO iMovieDAO = factory.makeMovieDAO();
		iMovieDAO.createMovie(movie);
	}

}
