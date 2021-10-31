package com.ReadMe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ReadMe.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	public List<User> findAll();
	public User findByUserName( String username);
	public User findByUserPassword( String password);
	
	public User findUserByUsernameAndPassword(String userame, String password);
	
	
	public User findByUserFirstname( String firstname);
	public User findByUserLastname( String lastname);
	public User findByUserEmail( String email);
	public User findByUserRolid(int roleid);
       
}
