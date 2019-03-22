package com.library.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.library.additem.AddBookController;
import com.library.additem.AddMovieController;
import com.library.additem.AddMusicController;
import com.library.businessModels.Book;
import com.library.businessModels.Movie;
import com.library.businessModels.Music;
import com.library.businessModels.User;
import com.library.email.EmailUtility;
import com.library.mockDB.WelcomePageMocked;
import com.library.search.DBSeachControllerBean;
import com.library.search.IDBSearchController;
import com.library.search.SearchRequestDetails;
import com.library.search.SearchResults;
import com.library.signIn.AuthenticatedUsers;
import com.library.signIn.SignInController;

@ComponentScan(basePackages = { "com.library.model" }, basePackageClasses = DBSeachControllerBean.class)
@Controller
public class LibraryController implements WebMvcConfigurer {
	private List<Map.Entry<String, String>> list = null;
	@Inject
	private IDBSearchController dbSearchController;

	public LibraryController() {
		
	}
	
	@PostMapping("/signUp")
	public String processSignUpForm(ModelMap model, User user) {
		try {
			ILibraryFactory factory = new LibraryControllerFactory();

			LibraryFactorySingleton.instance().build(factory);
			list = LibraryFactorySingleton.instance().getFactory().signUp(user).authenticateSignUp();
			for (int i = 0; i < list.size(); i++) {
				model.addAttribute(list.get(i).getKey(), list.get(i).getValue());
			}
			// model object has by default two values; anytime it gets more than that
			// signifies a validation violation
			if (model.size() > 2) {
				return "SignUpForm";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Results";

	}

	@GetMapping("/signUp")
	public String getSignUpForm(User user) {
		return "SignUpForm";
	}

	@GetMapping("/advancedSearch")
	public String getAdvancedSearchPage(HttpSession httpSession, ModelMap model) {
		AuthenticatedUsers.instance().addAuthenticatedUser(httpSession, "removeMeFromTheController@mail.com");
		if (AuthenticatedUsers.instance().userIsAuthenticated(httpSession)) {
			SearchRequestDetails searchRequestDetails = new SearchRequestDetails();
			searchRequestDetails.setExtendedSearch(true);
			model.addAttribute("searchRequestDetails", searchRequestDetails);
			model.addAttribute("userEmail", AuthenticatedUsers.instance().getUserEmail(httpSession));

			return "AdvancedSearchPage";
		}
		return "NoAccessToNonAuthenticated";
	}

	@PostMapping("/search")

	public String getSearchResults(HttpSession httpSession, ModelMap model, SearchRequestDetails srchRequestDetails) {
		SearchResults searchResults = dbSearchController.search(srchRequestDetails, httpSession);
		model.addAttribute("searchRequestDetails", srchRequestDetails);
		model.addAttribute("searchResults", searchResults);
		model.addAttribute("userEmail", AuthenticatedUsers.instance().getUserEmail(httpSession));
		return "SearchResultsPage";
	}

	@GetMapping("/")
	public String getItemDetailsById() {
		return "ItemDetailsPage";
	}

//	@RequestMapping("/")
//	String entry() {
//		return "Home.jsp";
//	} 	

	@GetMapping("/signIn")
	public String getSignInForm(User user) {
		return "SignInForm";
	}

	@PostMapping("/signIn")
	public String processSignInForm(HttpSession httpSession, ModelMap model, User user) {
		try {
			ILibraryFactory factory = new LibraryControllerFactory();
			LibraryFactorySingleton.instance().build(factory);
			SignInController signIn = LibraryFactorySingleton.instance().getFactory().signIn(user, httpSession);
			list = signIn.authenticateSignIn();
			for (int index = 0; index < list.size(); index++) {
				model.addAttribute(list.get(index).getKey(), list.get(index).getValue());
			}
			// model object has by default two values and anytime it gets more than that
			// signifies a validation violation
			if (model.size() > 2) {
				return "SignInForm";
			}
			return signIn.isAdmin();
		} catch (Exception e) {
			e.printStackTrace();
			return ""; // Something went wrong page.
		}
	}

	@GetMapping("/addBook")

	public String responseBookForm(ModelMap model, Book book) {

		model.addAttribute("book", new Book());
		model.addAttribute("movie", new Movie());
		model.addAttribute("music", new Music());

		return "AddItemPage";
	}

	@PostMapping("/addBook")
	public String addBookToDatabase(ModelMap model, Book book) {

		AddBookController addBookController = new AddBookController();
		addBookController.addBookRecordInDatabase(book);

		return "ResponseBook";
	}

	@PostMapping("/addMovie")
	public String addMovieToDatabase(ModelMap model, Movie movie) {

		AddMovieController addMovieController = new AddMovieController();
		addMovieController.addMovieRecordInDatabase(movie);

		return "ResponseMovie";
	}

	@PostMapping("/addMusic")
	public String addMusicToDatabase(ModelMap model, Music music) {

		AddMusicController addMusicController = new AddMusicController();
		addMusicController.addMusicRecordInDatabase(music);

		return "ResponseMusic";
	}

	@GetMapping("/welcome")
	public String welcomeBody(Movie movie) {

		return "Welcome";
	}

	@PostMapping("/welcome")
	public String welcomeProcess(ModelMap model, Movie movie) {
		model.addAttribute("movie", new WelcomePageMocked().initiateMock());

		return "Welcome";
	}

	@GetMapping("/logOut")
	public String processLogOut(HttpSession httpSession, ModelMap model, User user) {
		if (AuthenticatedUsers.instance().userIsAuthenticated(httpSession)) {
			// make DBSearchController listener of AuthenticatedUsers and
			// returnItem/AddItem/deleteItem/updateItem
			AuthenticatedUsers.instance().removeAuthenticatedUser(httpSession);
		}
		return "HomePage";
	}

	@RequestMapping(value = "/sendemail")
	public String sendEmail() throws AddressException, MessagingException, IOException {
		EmailUtility.sendmail("", "", "", "");
		return "Email sent successfully";
	}
}
