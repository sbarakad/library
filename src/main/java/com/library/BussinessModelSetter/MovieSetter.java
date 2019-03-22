package com.library.BussinessModelSetter;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.library.IBussinessModelSetter.IMovieSetter;
import com.library.businessModels.Movie;

public class MovieSetter implements IMovieSetter {

	@Override
	public Movie mapMovie(ResultSet resultSet) {
		Movie movie = new Movie();
		try {
			movie.setCategory(resultSet.getString("Category"));
			movie.setDescription(resultSet.getString("Description"));
			movie.setDirector(resultSet.getString("Director"));
			movie.setAvailability(resultSet.getInt("Availability"));
			movie.setTitle(resultSet.getString("Title"));
			movie.setItemID(resultSet.getInt("Item_ID"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movie;
	}

}
