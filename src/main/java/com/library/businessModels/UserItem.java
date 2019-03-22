package com.library.businessModels;

import java.util.Date;

public class UserItem {
	
	
	private String email;
	private int itemID;
	private Date timestamp;
	private short status;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getItem_ID() {
		return itemID;
	}
	public void setItem_ID(int itemID) {
		this.itemID = itemID;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public short getStatus() {
		return status;
	}
	public void setStatus(short status) {
		this.status = status;
	}
	
	

}
