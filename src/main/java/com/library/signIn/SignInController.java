package com.library.signIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import com.library.businessModels.IUserBasicInfo;
import com.library.businessModels.User;
import com.library.businessModels.UserBasicInfo;


public class SignInController {

	private IUserBasicInfo userBasicInfo;
	private User user;
	private HttpSession httpSession;
	private List<Entry<String, String>> listofValidationErrors;

	public SignInController(User user, HttpSession httpSession) {
		this.user = user;
		this.userBasicInfo = new UserBasicInfo();
		this.userBasicInfo.setEmail(user.getEmail());
		this.userBasicInfo.setPwd(user.getPassword());
		this.httpSession = httpSession;
	}

	public boolean connectDB() {

		// TODO: call DB
		return true;
	}

	public ArrayList<Entry<String, String>> authenticateSignIn() {
		listofValidationErrors = AuthenticateUserForms.instance().signInUserData(userBasicInfo);
		
		// If true connect DB as list has no validations to check.
		if (listofValidationErrors.size() == 0) {
			
			AuthenticatedUsers.instance().addAuthenticatedUser(httpSession, userBasicInfo.getEmail());
//			connectDB(); // will be worked upon.
		}
		return (ArrayList<Entry<String, String>>) listofValidationErrors;
	}
	public String isAdmin() {
		if(userBasicInfo.getEmail().equals(Authentication.isAdmin)){
			return "AdminMainPage";
		}
		return "MainPage";
	}
}
