package com.sit.Service;

import com.sit.entity.Doctor;

/**
 * Service interface for doctor management operations.
 * Defines business logic methods for doctor-related functionality.
 */
public interface IDoctorService {
	/**
	 * Registers a new doctor in the system.
	 * @param doctor the doctor entity to register
	 * @return confirmation message with the assigned doctor ID
	 */
    String registerDoctor(Doctor doctor);
}
