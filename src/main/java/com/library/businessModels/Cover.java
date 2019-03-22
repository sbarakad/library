package com.library.businessModels;

import java.sql.Blob;

public class Cover {
	private int itemID;
	private Blob coverBlob;
	private String fileExtension;
	
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public Blob getCoverBlob() {
		return coverBlob;
	}
	public void setCoverBlob(Blob coverBlob) {
		this.coverBlob = coverBlob;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
}
