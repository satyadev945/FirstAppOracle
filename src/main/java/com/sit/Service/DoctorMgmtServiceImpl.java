package com.sit.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sit.Repository.IDoctorRepo;
import com.sit.entity.Doctor;

/**
 * Service implementation for managing doctor operations.
 * Provides business logic for doctor registration and management.
 */
@Service("doctorService")
public class DoctorMgmtServiceImpl implements IDoctorService {

    @Autowired
    private IDoctorRepo doctorRepo;

    @Override
    public String registerDoctor(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor cannot be null");
        }
        Doctor doc = doctorRepo.save(doctor);
        return "Doctor object is saved with id value: %d".formatted(doc.getDocId());
    }
}
