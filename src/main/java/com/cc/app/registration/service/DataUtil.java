package com.cc.app.registration.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cc.app.registration.model.User;

public class DataUtil {
	public static List<User> getSeedData() {
		List<User> userDataCollection = new ArrayList<User>();
		for (int i = 1; i <= 10; i++) {
			User user = new User(i, "User No " + i, "User No@gmail.com");
			user.setDate(new Date());
			userDataCollection.add(user);
		}
		return userDataCollection;
	}
}
