package com.ReadMe.controller;


import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
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

import com.ReadMe.Main;
import com.ReadMe.model.Book;
import com.ReadMe.model.User;
import com.ReadMe.service.BookService;
import com.ReadMe.service.EmailSenderService;
import com.ReadMe.service.UserService;

import lombok.NoArgsConstructor;

@RestController
@RequestMapping(value="/bookstore")
@CrossOrigin(origins="*")
@NoArgsConstructor
public class FrontController {
	private UserService uServ;
	private BookService bServ;
	private EmailSenderService eServ;
	
	@Autowired
	public FrontController(UserService uServ, BookService bServ, EmailSenderService eServ) {
		super();
		this.uServ = uServ;
		this.bServ = bServ;
		this.eServ = eServ;
	}
	
	//GET: localhost:9015/bookstore/books
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks() {
		Main.log.info("Retrieved all books from database");
		return new ResponseEntity<List<Book>>(bServ.getAllBooks(), HttpStatus.OK);
	}
	
	//GET: localhost:9015/bookstore/isbn/{isbn}
	@GetMapping("/books/isbn/{isbn}")
	public ResponseEntity<Book> getBookByIsbn(@PathVariable("isbn") String isbn) {
		Optional<Book> book = bServ.getBookByIsbn(isbn);
		if (book.isPresent()) {
			Main.log.info("Retrieved book with ISBN " + isbn + " from database");
			return new ResponseEntity<Book>(book.get(), HttpStatus.OK);
		}
		
		Main.log.warn("Book with ISBN " + isbn + " not found");
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	//GET: localhost:9015/bookstore/title/{title}
	@GetMapping("/books/title/{title}")
	public ResponseEntity<List<Book>> getBookByTitle(@PathVariable("title") String title) {
		List<Book> bookList = bServ.getBookByTitle(title);
		if (bookList == null || bookList.size() == 0) {
			Main.log.warn("Book with title " + title + " not found");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		Main.log.info("Retrieved books with title " + title + " from database");
		return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
	}
	
	//GET: localhost:9015/bookstore/author/{author}
	@GetMapping("/books/author/{author}")
	public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable("author") String author) {
		List<Book> bookList = bServ.getBookByAuthor(author);
		if (bookList == null || bookList.size() == 0) {
			Main.log.warn("Book with author " + author + " not found");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		Main.log.info("Retrieved books with author " + author + " from database");
		return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
	}
	
	//GET: localhost:9015/bookstore/publisher/{publisher}
	@GetMapping("/books/publisher/{publisher}")
	public ResponseEntity<List<Book>> getBookByPublisher(@PathVariable("publisher") String publisher) {
		List<Book> bookList = bServ.getBookByPublisher(publisher);
		if (bookList == null || bookList.size() == 0) {
			Main.log.warn("Book with publisher " + publisher + " not found");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		Main.log.info("Retrieved books with publisher " + publisher + " from database");
		return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
	}
	
	//GET: localhost:9015/bookstore/genre/{genre}
	@GetMapping("/books/genre/{genre}")
	public ResponseEntity<List<Book>> getBookByGenre(@PathVariable("genre") String genre) {
		List<Book> bookList = bServ.getBookByGenre(genre);
		if (bookList == null || bookList.size() == 0) {
			Main.log.warn("Book with genre " + genre + " not found");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		Main.log.info("Retrieved books with genre " + genre + " from database");
		return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
	}
	
	//POST: localhost:9015/bookstore/books
	//Include book in JSON format in the request body
	@PostMapping("/books")
	public ResponseEntity<Object> insertBook(@RequestBody Book book) {
		if (bServ.getBookByIsbn(book.getIsbn()).isPresent()) {
			Main.log.warn("Failed to insert book with ISBN " + book.getIsbn() + ": Book with that ISBN already exists");
			return new ResponseEntity<>("Book with that ISBN already exists.", HttpStatus.FORBIDDEN);
		}
		
		bServ.insertBook(book);
		Main.log.info("Inserted book with ISBN " + book.getIsbn() + " into database");
		return new ResponseEntity<>(bServ.getBookByIsbn(book.getIsbn()).get(), HttpStatus.ACCEPTED);
	}
	
	//DELETE: localhost:9015/bookstore/isbn/{isbn}
	@DeleteMapping("/books/isbn/{isbn}")
	public ResponseEntity<String> deleteBook(@PathVariable("isbn") String isbn) {
		Optional<Book> book = bServ.getBookByIsbn(isbn);
		if (book.isPresent()) {
			bServ.deleteBook(book.get());
			Main.log.info("Deleted book with ISBN " + isbn + " from database");
			return new ResponseEntity<String>("Book was deleted", HttpStatus.ACCEPTED);
		}
		
		Main.log.warn("Failed to delete book with ISBN " + isbn + ": No book with that ISBN");
		return new ResponseEntity<String>("No book with that ISBN", HttpStatus.NOT_FOUND);
	}
	
	//GET: localhost:9015/bookstore/users/initial
	@GetMapping("/users/initial") 
	public ResponseEntity<List<User>>insertInitialValues() {
		List<Book> orders = bServ.getBookByAuthor("Nicholas Sparks");
		List<User> uList = new ArrayList<User>(Arrays.asList(new User("orderstest","password","test","user","test@gmail.com","Customer", orders, null, null))); 
		for (User user:uList) {
			uServ.insertUser(user); 
		} 
		return new ResponseEntity<List<User>>(uServ.getAllUsers(), HttpStatus.CREATED); 
	}
	 

	//GET: localhost:9015/bookstore/users
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(){
		Main.log.info("Retrieved all users from database");
		return new ResponseEntity<List<User>>(uServ.getAllUsers(), HttpStatus.OK);
	}
	
	//GET: localhost:9015/bookstore/users/{username}
	@GetMapping("/users/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
		User user = uServ.getUserByUsername(username);
		if(user==null) {
			Main.log.warn("User with username " + username + " not found");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		Main.log.info("Retrieved user with username " + username + " from database");
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
    
	//DELETE: localhost:9015/bookstore/users/{username}
	@DeleteMapping("/users/{username}")
	public ResponseEntity<String> deleteUser(@PathVariable("username") String username){
		User user = uServ.getUserByUsername(username);
		if(user==null) {
			Main.log.warn("Failed to delete user with username " + username + ": No user of that name was found");
			return new ResponseEntity<String>("No user of that name was found", HttpStatus.NOT_FOUND);
		}
		
		uServ.deleteUser(user);
		Main.log.info("Deleted user with username " + username + " from database");
		return new ResponseEntity<String>("User was deleted", HttpStatus.OK);

	}
	
	//POST: localhost:9015/bookstore/login
	//Include user in JSON format in the request body
	@PostMapping("/login")
	public ResponseEntity<Object> validateUser(@RequestBody User user){
		List<User> userList = uServ.getAllUsers();
		for (User u : userList) {
			if ((user.getUsername().equals(u.getUsername())) && (sha256(user.getPassword()).equals(u.getPassword()))) {
				Main.log.info("Login from user with username " + user.getUsername());
				return new ResponseEntity<>(uServ.getUserByUsername(user.getUsername()), HttpStatus.ACCEPTED);
			}
		}
		return new ResponseEntity<>("Invalid login", HttpStatus.FORBIDDEN);
	}
    
	//POST: localhost:9015/bookstore/users
	//Include user in JSON format in the request body
	@PostMapping("/register")
	public ResponseEntity<Object> insertUser(@RequestBody User user){
		if(uServ.getUserByUsername(user.getUsername()) != null) {
			Main.log.warn("Failed to insert user with username " + user.getUsername() + ": User of that username already exists");
			return new ResponseEntity<>("User of that username already exists", HttpStatus.FORBIDDEN);
		}
		String password = "";
		for (int i = 0; i < 9; i++) {
			password += randomCharacter();
		}
		String encrypted = sha256(password);
		user.setPassword(encrypted);
		uServ.insertUser(user);
		eServ.sendEmail(user.getEmail(), "ReadMe: Temporary Password", 
				"Thank you for registering an account, " + user.getFirstname() + " " + user.getLastname() +
				".\nYour temporary password is: " + password + ".\nYou may reset your password after logging in.");
		Main.log.info("Inserted user with username " + user.getUsername() + " into database");
		return new ResponseEntity<>(uServ.getUserByUsername(user.getUsername()), HttpStatus.CREATED);
	}
	
	//Update User made by Joey--------------
	@PostMapping("/updateuser")
	public ResponseEntity<Object> resetUser(@RequestBody User user){
		System.out.println("In resetuser");
		if(uServ.getUserByUsername(user.getUsername()) != null) {
			uServ.updatetUser(user);
			eServ.sendEmail(user.getEmail(), "ReadMe: Password Updated", 
					"Thank you for updating your password , " + user.getFirstname() + " " + user.getLastname() +
					".\nYour new password is: " + user.getPassword() + " .");
			Main.log.info("Updated user with username " + user.getUsername());
			return new ResponseEntity<>(uServ.getUserByUsername(user.getUsername()), HttpStatus.ACCEPTED);
		}
		Main.log.warn("Failed to update user with username " + user.getUsername() + ": User of that username does not exist");
		return new ResponseEntity<>("User does not exist!", HttpStatus.FORBIDDEN);
	}
	//--------------------------------
	
	private static char randomCharacter() {
		int rand = (int)(Math.random()*62);
		if(rand <= 9) {
			int number = rand + 48;
			return (char)(number);
		} else if(rand <= 35) {
			int uppercase = rand + 55;
			return (char)(uppercase);
		} else {
			int lowercase = rand + 61;
			return (char)(lowercase);
		}
	}
	
	private static String sha256(String base) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			final byte[] hash = digest.digest(base.getBytes("UTF-8"));
			final StringBuilder hexString = new StringBuilder();
			for (int i = 0; i < hash.length; i++) {
				final String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
