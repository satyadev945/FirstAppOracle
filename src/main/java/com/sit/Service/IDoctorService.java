package com.sit.Service;

import com.sit.entity.Doctor;

/**
 * Service interface for Doctor management operations.
 */
public interface IDoctorService {
    // Registers a new doctor and returns a confirmation message with the generated ID
    String registerDoctor(Doctor doctor);
}
