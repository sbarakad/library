package com.library.businessModels;

public class UserBasicInfo implements IUserBasicInfo{
	private String email;
	private String password;
	
	public String getEmail() {
		return email;
	}

	public String getPwd() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
		
	}

	public void setPwd(String password) {
		this.password = password;
	}

}
