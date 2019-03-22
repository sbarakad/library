package com.library.IDAO;

import com.library.businessModels.User;

public interface IUserDAO {
	public Boolean checkPassword(String emailAddress,String password);
	public Boolean changePassword(String emailAddress,String password);
	public Boolean registerUser(User user);
	public Boolean isUserActive(String emailAddress);
	public Boolean toggleStatus(String emailAddress);
}
