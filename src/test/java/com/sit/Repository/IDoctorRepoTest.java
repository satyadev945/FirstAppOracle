package com.sit.Repository;

import com.sit.entity.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for IDoctorRepo using @DataJpaTest with H2 in-memory database.
 * Tests all JpaRepository operations inherited by IDoctorRepo.
 */
@DataJpaTest
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
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

    private Doctor testDoctor;

    @BeforeEach
    void setUp() {
        doctorRepo.deleteAll();
        testDoctor = new Doctor();
        testDoctor.setDocName("sairam");
        testDoctor.setSpecialization("MD_Cardio");
        testDoctor.setIncome(90000.00);
    }

    // ─── save() Tests ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("save() persists doctor and returns saved entity with generated ID")
    void save_validDoctor_returnsSavedDoctorWithId() {
        // Act
        Doctor saved = doctorRepo.save(testDoctor);

        // Assert
        assertNotNull(saved);
        assertNotNull(saved.getDocId());
    }

    @Test
    @DisplayName("save() persists doctor name correctly")
    void save_validDoctor_persistsDocName() {
        // Act
        Doctor saved = doctorRepo.save(testDoctor);

        // Assert
        assertEquals("sairam", saved.getDocName());
    }

    @Test
    @DisplayName("save() persists specialization correctly")
    void save_validDoctor_persistsSpecialization() {
        // Act
        Doctor saved = doctorRepo.save(testDoctor);

        // Assert
        assertEquals("MD_Cardio", saved.getSpecialization());
    }

    @Test
    @DisplayName("save() persists income correctly")
    void save_validDoctor_persistsIncome() {
        // Act
        Doctor saved = doctorRepo.save(testDoctor);

        // Assert
        assertEquals(90000.00, saved.getIncome());
    }

    // ─── findById() Tests ─────────────────────────────────────────────────────

    @Test
    @DisplayName("findById() returns doctor when ID exists")
    void findById_existingId_returnsDoctor() {
        // Arrange
        Doctor saved = doctorRepo.save(testDoctor);

        // Act
        Optional<Doctor> found = doctorRepo.findById(saved.getDocId());

        // Assert
        assertTrue(found.isPresent());
    }

    @Test
    @DisplayName("findById() returns correct doctor for given ID")
    void findById_existingId_returnsCorrectDoctor() {
        // Arrange
        Doctor saved = doctorRepo.save(testDoctor);

        // Act
        Optional<Doctor> found = doctorRepo.findById(saved.getDocId());

        // Assert
        assertTrue(found.isPresent());
        assertEquals("sairam", found.get().getDocName());
    }

    @Test
    @DisplayName("findById() returns empty Optional for non-existing ID")
    void findById_nonExistingId_returnsEmptyOptional() {
        // Act
        Optional<Doctor> found = doctorRepo.findById(99999);

        // Assert
        assertFalse(found.isPresent());
    }

    // ─── findAll() Tests ──────────────────────────────────────────────────────

    @Test
    @DisplayName("findAll() returns empty list when no doctors saved")
    void findAll_noDoctors_returnsEmptyList() {
        // Act
        List<Doctor> doctors = doctorRepo.findAll();

        // Assert
        assertTrue(doctors.isEmpty());
    }

    @Test
    @DisplayName("findAll() returns all saved doctors")
    void findAll_multipleDoctors_returnsAllDoctors() {
        // Arrange
        Doctor doc2 = new Doctor();
        doc2.setDocName("Alice");
        doc2.setSpecialization("Neurology");
        doc2.setIncome(110000.0);

        doctorRepo.save(testDoctor);
        doctorRepo.save(doc2);

        // Act
        List<Doctor> doctors = doctorRepo.findAll();

        // Assert
        assertEquals(2, doctors.size());
    }

    // ─── delete() Tests ───────────────────────────────────────────────────────

    @Test
    @DisplayName("delete() removes doctor from repository")
    void delete_existingDoctor_removesFromRepo() {
        // Arrange
        Doctor saved = doctorRepo.save(testDoctor);

        // Act
        doctorRepo.delete(saved);

        // Assert
        Optional<Doctor> found = doctorRepo.findById(saved.getDocId());
        assertFalse(found.isPresent());
    }

    @Test
    @DisplayName("deleteById() removes doctor by ID")
    void deleteById_existingId_removesDoctor() {
        // Arrange
        Doctor saved = doctorRepo.save(testDoctor);
        Integer savedId = saved.getDocId();

        // Act
        doctorRepo.deleteById(savedId);

        // Assert
        assertFalse(doctorRepo.findById(savedId).isPresent());
    }

    // ─── count() Tests ────────────────────────────────────────────────────────

    @Test
    @DisplayName("count() returns 0 when no doctors exist")
    void count_noDoctors_returnsZero() {
        // Act
        long count = doctorRepo.count();

        // Assert
        assertEquals(0L, count);
    }

    @Test
    @DisplayName("count() returns correct count after saving doctors")
    void count_afterSavingDoctors_returnsCorrectCount() {
        // Arrange
        doctorRepo.save(testDoctor);

        Doctor doc2 = new Doctor();
        doc2.setDocName("Bob");
        doc2.setSpecialization("Orthopedics");
        doc2.setIncome(95000.0);
        doctorRepo.save(doc2);

        // Act
        long count = doctorRepo.count();

        // Assert
        assertEquals(2L, count);
    }

    // ─── existsById() Tests ───────────────────────────────────────────────────

    @Test
    @DisplayName("existsById() returns true for existing doctor")
    void existsById_existingId_returnsTrue() {
        // Arrange
        Doctor saved = doctorRepo.save(testDoctor);

        // Act
        boolean exists = doctorRepo.existsById(saved.getDocId());

        // Assert
        assertTrue(exists);
    }

    @Test
    @DisplayName("existsById() returns false for non-existing ID")
    void existsById_nonExistingId_returnsFalse() {
        // Act
        boolean exists = doctorRepo.existsById(99999);

        // Assert
        assertFalse(exists);
    }

    // ─── update (save existing) Tests ─────────────────────────────────────────

    @Test
    @DisplayName("save() updates existing doctor when ID is set")
    void save_existingDoctor_updatesRecord() {
        // Arrange
        Doctor saved = doctorRepo.save(testDoctor);
        saved.setDocName("UpdatedName");
        saved.setIncome(100000.0);

        // Act
        Doctor updated = doctorRepo.save(saved);

        // Assert
        assertEquals("UpdatedName", updated.getDocName());
        assertEquals(100000.0, updated.getIncome());
    }
}
