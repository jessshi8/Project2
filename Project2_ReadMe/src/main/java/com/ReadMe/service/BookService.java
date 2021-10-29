package com.ReadMe.service;

import org.springframework.stereotype.Service;

import com.ReadMe.repository.BookRepository;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class BookService {
	private BookRepository bRepo;
}
