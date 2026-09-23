package com.sit.Repository;

import org.springframework.data.repository.CrudRepository;

import com.sit.entity.Doctor;

/**
 * Repository interface for Doctor entity CRUD operations.
 * Extends CrudRepository for standard data access operations.
 * Compatible with Spring Data JPA and Java 25.
 */
public interface IDoctorRepo extends CrudRepository<Doctor, Integer> {

}
