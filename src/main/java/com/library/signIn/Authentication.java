package com.library.signIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.library.businessModels.IUserBasicInfo;
import com.library.businessModels.IUserExtendedInfo;
import com.library.common.XmlParser;

//Template pattern implemented in this class and in its child class. I have implemented setValidationRules() and setErrorStringRules() that are being used by the child class many times.
// Abstract functions are also added in this class and that are used by child class, where it changes the flow of action as required.
// Hence, template patter.
public abstract class Authentication {
	// Roots in the authentication xml file are initialized below.
	private static final String passwordRegexKeyRoot = "passwordRegex";
	private static final String emailRegexKeyRoot = "emailRegex";
	private static final String phoneCheckKeyRoot = "phoneCheck";
	private static final String adminCheckKeyRoot = "adminId";
	private static final String emailErrorKeyRoot = "emailErrorString";
	private static final String passwordErrorKeyRoot = "passwordErrorString";
	private static final String emptyErrorKeyRoot = "emptyErrorString";
	private static final String phoneErrorKeyRoot = "phoneErrorString";
	private static final String cpasswordErrorKeyRoot = "cpasswordErrorString";

	// Validations that will be checked via externalized file.
	protected String passwordRegex;
	protected String emailRegex;
	protected int phoneCheck;

	// Statements that are visible to the user if they miss out some fields in the
	// forms.
	protected String passwordErrorStatement;
	protected String emailErrorStatement;
	protected String blankErrorStatement;
	protected String phoneErrorStatement;
	protected String cpasswordErrorStatement;
	protected static String isAdmin;

	private static final String filePathToValidations = "AuthenticationRules.xml";
	private static final String filePathToErrorStatements = "ValidationStatements.xml";

	public abstract ArrayList<Map.Entry<String, String>> signUpUserData(IUserBasicInfo userBasicInfo,
			IUserExtendedInfo userExtendedInfo);

	public abstract ArrayList<Map.Entry<String, String>> signInUserData(IUserBasicInfo userBasicInfo);

	protected void setValidationRules() {
		try {
			// this function will help in setting the regex rules into private data members.
			List<Map.Entry<String, String>> list = XmlParser.parse(filePathToValidations);
			for (int i = 0; i < list.size(); i++) {
				switch (list.get(i).getKey()) {
				case passwordRegexKeyRoot:
					this.passwordRegex = list.get(i).getValue();
					break;
				case emailRegexKeyRoot:
					this.emailRegex = list.get(i).getValue();
					break;
				case phoneCheckKeyRoot:
					this.phoneCheck = Integer.parseInt(list.get(i).getValue());
					break;
				case adminCheckKeyRoot:
					this.isAdmin = list.get(i).getValue();
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void setErrorStringRules() {
		try {
			// this function will help in setting the regex rules into private data members.
			List<Map.Entry<String, String>> list = XmlParser.parse(filePathToErrorStatements);
			for (int i = 0; i < list.size(); i++) {
				switch (list.get(i).getKey()) {
				case emailErrorKeyRoot:
					this.emailErrorStatement = list.get(i).getValue();
					break;
				case emptyErrorKeyRoot:
					this.blankErrorStatement = list.get(i).getValue();
					break;
				case passwordErrorKeyRoot:
					this.passwordErrorStatement = list.get(i).getValue();
					break;
				case phoneErrorKeyRoot:
					this.phoneErrorStatement = list.get(i).getValue();
					break;
				case cpasswordErrorKeyRoot:
					this.cpasswordErrorStatement = list.get(i).getValue();
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
