package com.sit.Repository;

import org.springframework.data.repository.CrudRepository;

import com.sit.entity.Doctor;

/**
 * Repository interface for Doctor entity CRUD operations.
 * Extends Spring Data JPA CrudRepository for standard database operations.
 */
public interface IDoctorRepo extends CrudRepository<Doctor,Integer>{
	// Spring Data JPA will automatically provide implementations
	// for standard CRUD operations (save, findById, findAll, delete, etc.)
}
