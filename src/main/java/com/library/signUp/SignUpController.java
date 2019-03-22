package com.library.signUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.library.businessModels.IUserBasicInfo;
import com.library.businessModels.IUserExtendedInfo;
import com.library.businessModels.User;
import com.library.businessModels.UserBasicInfo;
import com.library.businessModels.UserExtendedInfo;
import com.library.signIn.AuthenticateUserForms;

public class SignUpController {
	private List<Entry<String, String>> listofValidationErrors;
	private IUserBasicInfo userBasicInfo;
	private IUserExtendedInfo userExtendedInfo;
	private User user;

	public SignUpController(User user) {
		this.user = user;

		userExtendedInfo = new UserExtendedInfo();
		userBasicInfo = new UserBasicInfo();
		
		userBasicInfo.setEmail(user.getEmail());
		userBasicInfo.setPwd(user.getPassword());
		userExtendedInfo.setCPassword(user.getCpassword());
		userExtendedInfo.setFullname(user.getFullName());
		userExtendedInfo.setPhone(user.getPhoneNumber());
	}

	public ArrayList<Entry<String, String>> authenticateSignUp() {
		listofValidationErrors = AuthenticateUserForms.instance().signUpUserData(userBasicInfo, userExtendedInfo);
		// If true connect DB as list has no validations to check.
		if (listofValidationErrors.size() == 0) {
//			connectDB(); // will be worked upon.
		}
		return (ArrayList<Entry<String, String>>) listofValidationErrors;

	}

	private static void connectDB() {
		// TODO:DB calls

	}
}
