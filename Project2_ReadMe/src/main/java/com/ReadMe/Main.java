package com.ReadMe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.ReadMe.service.EmailSenderService;

@SpringBootApplication
public class Main {
	@Autowired
	private EmailSenderService eServ;
	
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void sendMail() {
		eServ.sendEmail("readme.revature@gmail.com", "Test subject", "Test body");
	}
}
