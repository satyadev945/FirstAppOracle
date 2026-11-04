package com.sit.Repository;

import com.sit.entity.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for IDoctorRepo using H2 in-memory database.
 * Tests all CrudRepository operations inherited by IDoctorRepo.
 */
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
class IDoctorRepoTest {

    @Autowired
    private IDoctorRepo doctorRepo;

    private Doctor sampleDoctor;

    @BeforeEach
    void setUp() {
        doctorRepo.deleteAll();

        sampleDoctor = new Doctor();
        sampleDoctor.setDocName("sairam");
        sampleDoctor.setSpecialization("MD_Cardio");
        sampleDoctor.setIncome(90000.00);
    }

    // ─── save ────────────────────────────────────────────────────────────────

    @Test
    void save_withValidDoctor_persistsAndReturnsWithId() {
        // Act
        Doctor saved = doctorRepo.save(sampleDoctor);

        // Assert
        assertNotNull(saved);
        assertNotNull(saved.getDocId());
    }

    @Test
    void save_withValidDoctor_persistsDocName() {
        // Act
        Doctor saved = doctorRepo.save(sampleDoctor);

        // Assert
        assertEquals("sairam", saved.getDocName());
    }

    @Test
    void save_withValidDoctor_persistsSpecialization() {
        // Act
        Doctor saved = doctorRepo.save(sampleDoctor);

        // Assert
        assertEquals("MD_Cardio", saved.getSpecialization());
    }

    @Test
    void save_withValidDoctor_persistsIncome() {
        // Act
        Doctor saved = doctorRepo.save(sampleDoctor);

        // Assert
        assertEquals(90000.00, saved.getIncome());
    }

    // ─── findById ────────────────────────────────────────────────────────────

    @Test
    void findById_withExistingId_returnsDoctor() {
        // Arrange
        Doctor saved = doctorRepo.save(sampleDoctor);

        // Act
        Optional<Doctor> found = doctorRepo.findById(saved.getDocId());

        // Assert
        assertTrue(found.isPresent());
        assertEquals(saved.getDocId(), found.get().getDocId());
    }

    @Test
    void findById_withNonExistingId_returnsEmpty() {
        // Act
        Optional<Doctor> found = doctorRepo.findById(Integer.MAX_VALUE);

        // Assert
        assertFalse(found.isPresent());
    }

    // ─── findAll ─────────────────────────────────────────────────────────────

    @Test
    void findAll_withNoDoctors_returnsEmptyIterable() {
        // Act
        Iterable<Doctor> all = doctorRepo.findAll();

        // Assert
        assertFalse(all.iterator().hasNext());
    }

    @Test
    void findAll_withOneSavedDoctor_returnsOneDoctor() {
        // Arrange
        doctorRepo.save(sampleDoctor);

        // Act
        Iterable<Doctor> all = doctorRepo.findAll();

        // Assert
        assertTrue(all.iterator().hasNext());
    }

    @Test
    void findAll_withMultipleDoctors_returnsAll() {
        // Arrange
        Doctor doc2 = new Doctor();
        doc2.setDocName("john");
        doc2.setSpecialization("Neuro");
        doc2.setIncome(75000.00);

        doctorRepo.save(sampleDoctor);
        doctorRepo.save(doc2);

        // Act
        long count = doctorRepo.count();

        // Assert
        assertEquals(2, count);
    }

    // ─── count ───────────────────────────────────────────────────────────────

    @Test
    void count_withNoRecords_returnsZero() {
        // Act & Assert
        assertEquals(0, doctorRepo.count());
    }

    @Test
    void count_afterSavingOneRecord_returnsOne() {
        // Arrange
        doctorRepo.save(sampleDoctor);

        // Act & Assert
        assertEquals(1, doctorRepo.count());
    }

    // ─── existsById ──────────────────────────────────────────────────────────

    @Test
    void existsById_withExistingId_returnsTrue() {
        // Arrange
        Doctor saved = doctorRepo.save(sampleDoctor);

        // Act & Assert
        assertTrue(doctorRepo.existsById(saved.getDocId()));
    }

    @Test
    void existsById_withNonExistingId_returnsFalse() {
        // Act & Assert
        assertFalse(doctorRepo.existsById(Integer.MAX_VALUE));
    }

    // ─── deleteById ──────────────────────────────────────────────────────────

    @Test
    void deleteById_withExistingId_removesRecord() {
        // Arrange
        Doctor saved = doctorRepo.save(sampleDoctor);
        Integer id = saved.getDocId();

        // Act
        doctorRepo.deleteById(id);

        // Assert
        assertFalse(doctorRepo.existsById(id));
    }

    @Test
    void deleteById_withExistingId_decreasesCount() {
        // Arrange
        Doctor saved = doctorRepo.save(sampleDoctor);

        // Act
        doctorRepo.deleteById(saved.getDocId());

        // Assert
        assertEquals(0, doctorRepo.count());
    }

    // ─── delete ──────────────────────────────────────────────────────────────

    @Test
    void delete_withExistingDoctor_removesRecord() {
        // Arrange
        Doctor saved = doctorRepo.save(sampleDoctor);

        // Act
        doctorRepo.delete(saved);

        // Assert
        assertEquals(0, doctorRepo.count());
    }

    // ─── deleteAll ───────────────────────────────────────────────────────────

    @Test
    void deleteAll_removesAllRecords() {
        // Arrange
        Doctor doc2 = new Doctor();
        doc2.setDocName("john");
        doc2.setSpecialization("Neuro");
        doc2.setIncome(75000.00);

        doctorRepo.save(sampleDoctor);
        doctorRepo.save(doc2);

        // Act
        doctorRepo.deleteAll();

        // Assert
        assertEquals(0, doctorRepo.count());
    }

    // ─── update (save existing) ───────────────────────────────────────────────

    @Test
    void save_existingDoctor_updatesRecord() {
        // Arrange
        Doctor saved = doctorRepo.save(sampleDoctor);
        saved.setDocName("updatedName");

        // Act
        Doctor updated = doctorRepo.save(saved);

        // Assert
        assertEquals("updatedName", updated.getDocName());
        assertEquals(1, doctorRepo.count());
    }
}
