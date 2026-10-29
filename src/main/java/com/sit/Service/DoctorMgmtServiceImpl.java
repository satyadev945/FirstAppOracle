package com.sit.Service;

import org.springframework.stereotype.Service;
import com.sit.Repository.IDoctorRepo;
import com.sit.entity.Doctor;

@Service("doctorService")
public class DoctorMgmtServiceImpl implements IDoctorService {

    private final IDoctorRepo doctorRepo;

    public DoctorMgmtServiceImpl(IDoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @Override
    public String registerDoctor(Doctor doctor) {
        Doctor doc = doctorRepo.save(doctor);
        return "Doctor obj is saved with id value :" + doc.getDocId();
    }
}
