package com.cc.app.registration.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TestUserModel extends TestCase{
	User user = new User();
	
	private int 	USER_ID = 10; 
	private String USER_NAME = "John";
	private String USER_EMAIl = "John@comcast.com";
	
	
	@Before
	public void setUp() throws Exception {
		user.setUserId(USER_ID);
		user.setUsername(USER_NAME);
		user.setEmail(USER_EMAIl);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		User userToTest = new User(10, "John", "John@comcast.com" );
		assertEquals( user , userToTest);
	}

}
