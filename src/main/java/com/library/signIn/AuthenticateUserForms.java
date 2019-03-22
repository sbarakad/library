package com.library.signIn;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import com.library.businessModels.IUserBasicInfo;
import com.library.businessModels.IUserExtendedInfo;

public class AuthenticateUserForms extends Authentication {

	private static List<Entry<String, String>> listofValidationErrors = null;
	private static Map.Entry<String, String> entryMap = null;

	private static final String fullName = "fullName";
	private static final String password = "password";
	private static final String email = "email";
	private static final String cpassword = "cpassword";
	private static final String phoneNumber = "phoneNumber";

	private static AuthenticateUserForms instance = null;

	public static AuthenticateUserForms instance() {
		if (instance == null) {
			instance = new AuthenticateUserForms();
		}
		return instance;
	}

	public AuthenticateUserForms() {
		setErrorStringRules();
		setValidationRules();
	}

	// Till DB is integrated values are validated against some dummy values.
	// In next sprint i will add the functionality to check validation with XML
	// file. Also will create a const file for string values.
	public ArrayList<Map.Entry<String, String>> signUpUserData(IUserBasicInfo userBasicInfo,
			IUserExtendedInfo userExtendedInfo) {
		try {

			listofValidationErrors = new ArrayList<Map.Entry<String, String>>();
			listofValidationErrors.clear();

			if (userBasicInfo.getEmail().equals("")
					|| !Pattern.compile(emailRegex).matcher(userBasicInfo.getEmail()).find()) {
				entryMap = new AbstractMap.SimpleEntry<String, String>(email, emailErrorStatement);
				listofValidationErrors.add(entryMap);
			}
			if (userBasicInfo.getPwd().equals("")
					|| !Pattern.compile(passwordRegex).matcher(userBasicInfo.getPwd()).find()) 
			{
				entryMap = new AbstractMap.SimpleEntry<String, String>(password, passwordErrorStatement);
				listofValidationErrors.add(entryMap);
			}
			if (userExtendedInfo.getCPassword().equals("")
					|| !Pattern.compile(passwordRegex).matcher(userExtendedInfo.getCPassword()).find()) {

				entryMap = new AbstractMap.SimpleEntry<String, String>(cpassword, passwordErrorStatement);
				listofValidationErrors.add(entryMap);
			} else if (!userExtendedInfo.getCPassword().equals(userBasicInfo.getPwd())) {
				entryMap = new AbstractMap.SimpleEntry<String, String>(cpassword, cpasswordErrorStatement);
				listofValidationErrors.add(entryMap);
			}
			if (userExtendedInfo.getFullname().equals("")) {
				entryMap = new AbstractMap.SimpleEntry<String, String>(fullName, blankErrorStatement);
				listofValidationErrors.add(entryMap);
			}
			if (userExtendedInfo.getPhone().equals("") || userExtendedInfo.getPhone().length() != phoneCheck) {
				entryMap = new AbstractMap.SimpleEntry<String, String>(phoneNumber, phoneErrorStatement);
				listofValidationErrors.add(entryMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<Entry<String, String>>) listofValidationErrors;
	}

	public ArrayList<Map.Entry<String, String>> signInUserData(IUserBasicInfo userBasicInfo) {
		try {
			listofValidationErrors = new ArrayList<Map.Entry<String, String>>();
			listofValidationErrors.clear();

			// some string comparison will be excluded once i get the DB integrated.
			// TODO change the if cond
			if (userBasicInfo.getEmail().equals("")
					|| !Pattern.compile(emailRegex).matcher(userBasicInfo.getEmail()).find()) {
				entryMap = new AbstractMap.SimpleEntry<String, String>(email, emailErrorStatement);
				listofValidationErrors.add(entryMap);
			}
			if (userBasicInfo.getPwd().equals("")
					|| !Pattern.compile(passwordRegex).matcher(userBasicInfo.getPwd()).find()) {
				entryMap = new AbstractMap.SimpleEntry<String, String>(password, passwordErrorStatement);
				listofValidationErrors.add(entryMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<Entry<String, String>>) listofValidationErrors;
	}

}
