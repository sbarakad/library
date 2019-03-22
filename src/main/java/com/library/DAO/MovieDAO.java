package com.library.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.library.BussinessModelSetter.MovieSetter;
import com.library.IBussinessModelSetter.IMovieSetter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.library.IDAO.IMovieDAO;
import com.library.businessModels.Movie;
import com.library.dbConnection.DatabaseConnection;
import com.library.search.IMovieSearchRequestDetails;

public class MovieDAO implements IMovieDAO {

	private PreparedStatement preparedStatement;
	String query;
	Connection connection;
	IMovieSetter movieSetter = new MovieSetter();
	private static final Logger logger = LogManager.getLogger(MovieDAO.class);

	public MovieDAO() {

		try {
			DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionInstance();
			this.connection = databaseConnection.getConnection();
		} catch (Exception e) {

			logger.log(Level.ALL, "Unable to connect to database", e);
		}
	}

	@Override
	public Movie getMovieById(int itemID) {

		Movie movie = new Movie();
		query = "SELECT * from movie WHERE Item_ID = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				movie = movieSetter.mapMovie(resultSet);
			}
		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {

			logger.log(Level.ALL, "Movie not found for the specific Itemid", e);
		}
		return movie;
	}

	@Override
	public List<Movie> getMoviesByCategory(String category) {

		Movie movie = new Movie();
		query = "SELECT * from movie WHERE Category LIKE ?";
		List<Movie> moviesByCategory = new ArrayList<Movie>();

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%" + category + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				movie = new Movie();
				movie = movieSetter.mapMovie(resultSet);
				moviesByCategory.add(movie);
			}

		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Error fetching the list of movies for this category", e);
		}
		return moviesByCategory;
	}

	@Override
	public Boolean createMovie(Movie movie) {

		try {
			query = "INSERT INTO movie (Category,Title,Director,Description,Availability) VALUES (?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, movie.getCategory());
			preparedStatement.setString(2, movie.getTitle());
			preparedStatement.setString(3, movie.getDirector());
			preparedStatement.setString(4, movie.getDescription());
			preparedStatement.setInt(5, movie.getAvailability());
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not insert movie into database", e);
		}
		return false;
	}

	@Override
	public Boolean updateMovie(Movie movie) {

		try {
			query = "UPDATE movie SET Category=?,Title=?,Director=?,Description=?,Availability=? WHERE Item_ID=? ";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, movie.getCategory());
			preparedStatement.setString(2, movie.getTitle());
			preparedStatement.setString(3, movie.getDirector());
			preparedStatement.setString(4, movie.getDescription());
			preparedStatement.setInt(5, movie.getAvailability());
			preparedStatement.setInt(6, movie.getItemID());
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not update movie into database", e);
		}
		return false;
	}

	@Override
	public Boolean deleteMovie(Movie movie) {

		try {
			query = "DELETE from movie WHERE Item_ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, movie.getItemID());
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {

			logger.log(Level.ALL, "Check the SQL syntax", e);

		} catch (Exception e) {
			logger.log(Level.ALL, "Can not delete movie into database", e);
		}
		return false;
	}

	private void prepareSearchQuery(IMovieSearchRequestDetails requestDetails) {

		if (0 == requestDetails.getSearchTerms().length()) {
			// logger.log("ERROR: Search terms length is zero.");
		}

		query = "SELECT DISTINCT * FROM movie WHERE ";
		String[] searchterms = requestDetails.getSearchTerms().split("\\s");
		for (String term : searchterms) {
			if (requestDetails.isSearchMovieTitle()) {
				query += "Title like \"%" + term + "%\" or ";
			}
			if (requestDetails.isSearchMovieDirector()) {
				query += "Director like \"%" + term + "%\" or ";
			}
			if (requestDetails.isSearchMovieDescription()) {
				query += "Description like \"%" + term + "%\" or ";
			}
		}

		query = query.substring(0, query.length() - 4);
	}

	@Override
	public LinkedList<Movie> getMoviesBySearchTerms(IMovieSearchRequestDetails searchRequestDetails) {
		LinkedList<Movie> movies = new LinkedList<Movie>();
		Movie movie;
		prepareSearchQuery(searchRequestDetails);

		try {
			preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}

			while (resultSet.next()) {
				movie = movieSetter.mapMovie(resultSet);
				movie = movieSetter.mapMovie(resultSet);
				movies.add(movie);
			}

			return movies;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return movies;
	}
}
