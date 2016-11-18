package com.cc.app.registration.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

import com.cc.app.registration.config.Constants;
import com.cc.app.registration.model.User;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
	List<User> userDataCollection = new ArrayList<User>();
	public UserServiceImpl() {
		super();
		userDataCollection.addAll( DataUtil.getSeedData() );
	}

	public synchronized void deleteUser(Integer id) {
		for (User user : userDataCollection) {
			if (user.getUserId().equals(id)) {
				userDataCollection.remove(user);
				break;
			}
		}
	}

	public User findUser(Integer id) {
		User usr = null;
		for (User user : userDataCollection) {
			if (user.getUserId().equals(id)) {
				usr = user;
				break;
			}
		}
		return usr;
	}

	public List<User> findAllUsers() {
		return userDataCollection;
	}

	public synchronized void saveUser(User user) {
		Date date = new Date();
		user.setDate(new Date());
		userDataCollection.add(user);
	}

	private void setList(List<User> list) {
		this.userDataCollection = list;
	}

	@PostConstruct
	public void init() {
		InputStream registrationDataStream = null;
		List<User> userData = null;
		try {
			//this.getClass().getR
			registrationDataStream = new FileInputStream(Constants.SER_FILE_NAME);
			ObjectInputStream in = new ObjectInputStream(registrationDataStream);
			userData = (ArrayList<User>) in.readObject();
			in.close();
			registrationDataStream.close();
			if (userData != null) {
				this.setList(userData);
			}
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("User class not found");
			c.printStackTrace();
			return;
		} catch (Exception ioex) {
			System.out.println("@@@@@@@@@@@@@@@ EXCEPTION ===> " + ioex.getMessage());
		}
	}

	@PreDestroy
	public void destroy() {
		try {
			FileOutputStream fileOut = new FileOutputStream(Constants.SER_FILE_NAME);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(userDataCollection);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in =>" + Constants.SER_FILE_NAME);
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}
