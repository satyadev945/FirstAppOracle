package com.sit.Repository;

import org.springframework.data.repository.CrudRepository;

import com.sit.entity.Doctor;

public interface IDoctorRepo extends CrudRepository<Doctor,Integer>{

}
