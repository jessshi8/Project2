package com.ReadMe.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ReadMe.model.Users;
import com.ReadMe.repository.UserRepository;

@Controller
@RequestMapping(path="/usercontroller")// this will map the controller class specific uri aka:localhost:port#/projectname/api/usercontroller

public class UserController {
	private UserRepository uRepo;
	
	public UserController() {
		// TODO Auto-generated constructor stub
	}
	@Autowired //less coupling effect decoupling 
	public UserController(UserRepository uRepo) {
		super();
		//System.out.println("in project 2 control constructor");
		this.uRepo = uRepo;
	}
	
	@RequestMapping(value="/init", method=RequestMethod.GET, produces="application/json")// this method will be triggered when a get request is made at the URI input of
	//localhost:8080/ReadMe-BookStore/api/usercontroller/init
	public @ResponseBody List<Users> insertAllUsers(){
		//System.out.println("in project 2 insertAllUsers");
		List<Users>uList = new ArrayList<Users>(Arrays.asList(new Users("u1","password1","fname1", "lname1","test1@gmail.com","customer"),
				new Users("u2","password2","fname2", "lname2","test2@gmail.com","customer"), new Users("u3","password3","fname3", "lname3","test3@gmail.com","customer")));
		for(Users user: uList) {
			uRepo.insert(user);

		}
		return uRepo.selectAll();
	}
	
	@PostMapping(value="/users/id", produces="application/json")//this will make the below method exposed via post http method at the 
	//endpoint of localhost:8080/ReadMe-BookStore/api/usercontroller/users/id
	public ResponseEntity<Users> getUserByUsername(@RequestParam("username")String username){// alows me to create custom statuses 
		//@requestparam will grab the specified value out of the parameters of the query parameters in the url,  url?keyname=value
		return new ResponseEntity<Users>(uRepo.selectByUsername("username"), HttpStatus.I_AM_A_TEAPOT);
	
	}
	@GetMapping(value="/users/{username}")
	public ResponseEntity<Users> getByPathVariable(@PathVariable("username") String username){
		return new ResponseEntity<Users>(uRepo.selectByUsername(username), HttpStatus.OK);
		
	}
    @PostMapping(value="/user", consumes=MediaType.APPLICATION_JSON_VALUE)// IF its not json reh=ject it im only consuming json here
     public ResponseEntity<Users>insertUser(@RequestBody LinkedHashMap<String, String> uMap){
    	
    	System.out.println(uMap);
    	uRepo.insert(new Users(uMap.get("username"),uMap.get("password"), uMap.get("firstname"), uMap.get("lastname"),uMap.get("email"), uMap.get("role")));
    	
    	return new ResponseEntity<Users>(new Users(),HttpStatus.OK);
    	
    	
    }
}

