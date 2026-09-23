package com.sit.Service;

import com.sit.entity.Doctor;

/**
 * Service interface for Doctor management operations.
 * Compatible with Java 25 and Spring Boot 3.x.
 */
public interface IDoctorService {
    String registerDoctor(Doctor doctor);
}
