package com.library.IBussinessModelSetter;

import java.sql.ResultSet;
import java.util.List;

import com.library.businessModels.Book;

public interface IBookSetter {
	public Book mapBook(ResultSet resultSet);
	List<Integer> getItemIDFromBook(List<Book> books);
}
