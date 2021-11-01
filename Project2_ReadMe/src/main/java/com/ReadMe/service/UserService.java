package com.ReadMe.service;

import org.springframework.stereotype.Service;

import com.ReadMe.repository.UserRepository;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class UserService {
	private UserRepository uRepo;
}
