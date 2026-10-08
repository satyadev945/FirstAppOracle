package com.sit.Repository;

import com.sit.entity.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for IDoctorRepo using Spring Data JPA with H2 in-memory database.
 * Tests all CrudRepository operations inherited by IDoctorRepo.
 */
@DataJpaTest
@ActiveProfiles("test")
@DisplayName("IDoctorRepo Repository Tests")
class IDoctorRepoTest {

    @Autowired
    private IDoctorRepo doctorRepo;

    @Autowired
    private TestEntityManager entityManager;

    private Doctor sampleDoctor;

    @BeforeEach
    void setUp() {
        sampleDoctor = new Doctor();
        sampleDoctor.setDocName("sairam");
        sampleDoctor.setSpecialization("MD_Cardio");
        sampleDoctor.setIncome(90000.00);
    }

    // ─── save() Tests ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("save persists a Doctor and returns saved entity with generated id")
    void testSave_persistsDoctorWithGeneratedId() {
        // Act
        Doctor saved = doctorRepo.save(sampleDoctor);

        // Assert
        assertNotNull(saved,          "Saved doctor should not be null");
        assertNotNull(saved.getDocId(),"Saved doctor should have a generated id");
    }

    @Test
    @DisplayName("save persists Doctor with correct name")
    void testSave_persistsDoctorWithCorrectName() {
        // Act
        Doctor saved = doctorRepo.save(sampleDoctor);

        // Assert
        assertEquals("sairam", saved.getDocName(),
                "Saved doctor name should match input");
    }

    @Test
    @DisplayName("save persists Doctor with correct specialization")
    void testSave_persistsDoctorWithCorrectSpecialization() {
        // Act
        Doctor saved = doctorRepo.save(sampleDoctor);

        // Assert
        assertEquals("MD_Cardio", saved.getSpecialization(),
                "Saved doctor specialization should match input");
    }

    @Test
    @DisplayName("save persists Doctor with correct income")
    void testSave_persistsDoctorWithCorrectIncome() {
        // Act
        Doctor saved = doctorRepo.save(sampleDoctor);

        // Assert
        assertEquals(90000.00, saved.getIncome(), 0.001,
                "Saved doctor income should match input");
    }

    // ─── findById() Tests ─────────────────────────────────────────────────────

    @Test
    @DisplayName("findById returns Doctor when id exists")
    void testFindById_whenIdExists_returnsDoctor() {
        // Arrange
        Doctor saved = doctorRepo.save(sampleDoctor);
        entityManager.flush();

        // Act
        Optional<Doctor> found = doctorRepo.findById(saved.getDocId());

        // Assert
        assertTrue(found.isPresent(), "Doctor should be found by id");
        assertEquals(saved.getDocId(), found.get().getDocId());
    }

    @Test
    @DisplayName("findById returns empty Optional when id does not exist")
    void testFindById_whenIdDoesNotExist_returnsEmpty() {
        // Act
        Optional<Doctor> found = doctorRepo.findById(Integer.MAX_VALUE);

        // Assert
        assertFalse(found.isPresent(), "Should return empty Optional for non-existent id");
    }

    // ─── findAll() Tests ──────────────────────────────────────────────────────

    @Test
    @DisplayName("findAll returns all saved doctors")
    void testFindAll_returnsAllSavedDoctors() {
        // Arrange
        Doctor doc1 = new Doctor();
        doc1.setDocName("Alice");
        doc1.setSpecialization("Neurology");
        doc1.setIncome(120000.00);

        Doctor doc2 = new Doctor();
        doc2.setDocName("Bob");
        doc2.setSpecialization("Orthopedics");
        doc2.setIncome(95000.00);

        doctorRepo.save(doc1);
        doctorRepo.save(doc2);
        entityManager.flush();

        // Act
        Iterable<Doctor> all = doctorRepo.findAll();

        // Assert
        assertNotNull(all, "findAll should not return null");
        long count = 0;
        for (Doctor d : all) count++;
        assertTrue(count >= 2, "Should find at least 2 doctors");
    }

