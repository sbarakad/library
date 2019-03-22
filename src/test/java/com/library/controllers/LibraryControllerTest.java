package com.library.controllers;

import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Enumeration;
import java.util.LinkedList;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.library.businessModels.Book;
import com.library.businessModels.Movie;
import com.library.demo.LibraryApplication;
import com.library.search.IDBSearchController;
import com.library.search.SearchRequestDetails;
import com.library.search.SearchResults;
import com.library.signIn.AuthenticatedUsers;


@RunWith(SpringRunner.class)
@WebMvcTest
// try to remove the annotation below, run this TEST and observe error. Look for solution here:
// https://stackoverflow.com/questions/43515279/error-unable-to-find-springbootconfiguration-when-doing-webmvctest-for-spring
@ContextConfiguration(classes={LibraryApplication.class})
public class LibraryControllerTest {

    @Inject
    private MockMvc mockMvc;

    @MockBean
    private IDBSearchController dataBaseMock;
    
    private MockHttpSession mockHttpSession;
    
    @Before
    public void setUp() {
        Mockito.reset(dataBaseMock);
        mockHttpSession = new MockHttpSession();
        AuthenticatedUsers.instance().addAuthenticatedUser(mockHttpSession, "asd@mail.com");
     }

    @SuppressWarnings("unchecked")
	@Test
    public void executeSearch() throws Exception {
    	
    	SearchResults searchResult = new SearchResults();
    	SearchRequestDetails searchRqstDetails = new SearchRequestDetails();
    	searchRqstDetails.setSearchTerms("Jack London");
    	searchRqstDetails.setExtendedSearch(true);
    	
		Book book1 = new Book();
		book1.setIsbn(111);
		book1.setItemID(1111);
		book1.setTitle("The Star Rover");
		book1.setAuthor("Jack London");
		book1.setCategory("Novel");
		book1.setDescription("Good Story!");
		book1.setPublisher("Good One Publisher");
		book1.setAvailability(10);
		
		Book book2 = new Book();
		book2.setIsbn(222);
		book2.setItemID(2222);
		book2.setTitle("The Scarlet Plague");
		book2.setAuthor("Jack London");
		book2.setCategory("Post-Apocalyptic Fiction");
		book2.setDescription("Good Story Too!");
		book2.setPublisher("Old Good Publisher");
		book2.setAvailability(11);
    	
    	LinkedList<Book> booksFoundInSearch = new LinkedList<Book>();
    	booksFoundInSearch.add(book1);
    	booksFoundInSearch.add(book2);
    	searchResult.setBookSearchResults(booksFoundInSearch);
    	
		Movie movie = new Movie();
	
		movie.setItemID(3333);
		movie.setTitle("Doctor Zhivago");
		movie.setDirector("David Lean");
		movie.setCategory("Drama");
		movie.setDescription("Cannot describe - you have to see it!");
		movie.setAvailability(12);
    	
    	LinkedList<Movie> moviesFoundInSearch = new LinkedList<Movie>();
    	moviesFoundInSearch.add(movie);
    	searchResult.setMovieSearchResults(moviesFoundInSearch);
    	
    	searchResult.setMusicSearchResults(null);
        
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/search");
        // "searchRequestDetails" is a bean --> method in the Controller should expect argument of type SearchRequestDetails with whatever name.
        request.flashAttr("searchRequestDetails", searchRqstDetails); 
        request.session(mockHttpSession);
        
        when(dataBaseMock.search(searchRqstDetails, mockHttpSession)).thenReturn(searchResult);
       
        
		this.mockMvc.perform(request)
			.andExpect(status().isOk())
			.andExpect(view().name("SearchResultsPage"))

			.andExpect(model().attribute("searchResults", hasProperty("bookSearchResults", hasSize(2))))
			.andExpect(model().attribute("searchResults",
				allOf(hasProperty("bookSearchResults", 
						hasItems(
								allOf(
										hasProperty("description", is("Good Story!")), 
										hasProperty("author", is("Jack London")),
										hasProperty("isbn", is(111)),
										hasProperty("publisher", is("Good One Publisher")),
										hasProperty("category", is("Novel")),
										hasProperty("title", is("The Star Rover")),
										hasProperty("itemID", is(1111)),
										hasProperty("availability", is(10))
									  )
								,

								allOf(
										hasProperty("description", is("Good Story Too!")), 
										hasProperty("author", is("Jack London")),
										hasProperty("isbn", is(222)),
										hasProperty("publisher", is("Old Good Publisher")),
										hasProperty("category", is("Post-Apocalyptic Fiction")),
										hasProperty("title", is("The Scarlet Plague")),
										hasProperty("itemID", is(2222)),
										hasProperty("availability", is(11))
									  )
								)))))
			
			.andExpect(model().attribute("searchResults", hasProperty("movieSearchResults", hasSize(1))))
			.andExpect(model().attribute("searchResults",
				allOf(hasProperty("movieSearchResults", 
						hasItem(
								allOf(
										hasProperty("director", is("David Lean")),
										hasProperty("description", is("Cannot describe - you have to see it!")),
										hasProperty("category", is("Drama")),
										hasProperty("title", is("Doctor Zhivago")),
										hasProperty("itemID", is(3333)),
										hasProperty("availability", is(12))
									  )
							   )))))
			
			.andExpect(model().attribute("searchResults", hasProperty("musicSearchResults", is(nullValue()))))
			;
    }
    
    
	@Test
    public void browseAdvancedSearchPage() throws Exception {
        
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/advancedSearch");
        request.session(mockHttpSession);
        
		this.mockMvc.perform(request)
			.andExpect(status().isOk())
			.andExpect(view().name("AdvancedSearchPage"))
			;
    }   
    
}

