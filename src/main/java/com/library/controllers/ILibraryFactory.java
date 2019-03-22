package com.library.controllers;

import javax.servlet.http.HttpSession;

import com.library.businessModels.User;
import com.library.signIn.SignInController;
import com.library.signUp.SignUpController;

public interface ILibraryFactory{
	public SignInController signIn(User user,HttpSession httpSession);
	public SignUpController signUp(User user);
}