    @Test
    @DisplayName("findAll returns empty iterable when no doctors exist")
    void testFindAll_whenNoDoctors_returnsEmpty() {
        // Arrange - ensure clean state
        doctorRepo.deleteAll();
        entityManager.flush();

        // Act
        Iterable<Doctor> all = doctorRepo.findAll();

        // Assert
        assertNotNull(all);
        long count = 0;
        for (Doctor d : all) count++;
        assertEquals(0, count, "Should return empty iterable when no doctors exist");
    }

    // ─── existsById() Tests ───────────────────────────────────────────────────

    @Test
    @DisplayName("existsById returns true when doctor exists")
    void testExistsById_whenDoctorExists_returnsTrue() {
        // Arrange
        Doctor saved = doctorRepo.save(sampleDoctor);
        entityManager.flush();

        // Act
        boolean exists = doctorRepo.existsById(saved.getDocId());

        // Assert
        assertTrue(exists, "existsById should return true for existing doctor");
    }

    @Test
    @DisplayName("existsById returns false when doctor does not exist")
    void testExistsById_whenDoctorDoesNotExist_returnsFalse() {
        // Act
        boolean exists = doctorRepo.existsById(Integer.MAX_VALUE);

        // Assert
        assertFalse(exists, "existsById should return false for non-existent doctor");
    }

    // ─── count() Tests ────────────────────────────────────────────────────────

    @Test
    @DisplayName("count returns correct number of doctors")
    void testCount_returnsCorrectCount() {
        // Arrange
        doctorRepo.deleteAll();
        entityManager.flush();

        doctorRepo.save(sampleDoctor);

        Doctor doc2 = new Doctor();
        doc2.setDocName("Carol");
        doc2.setSpecialization("Pediatrics");
        doc2.setIncome(80000.00);
        doctorRepo.save(doc2);
        entityManager.flush();

        // Act
        long count = doctorRepo.count();

        // Assert
        assertEquals(2, count, "count should return 2 after saving 2 doctors");
    }

    // ─── deleteById() Tests ───────────────────────────────────────────────────

    @Test
    @DisplayName("deleteById removes doctor from repository")
    void testDeleteById_removesDoctorFromRepo() {
        // Arrange
        Doctor saved = doctorRepo.save(sampleDoctor);
        entityManager.flush();
        Integer id = saved.getDocId();

        // Act
        doctorRepo.deleteById(id);
        entityManager.flush();

        // Assert
        assertFalse(doctorRepo.existsById(id),
                "Doctor should not exist after deleteById");
    }

    // ─── delete() Tests ───────────────────────────────────────────────────────

    @Test
    @DisplayName("delete removes the given doctor entity")
    void testDelete_removesGivenDoctorEntity() {
        // Arrange
        Doctor saved = doctorRepo.save(sampleDoctor);
        entityManager.flush();
        Integer id = saved.getDocId();

        // Act
        doctorRepo.delete(saved);
        entityManager.flush();

        // Assert
        assertFalse(doctorRepo.existsById(id),
                "Doctor should not exist after delete");
    }

    // ─── Update (save existing) Tests ─────────────────────────────────────────

    @Test
    @DisplayName("save updates existing doctor when id already exists")
    void testSave_updatesExistingDoctor() {
        // Arrange
        Doctor saved = doctorRepo.save(sampleDoctor);
        entityManager.flush();

        // Act - update the name
        saved.setDocName("Updated Name");
        Doctor updated = doctorRepo.save(saved);
        entityManager.flush();

        // Assert
        assertEquals("Updated Name", updated.getDocName(),
                "Doctor name should be updated");
        assertEquals(saved.getDocId(), updated.getDocId(),
                "Doctor id should remain the same after update");
    }
}
