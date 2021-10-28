package com.readme;

import org.apache.log4j.Level;

import com.readme.dao.UserDao;
import com.readme.model.User;
import com.readme.util.HibernateUtil;

public class MainDriver {
	
	public static HibernateUtil hUtil = new HibernateUtil();
	public static UserDao uDao = new UserDao(hUtil);
	
	public static void main(String[] args) {
		HibernateUtil.log.setLevel(Level.TRACE);
		UserDao.log.setLevel(Level.TRACE);
		User u = new User("user123", "password", "Test", "User", "testuser@email.com", 1);
		uDao.insert(u);
		
		User user = uDao.getUserByUsername("user123");
		System.out.println(user);
		user.setPassword("newpassword");
		uDao.update(user);
		
		hUtil.closeFactory();
	}
}
