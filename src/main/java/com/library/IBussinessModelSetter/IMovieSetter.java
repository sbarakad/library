package com.library.IBussinessModelSetter;

import java.sql.ResultSet;

import com.library.businessModels.Movie;

public interface IMovieSetter {
	public Movie mapMovie(ResultSet resultSet);
}
