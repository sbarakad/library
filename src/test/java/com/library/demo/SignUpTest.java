package com.library.demo;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.Logger;
import com.library.businessModels.UserBasicInfo;
import com.library.businessModels.UserExtendedInfo;
import com.library.mockDB.SignUpMocked;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignUpTest {
	private static SignUpMocked signUpMocked;
	private static Map mapList;
	private List arrayList;
	private UserBasicInfo userBasicInfo = null;
	private UserExtendedInfo userExtendInfo = null;

	@BeforeClass
	public static void initializer() {
		signUpMocked = new SignUpMocked();
	}

	@Test
	public void testSignUpwithCorrectUserInfo() {
		mapList = signUpMocked.getMockData();

		for (int i = 0; i < mapList.size(); i++) {
			if (mapList.containsKey("clean-data")) {
				arrayList = (ArrayList) mapList.get("clean-data");
				userBasicInfo = (UserBasicInfo) arrayList.get(0);
				assertEquals("123456789", userBasicInfo.getPwd());
				assertEquals("devanshu1@gmail.com", userBasicInfo.getEmail());
				assertTrue(userBasicInfo.getEmail().contains("@"));
				userExtendInfo = (UserExtendedInfo) arrayList.get(1);
				assertEquals("deva sriv", userExtendInfo.getFullname());
				assertEquals("9024031714", userExtendInfo.getPhone());
				assertEquals("123456789", userExtendInfo.getCPassword());
				assertTrue(userBasicInfo.getPwd() == userExtendInfo.getCPassword());
				assertTrue(userExtendInfo.getPhone().length() == 10);
			}
		}
	}

	@Test
	public void testSignUpwithINCorrectUserInfo() {
		mapList = signUpMocked.getCorruptMockData();
		for (int i = 0; i < mapList.size(); i++) {
			if (mapList.containsKey("corrupt-data")) {
				arrayList = (ArrayList) mapList.get("corrupt-data");
				userBasicInfo = (UserBasicInfo) arrayList.get(0);
				assertEquals("1qaz!QAZ", userBasicInfo.getPwd());
				assertEquals("devanshu0101@gmail.com", userBasicInfo.getEmail());
				assertTrue(userBasicInfo.getEmail().contains("@"));
				userExtendInfo = (UserExtendedInfo) arrayList.get(1);
				assertEquals("devanshu sriv", userExtendInfo.getFullname());
				assertEquals("902", userExtendInfo.getPhone());
				assertEquals("1qazZAQ!", userExtendInfo.getCPassword());
				assertTrue(userBasicInfo.getPwd() != userExtendInfo.getCPassword());
				assertTrue(userExtendInfo.getPhone().length() != 10);
			}
		}
	}

	@Test
	public void testLogger() {
		Logger logger = Logger.loggerInstance();
		logger.writeLog("Test message!");
	}
}
