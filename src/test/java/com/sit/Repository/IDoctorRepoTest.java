package com.sit.Repository;

import com.sit.entity.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for IDoctorRepo using H2 in-memory database.
 * Tests all CrudRepository operations inherited by IDoctorRepo.
 * Note: Doctor uses a SEQUENCE generator, so IDs are auto-assigned by the sequence.
 */
@DataJpaTest
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
    "spring.datasource.driver-class-name=org.h2.Driver",
    "spring.datasource.username=sa",
    "spring.datasource.password=",
    "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
@DisplayName("IDoctorRepo Repository Tests")
class IDoctorRepoTest {

    @Autowired
    private IDoctorRepo doctorRepo;

    @BeforeEach
    void setUp() {
        doctorRepo.deleteAll();
    }

    // ─── Helper method ────────────────────────────────────────────────────────

    private Doctor buildDoctor(String name, String specialization, Double income) {
        Doctor doc = new Doctor();
        doc.setDocName(name);
        doc.setSpecialization(specialization);
        doc.setIncome(income);
        return doc;
    }

    // ─── save() Tests ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("save persists a Doctor and returns saved entity with generated id")
    void save_validDoctor_returnsSavedDoctorWithGeneratedId() {
        // Arrange
        Doctor doctor = buildDoctor("sairam", "MD_Cardio", 90000.00);

        // Act
        Doctor savedDoctor = doctorRepo.save(doctor);

        // Assert
        assertNotNull(savedDoctor);
        assertNotNull(savedDoctor.getDocId());
    }

    @Test
    @DisplayName("save persists Doctor with correct name")
    void save_validDoctor_persistsDocName() {
        // Arrange
        Doctor doctor = buildDoctor("sairam", "MD_Cardio", 90000.00);

        // Act
        Doctor savedDoctor = doctorRepo.save(doctor);

        // Assert
        assertEquals("sairam", savedDoctor.getDocName());
    }

    @Test
    @DisplayName("save persists Doctor with correct specialization")
    void save_validDoctor_persistsSpecialization() {
        // Arrange
        Doctor doctor = buildDoctor("sairam", "MD_Cardio", 90000.00);

        // Act
        Doctor savedDoctor = doctorRepo.save(doctor);

        // Assert
        assertEquals("MD_Cardio", savedDoctor.getSpecialization());
    }

    @Test
    @DisplayName("save persists Doctor with correct income")
    void save_validDoctor_persistsIncome() {
        // Arrange
        Doctor doctor = buildDoctor("sairam", "MD_Cardio", 90000.00);

        // Act
        Doctor savedDoctor = doctorRepo.save(doctor);

        // Assert
        assertEquals(90000.00, savedDoctor.getIncome());
    }

    // ─── findById() Tests ─────────────────────────────────────────────────────

    @Test
    @DisplayName("findById returns Doctor when valid id is provided")
    void findById_existingId_returnsDoctor() {
        // Arrange
        Doctor doctor = buildDoctor("sairam", "MD_Cardio", 90000.00);
        Doctor savedDoctor = doctorRepo.save(doctor);
        Integer savedId = savedDoctor.getDocId();

        // Act
        Optional<Doctor> found = doctorRepo.findById(savedId);

        // Assert
        assertTrue(found.isPresent());
    }

    @Test
    @DisplayName("findById returns correct Doctor for given id")
    void findById_existingId_returnsCorrectDoctor() {
        // Arrange
        Doctor doctor = buildDoctor("sairam", "MD_Cardio", 90000.00);
        Doctor savedDoctor = doctorRepo.save(doctor);
        Integer savedId = savedDoctor.getDocId();

        // Act
        Optional<Doctor> found = doctorRepo.findById(savedId);

        // Assert
        assertTrue(found.isPresent());
        assertEquals("sairam", found.get().getDocName());
    }

    @Test
    @DisplayName("findById returns empty Optional for non-existing id")
    void findById_nonExistingId_returnsEmptyOptional() {
        // Act
        Optional<Doctor> found = doctorRepo.findById(Integer.MAX_VALUE);

        // Assert
        assertFalse(found.isPresent());
    }

    @Test
    @DisplayName("findById returns doctor with correct specialization")
    void findById_existingId_returnsCorrectSpecialization() {
        // Arrange
        Doctor doctor = buildDoctor("Dr. Jane", "Neurology", 120000.00);
        Doctor savedDoctor = doctorRepo.save(doctor);
        Integer savedId = savedDoctor.getDocId();

        // Act
        Optional<Doctor> found = doctorRepo.findById(savedId);

        // Assert
        assertTrue(found.isPresent());
        assertEquals("Neurology", found.get().getSpecialization());
    }

    // ─── findAll() Tests ──────────────────────────────────────────────────────

    @Test
    @DisplayName("findAll returns all saved doctors")
    void findAll_multipleDoctors_returnsAllDoctors() {
        // Arrange
        doctorRepo.save(buildDoctor("sairam", "MD_Cardio", 90000.00));
        doctorRepo.save(buildDoctor("Dr. Jane", "Neurology", 120000.00));

        // Act
        Iterable<Doctor> allDoctors = doctorRepo.findAll();

        // Assert
        assertNotNull(allDoctors);
        int count = 0;
        for (Doctor d : allDoctors) {
            count++;
        }
        assertEquals(2, count);
    }

