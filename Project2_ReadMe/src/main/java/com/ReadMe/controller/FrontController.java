package com.ReadMe.controller;

import java.util.List;
import java.util.Optional;

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

import com.ReadMe.model.Book;
import com.ReadMe.service.BookService;
import com.ReadMe.service.UserService;

import lombok.NoArgsConstructor;

@RestController
@RequestMapping(value="/bookstore")
@CrossOrigin(origins="*")
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
	
	//GET: localhost:9015/bookstore
	@GetMapping()
	public ResponseEntity<List<Book>> getAllBooks() {
		return new ResponseEntity<List<Book>>(bServ.getAllBooks(), HttpStatus.OK);
	}
	
	//GET: localhost:9015/bookstore/isbn/{isbn}
	@GetMapping("/isbn/{isbn}")
	public ResponseEntity<Book> getBookByIsbn(@PathVariable("isbn") String isbn) {
		Optional<Book> book = bServ.getBookByIsbn(isbn);
		if (book.isPresent()) {
			return new ResponseEntity<Book>(book.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	//GET: localhost:9015/bookstore/title/{title}
	@GetMapping("/title/{title}")
	public ResponseEntity<List<Book>> getBookByTitle(@PathVariable("title") String title) {
		List<Book> bookList = bServ.getBookByTitle(title);
		if (bookList == null || bookList.size() == 0) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
	}
	
	//GET: localhost:9015/bookstore/author/{author}
	@GetMapping("/author/{author}")
	public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable("author") String author) {
		List<Book> bookList = bServ.getBookByAuthor(author);
		if (bookList == null || bookList.size() == 0) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
	}
	
	//GET: localhost:9015/bookstore/publisher/{publisher}
	@GetMapping("/publisher/{publisher}")
	public ResponseEntity<List<Book>> getBookByPublisher(@PathVariable("publisher") String publisher) {
		List<Book> bookList = bServ.getBookByPublisher(publisher);
		if (bookList == null || bookList.size() == 0) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
	}
	
	//GET: localhost:9015/bookstore//genre/{genre}
	@GetMapping("/genre/{genre}")
	public ResponseEntity<List<Book>> getBookByGenre(@PathVariable("genre") String genre) {
		List<Book> bookList = bServ.getBookByGenre(genre);
		if (bookList == null || bookList.size() == 0) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
	}
	
	//POST: localhost:9015/bookstore
	//Include book in JSON format in the request body
	@PostMapping()
	public ResponseEntity<Object> insertBook(@RequestBody Book book) {
		if (bServ.getBookByIsbn(book.getIsbn()).isPresent()) {
			return new ResponseEntity<>("Book with that ISBN already exists.", HttpStatus.FORBIDDEN);
		}
		
		bServ.insertBook(book);
		return new ResponseEntity<>(bServ.getBookByIsbn(book.getIsbn()).get(), HttpStatus.ACCEPTED);
	}
	
	//DELETE: localhost:9015/bookstore/isbn/{isbn}
	@DeleteMapping("isbn/{isbn}")
	public ResponseEntity<String> deleteBook(@PathVariable("isbn") String isbn) {
		Optional<Book> book = bServ.getBookByIsbn(isbn);
		if (book.isPresent()) {
			bServ.deleteBook(book.get());
			return new ResponseEntity<String>("Book was deleted", HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<String>("No book with that ISBN", HttpStatus.NOT_FOUND);
	}
	
}
