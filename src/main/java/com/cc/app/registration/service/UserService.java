package com.cc.app.registration.service;

import java.util.List;

import com.cc.app.registration.model.User;

public interface UserService {
	User findUser(Integer id);
/*
	User findByName(String name);
*/
	void saveUser(User user);
/*
	void updateUser(User user);
*/
	public void  deleteUser(Integer id);

	public List<User> findAllUsers();

/*	void deleteAllUsers();

	public boolean isUserExist(User user);*/
}
