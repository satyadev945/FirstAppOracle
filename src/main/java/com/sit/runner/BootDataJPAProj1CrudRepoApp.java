package com.sit.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sit.Service.IDoctorService;
import com.sit.entity.Doctor;

/**
 * Command line runner for testing doctor registration functionality.
 * Executes on application startup to demonstrate CRUD operations.
 */
@Component
public class BootDataJPAProj1CrudRepoApp implements CommandLineRunner {
	
	@Autowired
	private IDoctorService service;
	
	@Override
	public void run(String... args) throws Exception {
		try {
			Doctor doc = new Doctor();
			doc.setDocName("sairam");
			doc.setSpecialization("MD_Cardio");
			doc.setIncome(90000.00);

			String result = service.registerDoctor(doc);
			System.out.println(result);
			System.out.println("Doctor details: " + doc);
		} catch (Exception e) {
			System.err.println("Error registering doctor: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
