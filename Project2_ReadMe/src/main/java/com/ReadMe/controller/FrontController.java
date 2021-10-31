package com.ReadMe.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ReadMe.model.User;
import com.ReadMe.service.BookService;
import com.ReadMe.service.UserService;


import lombok.NoArgsConstructor;

@RestController
@RequestMapping(value="/users")
@CrossOrigin(origins="*")// we are allowing any domaine to request the server 
@NoArgsConstructor
public class FrontController {
	private UserService uServ;
	private BookService bServ;
	
	@Autowired
	public FrontController(UserService uServ, BookService bServ) {
		super();
		this.uServ = uServ;
		this.bServ = bServ;
	}
	
	@GetMapping("/initial")
    public ResponseEntity<List<User>>  insertInitialValues(){
    	List<User> uList = new ArrayList<User>(Arrays.asList(new User("user1","password1","sonia", "bench","sonia@gmail.com","customer")));
    	for (User user:uList) {
    		uServ.insertUser(user);
    	}
        return new ResponseEntity<List<User>>(uServ.getAllUsers(), HttpStatus.CREATED);
    }

	  @GetMapping()
      public ResponseEntity<List<User>>getAllUsers(){
   	   return new ResponseEntity<List<User>>(uServ.getAllUsers(), HttpStatus.OK);
	  }
	
	  @GetMapping("/{username}")
      public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
   	   User user = uServ.getUserByUsername(username);
   	   if(user==null) {
   		   return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
   	   }
   	    return new ResponseEntity<>(null, HttpStatus.OK);
   	   }
      
      @DeleteMapping("/{username}")
      public ResponseEntity<String> deleteUser(@PathVariable("username") String username){
   	  User user = uServ.getUserByUsername(username);
   	   if(user==null) {
   		   return new ResponseEntity<String>("No user of that name was found", HttpStatus.NOT_FOUND);
      }
      uServ.deleteUser(user);
      return new ResponseEntity<String>("user was deleted", HttpStatus.NOT_FOUND);
      
      }
      
      @PostMapping()
      public ResponseEntity<Object> insertUser(@RequestBody User user){
   	   System.out.println(user);
   	   if(uServ.getUserByUsername(user.getUsername()) != null) {
   		   return new ResponseEntity<>("user of that name already exists",HttpStatus.FORBIDDEN);
   	   }
   	   uServ.insertUser(user);
   	   return new ResponseEntity<>(uServ.getUserByUsername(user.getUsername()), HttpStatus.CREATED);
      }
	  

}
