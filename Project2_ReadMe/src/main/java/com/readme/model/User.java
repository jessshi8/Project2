package com.readme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="user")
public class User {
	@Id
	@Column(name="username")
	private String username;
	@Column(name="password", nullable=false)
	private String password; 
	@Column(name="firstname", nullable=false)
	private String firstname; 
	@Column(name="lastname", nullable=false)
	private String lastname; 
	@Column(name="email", unique=true, nullable=false)
	private String email;
	@Column(name="role")
	@ColumnDefault("1") // We use 1 to identify users with customer role
	private int role;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, String firstname, String lastname, String email, int role) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
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

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", email=" + email + ", role=" + role + "]";
	}
}
