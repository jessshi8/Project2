package com.ReadMe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users_table")

public class Users {
//@Id
@Column(name="username", unique=true)
private String username;

@Column(name="password")
private String password;

@Column(name="firstname")
private String firstname;

@Column(name="lastname")
private String lastname;

@Column(name="email")
private String email;

@Column(name="roleid")
private String roleid;

      public Users() {
	// TODO Auto-generated constructor stub
}     
      
	public Users(String password, String firstname, String lastname, String email, String roleid) {
		super();
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.roleid = roleid;
	}


	public Users(String username, String password, String firstname, String lastname, String email, String roleid) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.roleid = roleid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getRoleid() {
		return roleid;
	}


	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}


	@Override
	public String toString() {
		return "Users [username=" + username + ", password=" + password + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", roleid=" + roleid + "]";
	}
     
      
}

