package com.library.BussinessModelSetter;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.library.IBussinessModelSetter.IBookSetter;
import com.library.businessModels.Book;;

public class BookSetter implements IBookSetter {
	
	public Book mapBook(ResultSet resultSet) 
	{
		try {
			Book book = new Book();
			book.setIsbn(resultSet.getInt("ISBN"));
			book.setItemID(resultSet.getInt("Item_ID"));
			book.setTitle(resultSet.getString("Title"));
			book.setAuthor(resultSet.getString("Author"));
			book.setCategory(resultSet.getString("Category"));
			book.setDescription(resultSet.getString("Description"));
			book.setPublisher(resultSet.getString("Publisher"));
			book.setAvailability(resultSet.getInt("Availability"));
			return book;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Integer> getItemIDFromBook(List<Book> books) {
		List<Integer> items = new ArrayList<Integer>();
		for(int i=0;i<books.size();i++)
		{
			items.add(books.get(i).getItemID());
		}
		return items;
	}
}
