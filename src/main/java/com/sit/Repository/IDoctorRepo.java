package com.sit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sit.entity.Doctor;

/**
 * Repository interface for Doctor entity.
 * Updated from CrudRepository to JpaRepository for full Spring Data JPA
 * feature support and better Java 17 / Spring Boot 3.x compatibility.
 */
public interface IDoctorRepo extends JpaRepository<Doctor, Integer> {

}