    @Test
    @DisplayName("findAll returns empty iterable when no doctors exist")
    void findAll_noDoctors_returnsEmptyIterable() {
        // Act
        Iterable<Doctor> allDoctors = doctorRepo.findAll();

        // Assert
        assertNotNull(allDoctors);
        assertFalse(allDoctors.iterator().hasNext());
    }

    // ─── count() Tests ────────────────────────────────────────────────────────

    @Test
    @DisplayName("count returns 0 when no doctors exist")
    void count_noDoctors_returnsZero() {
        // Act
        long count = doctorRepo.count();

        // Assert
        assertEquals(0L, count);
    }

    @Test
    @DisplayName("count returns correct count after saving doctors")
    void count_afterSavingDoctors_returnsCorrectCount() {
        // Arrange
        doctorRepo.save(buildDoctor("sairam", "MD_Cardio", 90000.00));

        // Act
        long count = doctorRepo.count();

        // Assert
        assertEquals(1L, count);
    }

    @Test
    @DisplayName("count returns correct count after saving multiple doctors")
    void count_afterSavingMultipleDoctors_returnsCorrectCount() {
        // Arrange
        doctorRepo.save(buildDoctor("sairam", "MD_Cardio", 90000.00));
        doctorRepo.save(buildDoctor("Dr. Jane", "Neurology", 120000.00));
        doctorRepo.save(buildDoctor("Dr. Bob", "Orthopedics", 80000.00));

        // Act
        long count = doctorRepo.count();

        // Assert
        assertEquals(3L, count);
    }

    // ─── existsById() Tests ───────────────────────────────────────────────────

    @Test
    @DisplayName("existsById returns true for existing doctor id")
    void existsById_existingId_returnsTrue() {
        // Arrange
        Doctor savedDoctor = doctorRepo.save(buildDoctor("sairam", "MD_Cardio", 90000.00));
        Integer savedId = savedDoctor.getDocId();

        // Act
        boolean exists = doctorRepo.existsById(savedId);

        // Assert
        assertTrue(exists);
    }

    @Test
    @DisplayName("existsById returns false for non-existing doctor id")
    void existsById_nonExistingId_returnsFalse() {
        // Act
        boolean exists = doctorRepo.existsById(Integer.MAX_VALUE);

        // Assert
        assertFalse(exists);
    }

    // ─── delete() Tests ───────────────────────────────────────────────────────

    @Test
    @DisplayName("deleteById removes doctor from repository")
    void deleteById_existingId_removesDoctor() {
        // Arrange
        Doctor savedDoctor = doctorRepo.save(buildDoctor("sairam", "MD_Cardio", 90000.00));
        Integer savedId = savedDoctor.getDocId();

        // Act
        doctorRepo.deleteById(savedId);

        // Assert
        assertFalse(doctorRepo.existsById(savedId));
    }

    @Test
    @DisplayName("delete removes doctor from repository")
    void delete_existingDoctor_removesDoctor() {
        // Arrange
        Doctor savedDoctor = doctorRepo.save(buildDoctor("sairam", "MD_Cardio", 90000.00));

        // Act
        doctorRepo.delete(savedDoctor);

        // Assert
        assertEquals(0L, doctorRepo.count());
    }

    @Test
    @DisplayName("deleteAll removes all doctors from repository")
    void deleteAll_multipleDoctors_removesAllDoctors() {
        // Arrange
        doctorRepo.save(buildDoctor("sairam", "MD_Cardio", 90000.00));
        doctorRepo.save(buildDoctor("Dr. Jane", "Neurology", 120000.00));

        // Act
        doctorRepo.deleteAll();

        // Assert
        assertEquals(0L, doctorRepo.count());
    }

    // ─── Update Tests ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("save with existing id updates the doctor record")
    void save_existingId_updatesDoctor() {
        // Arrange
        Doctor savedDoctor = doctorRepo.save(buildDoctor("sairam", "MD_Cardio", 90000.00));
        Integer savedId = savedDoctor.getDocId();

        // Act
        savedDoctor.setDocName("UpdatedName");
        savedDoctor.setIncome(150000.00);
        doctorRepo.save(savedDoctor);

        // Assert
        Optional<Doctor> updated = doctorRepo.findById(savedId);
        assertTrue(updated.isPresent());
        assertEquals("UpdatedName", updated.get().getDocName());
        assertEquals(150000.00, updated.get().getIncome());
    }

    @Test
    @DisplayName("save multiple doctors increments count correctly")
    void save_multipleDoctors_countIncrementsCorrectly() {
        // Arrange & Act
        doctorRepo.save(buildDoctor("Doctor1", "Cardiology", 90000.00));
        long countAfterFirst = doctorRepo.count();

        doctorRepo.save(buildDoctor("Doctor2", "Neurology", 100000.00));
        long countAfterSecond = doctorRepo.count();

        // Assert
        assertEquals(1L, countAfterFirst);
        assertEquals(2L, countAfterSecond);
    }
}
