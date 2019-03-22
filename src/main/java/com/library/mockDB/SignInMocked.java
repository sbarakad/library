package com.library.mockDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.library.businessModels.IUserBasicInfo;
import com.library.businessModels.UserBasicInfo;


public class SignInMocked {
	private String email;
	private String password;
	
	List arrItems;
	private IUserBasicInfo userBasicInfo;
	Map map;
	
	public SignInMocked() {
		userBasicInfo = new UserBasicInfo();
	}

	public Map getCorruptMockData() {
		arrItems = new ArrayList<String>();
		map = new HashMap<String, ArrayList>();
		userBasicInfo.setEmail("devanshu0101@gmail.com");
		userBasicInfo.setPwd("1qaz!QAZ");
		arrItems.add(userBasicInfo);
		map.put("corrupt-data", arrItems);
		return map;
	}

	public Map getMockData() {
		arrItems = new ArrayList<String>();
		map = new HashMap<String, ArrayList>();
		userBasicInfo.setEmail("devanshu1@gmail.com");
		userBasicInfo.setPwd("123456789");
		arrItems.add(userBasicInfo);
		map.put("clean-data", arrItems);
		return map;
	}
}
