package com.cc.app.registration.service;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cc.app.registration.model.User;

import junit.framework.TestCase;


public class UserServiceImplTest extends TestCase{

	private User user;
	private User userNo4;
	private User userNo3;
	private User userNo30Save;
	private UserService userService;


	@Before
	public void setUp() throws Exception {
		user = new User(1, "TEST", "user@comcast.com");
		userNo4 =  new User(4, "User No 4", "User No@gmail.com");
		userNo3 =  new User(3, "User No 3", "User No@gmail.com");
		userNo30Save =  new User(30, "User No 30", "User No@gmail.com");
		userService = new UserServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
	}


	
	@Test
	public final void testDeleteUser() {
		int sizeBeforedelete = userService.findAllUsers().size();
		assertTrue( sizeBeforedelete > 0 );
		userService.deleteUser(3);
		int sizeAfterdelete = userService.findAllUsers().size();
		assertTrue( sizeBeforedelete > 0 );
		assertTrue(sizeBeforedelete>sizeAfterdelete);
	}
	
	@Test
	public final void testFindUser() {
		User userNew = userService.findUser( 4 );
		assertEquals( userNew , userNo4 );
	}
	
	@Test
	public final void testFindAllUsers() {
		List<User> listUsers = userService.findAllUsers();
		assertTrue( listUsers!=null);
		assertTrue( listUsers.size()>0 );
	}
	
	@Test
	public final void testSaveUser() {		
		int sizeBeforeSave = userService.findAllUsers().size();
		assertTrue( sizeBeforeSave > 0 );
		userService.saveUser(userNo30Save);
		int sizeAfterSave = userService.findAllUsers().size();
		assertTrue( sizeAfterSave > 0 );
		assertTrue(sizeAfterSave>sizeBeforeSave);
	}
	
}
