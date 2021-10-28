package com.ReadMe;

import org.hibernate.SessionFactory;

import com.ReadMe.model.Users;
import com.ReadMe.repository.UserRepository;

public class MainDriver {
	public static SessionFactory sesFact;
	public static UserRepository uRepo;
	public static Users user;
	
	public static void main(String[] args) {
		Users u = new Users("joey2", "password", "Joey", "Vigil", "joey2@vigil.com", "Customer");

		uRepo = new UserRepository(sesFact);
		uRepo.insert(u);
//		System.out.println(uRepo.selectByUsername("joey2"));
	}

}
