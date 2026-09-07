package com.sit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sit.entity.Doctor;

public interface IDoctorRepo extends JpaRepository<Doctor, Integer> {

}
