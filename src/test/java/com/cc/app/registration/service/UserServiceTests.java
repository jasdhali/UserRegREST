package com.cc.app.registration.service;

import org.junit.Before;
import org.junit.Test;

import com.cc.app.registration.model.User;

import junit.framework.*;

public class UserServiceTests extends TestCase {

	private User user;
	private UserService userService;
	@Before	
	public void init(){
		user = new User(1, "TEST", "user@comcast.com");
		userService = new UserServiceImpl();
	}
	
	@Test
	public void findUserTest(){
		User userNew = userService.findUser( 1 );
		assertEquals( userNew , user );
	}
}
