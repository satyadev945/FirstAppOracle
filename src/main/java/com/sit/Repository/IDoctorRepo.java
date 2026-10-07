package com.sit.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.sit.entity.Doctor;

@Repository
public interface IDoctorRepo extends CrudRepository<Doctor,Integer>{

}
