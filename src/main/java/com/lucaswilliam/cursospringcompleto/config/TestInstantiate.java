package com.lucaswilliam.cursospringcompleto.config;


import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.lucaswilliam.cursospringcompleto.services.DBService;
import com.lucaswilliam.cursospringcompleto.services.EmailService;
import com.lucaswilliam.cursospringcompleto.services.MockEmailService;

@Configuration
@Profile("test")
public class TestInstantiate{
	
	@Autowired
	private DBService service;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		service.instantiateDatabase();
		return true;
	}

	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

}
