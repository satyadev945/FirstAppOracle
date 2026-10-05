package com.sit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class for the Doctor Management System.
 * Uses Spring Data JPA for database operations with Oracle/MySQL support.
 * 
 * @author SIT Team
 * @version 1.0
 * @since Java 17
 */
@SpringBootApplication
public class SpringBootDataJpa {

	/**
	 * Application entry point.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpa.class, args);
	}
}
