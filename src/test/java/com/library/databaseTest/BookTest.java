package com.library.databaseTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import com.library.IDAO.IBookDAO;
import com.library.businessModels.Book;
import com.library.DAO.BookDAO;


public class BookTest {

	IBookDAO bookDAO = new BookDAO();
	
	@Test
	public void getBookByIDTest() {
		Book book = bookDAO.getBookByID(100001);
		assertEquals(269,book.getIsbn());
	}
	
	@Test
	public void getBookByISBNTest() {
		Book book = bookDAO.getBookByISBN(269);
		assertEquals("Ptrick Ruthfus", book.getAuthor());
	}
	
	@Test
	public void getBookByTitle() {
		List<Book> books = bookDAO.getBookByTitle("The girl");
		assertEquals("The girl who played with fire",books.get(0).getTitle());
	}
	
	@Test
	public void getBookByAuthorTest() {
		List<Book> books = bookDAO.getBooksByAuthor("Ptrick Ruthfus");
		assertEquals("Ptrick Ruthfus",books.get(0).getAuthor());
	}
	
	@Test
	public void getBookByPublisherTest() {
		List<Book> books = bookDAO.getBookByPublisher("Shrivastav Pubilication");
		assertEquals("Shrivastav Pubilication",books.get(0).getPublisher());
	}
	
	@Test
	public void getBookByDescriptionTest() {
		List<Book> books = bookDAO.getBookByDescription("Horroe");
		assertEquals("Based on horroe movie",books.get(0).getDescription());
	}
	
	@Test
	public void getBookTitleTest() {
		String bookTitle = bookDAO.getBookTitle(100001);
		assertEquals("The girl who played with fire",bookTitle);
	}
	
	@Test
	public void deleteBookTest()
	{
		Boolean isBookDeleted = bookDAO.deleteBookByID(100002);
		assertEquals(true,isBookDeleted);
	}

	@Test
	public void createBookTest()
	{
		Book book = new Book();
		book.setAuthor("Albert Camus");
		book.setAvailability(1);
		book.setCategory("Philoshophy");
		book.setDescription("How sysphus loves rolling the bolder to the mountain top");
		book.setIsbn(265);
		book.setPublisher("Paris publication");
		book.setTitle("Myth of sysphus");
		Boolean isBookCreated = bookDAO.createBook(book);
		assertEquals(true,isBookCreated);
	}
	
	@Test
	public void updateBookTest() {
		Book book = new Book();
		book.setAuthor("Albert Camus");
		book.setAvailability(1);
		book.setCategory("Philoshophy");
		book.setDescription("How sysphus loves rolling the bolder to the mountain top");
		book.setIsbn(265);
		book.setItemID(1002);
		book.setPublisher("Swedan publication");
		book.setTitle("Myth of sysphus");
		Boolean isBookUpdated = bookDAO.updateBook(book);
		assertEquals(true,isBookUpdated);
	}
}
