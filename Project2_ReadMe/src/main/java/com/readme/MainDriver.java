package com.readme;

import com.readme.dao.UserDao;
import com.readme.model.User;
import com.readme.util.HibernateUtil;

public class MainDriver {
	
	public static HibernateUtil hUtil = new HibernateUtil();
	public static UserDao uDao = new UserDao(hUtil);
	
	public static void main(String[] args) {
		User u = new User("user123", "password", "Test", "User", "testuser@email.com", 1);
		uDao.insert(u);
		
		User user = uDao.getUserByUsername("user123");
		System.out.println(user);
		user.setPassword("newpassword");
		uDao.update(user);
		
		hUtil.closeFactory();
		
//		System.out.println(uDao.getUserByUsername("user123"));
		
	}
}
