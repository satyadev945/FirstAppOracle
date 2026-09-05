package com.sit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ConfigurableApplicationContext;
//import com.sit.Service.IDoctorService;
//import com.sit.entity.Doctor;

@SpringBootApplication
public class SpringBootDataJpa {

	public static void main(String[] args) {
		
		SpringApplication.run(SpringBootDataJpa.class, args);
		
		//ApplicationContext ctx=SpringApplication.run(SpringBootDataJpa.class, args);

		//		IDoctorService ser=ctx.getBean("doctorService",IDoctorService.class);
		//		try {
		//			Doctor doc=new Doctor();
		//			doc.setDocName("sairam");
		//			doc.setSpecialization("MD_Cardio");
		//			doc.setIncome(90000.00);
		//
		//			String Result=ser.registerDoctor(doc);
		//			System.out.println(Result);
		//
		//		}
		//		catch(Exception e) {e.printStackTrace();}
		//
		//		((ConfigurableApplicationContext) ctx).close();
		//
		//
	}
}

