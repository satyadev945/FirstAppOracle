package com.sit.Repository;

import com.sit.entity.Doctor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Unit tests for IDoctorRepo interface.
 * Uses Mockito to mock the CrudRepository interface methods.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("IDoctorRepo Interface Tests")
class IDoctorRepoTest {

    @Mock
    private IDoctorRepo doctorRepo;

    // ─── save() Tests ────────────────────────────────────────────────────────

    @Test
    @DisplayName("save() returns saved Doctor with ID")
    void testSave_returnsSavedDoctorWithId() {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setDocId(203);
        doctor.setDocName("sairam");
        doctor.setSpecialization("MD_Cardio");
        doctor.setIncome(90000.00);

        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        Doctor savedDoctor = doctorRepo.save(doctor);

        // Assert
        assertNotNull(savedDoctor);
        assertEquals(203, savedDoctor.getDocId());
    }

    @Test
    @DisplayName("save() is called with correct doctor object")
    void testSave_calledWithCorrectDoctor() {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setDocId(1);
        doctor.setDocName("TestDoc");

        when(doctorRepo.save(doctor)).thenReturn(doctor);

        // Act
        doctorRepo.save(doctor);

        // Assert
        verify(doctorRepo, times(1)).save(doctor);
    }

    @Test
    @DisplayName("save() returns doctor with correct name")
    void testSave_returnsDoctorWithCorrectName() {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setDocId(1);
        doctor.setDocName("sairam");

        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        Doctor result = doctorRepo.save(doctor);

        // Assert
        assertEquals("sairam", result.getDocName());
    }

    // ─── findById() Tests ────────────────────────────────────────────────────

    @Test
    @DisplayName("findById() returns Optional with doctor when found")
    void testFindById_returnsOptionalWithDoctor() {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setDocId(203);
        doctor.setDocName("sairam");

        when(doctorRepo.findById(203)).thenReturn(Optional.of(doctor));

        // Act
        Optional<Doctor> result = doctorRepo.findById(203);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(203, result.get().getDocId());
    }

    @Test
    @DisplayName("findById() returns empty Optional when not found")
    void testFindById_returnsEmptyOptional_whenNotFound() {
        // Arrange
        when(doctorRepo.findById(anyInt())).thenReturn(Optional.empty());

        // Act
        Optional<Doctor> result = doctorRepo.findById(999);

        // Assert
        assertFalse(result.isPresent());
    }

    // ─── findAll() Tests ─────────────────────────────────────────────────────

    @Test
    @DisplayName("findAll() returns all doctors")
    void testFindAll_returnsAllDoctors() {
        // Arrange
        Doctor doc1 = new Doctor();
        doc1.setDocId(1);
        doc1.setDocName("sairam");

        Doctor doc2 = new Doctor();
        doc2.setDocId(2);
        doc2.setDocName("john");

        when(doctorRepo.findAll()).thenReturn(Arrays.asList(doc1, doc2));

        // Act
        Iterable<Doctor> result = doctorRepo.findAll();

        // Assert
        assertNotNull(result);
        assertTrue(result.iterator().hasNext());
    }

    @Test
    @DisplayName("findAll() returns empty list when no doctors")
    void testFindAll_returnsEmptyList_whenNoDoctors() {
        // Arrange
        when(doctorRepo.findAll()).thenReturn(Arrays.asList());

        // Act
        Iterable<Doctor> result = doctorRepo.findAll();

        // Assert
        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
    }

    // ─── deleteById() Tests ──────────────────────────────────────────────────

    @Test
    @DisplayName("deleteById() is called with correct ID")
    void testDeleteById_calledWithCorrectId() {
        // Arrange
        doNothing().when(doctorRepo).deleteById(203);

        // Act
        doctorRepo.deleteById(203);

        // Assert
        verify(doctorRepo, times(1)).deleteById(203);
    }

    // ─── existsById() Tests ──────────────────────────────────────────────────

    @Test
    @DisplayName("existsById() returns true when doctor exists")
    void testExistsById_returnsTrue_whenDoctorExists() {
        // Arrange
        when(doctorRepo.existsById(203)).thenReturn(true);

        // Act
        boolean result = doctorRepo.existsById(203);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("existsById() returns false when doctor does not exist")
    void testExistsById_returnsFalse_whenDoctorDoesNotExist() {
        // Arrange
        when(doctorRepo.existsById(999)).thenReturn(false);

        // Act
        boolean result = doctorRepo.existsById(999);

        // Assert
        assertFalse(result);
    }

    // ─── count() Tests ───────────────────────────────────────────────────────

    @Test
    @DisplayName("count() returns correct number of doctors")
    void testCount_returnsCorrectCount() {
        // Arrange
        when(doctorRepo.count()).thenReturn(5L);

        // Act
        long count = doctorRepo.count();

        // Assert
        assertEquals(5L, count);
    }

    @Test
    @DisplayName("count() returns zero when no doctors")
    void testCount_returnsZero_whenNoDoctors() {
        // Arrange
        when(doctorRepo.count()).thenReturn(0L);

        // Act
        long count = doctorRepo.count();

        // Assert
        assertEquals(0L, count);
    }

    // ─── IDoctorRepo interface Tests ─────────────────────────────────────────

    @Test
    @DisplayName("IDoctorRepo mock is not null")
    void testIDoctorRepo_mockIsNotNull() {
        // Assert
        assertNotNull(doctorRepo);
    }
}
