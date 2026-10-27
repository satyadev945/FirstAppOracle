package com.sit.Service;

import com.sit.Repository.IDoctorRepo;
import com.sit.entity.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for DoctorMgmtServiceImpl.
 * Uses Mockito to mock IDoctorRepo dependency.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("DoctorMgmtServiceImpl Service Tests")
class DoctorMgmtServiceImplTest {

    @Mock
    private IDoctorRepo doctorRepo;

    @InjectMocks
    private DoctorMgmtServiceImpl doctorMgmtService;

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
        doctor.setDocId(203);
        doctor.setDocName("sairam");
        doctor.setSpecialization("MD_Cardio");
        doctor.setIncome(90000.00);
    }

    // ─── registerDoctor Tests ─────────────────────────────────────────────────

    @Test
    @DisplayName("registerDoctor with valid doctor returns success message with docId")
    void registerDoctor_validDoctor_returnsSuccessMessageWithDocId() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertNotNull(result);
        assertEquals("Doctor obj is saved with id value :203", result);
    }

    @Test
    @DisplayName("registerDoctor calls doctorRepo.save exactly once")
    void registerDoctor_validDoctor_callsRepoSaveOnce() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        doctorMgmtService.registerDoctor(doctor);

        // Assert
        verify(doctorRepo, times(1)).save(doctor);
    }

    @Test
    @DisplayName("registerDoctor returns message containing docId")
    void registerDoctor_validDoctor_returnMessageContainsDocId() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertTrue(result.contains("203"));
    }

    @Test
    @DisplayName("registerDoctor returns message with correct prefix")
    void registerDoctor_validDoctor_returnMessageHasCorrectPrefix() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertTrue(result.startsWith("Doctor obj is saved with id value :"));
    }

    @Test
    @DisplayName("registerDoctor with different docId returns correct message")
    void registerDoctor_differentDocId_returnsCorrectMessage() {
        // Arrange
        Doctor anotherDoctor = new Doctor();
        anotherDoctor.setDocId(999);
        anotherDoctor.setDocName("Dr. Jane");
        anotherDoctor.setSpecialization("Neurology");
        anotherDoctor.setIncome(120000.00);
        when(doctorRepo.save(any(Doctor.class))).thenReturn(anotherDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(anotherDoctor);

        // Assert
        assertEquals("Doctor obj is saved with id value :999", result);
    }

    @Test
    @DisplayName("registerDoctor passes the correct doctor object to repository")
    void registerDoctor_validDoctor_passesCorrectDoctorToRepo() {
        // Arrange
        when(doctorRepo.save(doctor)).thenReturn(doctor);

        // Act
        doctorMgmtService.registerDoctor(doctor);

        // Assert
        verify(doctorRepo).save(doctor);
    }

    @Test
    @DisplayName("registerDoctor with doctor having docId 0 returns message with 0")
    void registerDoctor_doctorWithZeroId_returnsMessageWithZero() {
        // Arrange
        Doctor zeroIdDoctor = new Doctor();
        zeroIdDoctor.setDocId(0);
        zeroIdDoctor.setDocName("TestDoc");
        when(doctorRepo.save(any(Doctor.class))).thenReturn(zeroIdDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(zeroIdDoctor);

        // Assert
        assertEquals("Doctor obj is saved with id value :0", result);
    }

    @Test
    @DisplayName("registerDoctor when repo throws exception propagates exception")
    void registerDoctor_repoThrowsException_propagatesException() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> doctorMgmtService.registerDoctor(doctor));
    }

    @Test
    @DisplayName("registerDoctor when repo throws exception does not return result")
    void registerDoctor_repoThrowsException_noResultReturned() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenThrow(new RuntimeException("DB connection failed"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
            () -> doctorMgmtService.registerDoctor(doctor));
        assertEquals("DB connection failed", exception.getMessage());
    }

    @Test
    @DisplayName("registerDoctor result is not empty string")
    void registerDoctor_validDoctor_resultIsNotEmpty() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("registerDoctor with doctor having null docId returns message with null")
    void registerDoctor_doctorWithNullId_returnsMessageWithNull() {
        // Arrange
        Doctor nullIdDoctor = new Doctor();
        nullIdDoctor.setDocId(null);
        nullIdDoctor.setDocName("NullIdDoc");
        when(doctorRepo.save(any(Doctor.class))).thenReturn(nullIdDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(nullIdDoctor);

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("null"));
    }

    @Test
    @DisplayName("registerDoctor does not call any other repo methods")
    void registerDoctor_validDoctor_onlySaveIsCalled() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        doctorMgmtService.registerDoctor(doctor);

        // Assert
        verify(doctorRepo, times(1)).save(any(Doctor.class));
        verifyNoMoreInteractions(doctorRepo);
    }

    // ─── Service Implementation Tests ─────────────────────────────────────────

    @Test
    @DisplayName("DoctorMgmtServiceImpl implements IDoctorService interface")
    void doctorMgmtServiceImpl_implementsIDoctorService() {
        // Assert
        assertTrue(doctorMgmtService instanceof IDoctorService);
    }

    @Test
    @DisplayName("registerDoctor with multiple doctors saves each correctly")
    void registerDoctor_multipleDoctors_eachSavedCorrectly() {
        // Arrange
        Doctor doc1 = new Doctor();
        doc1.setDocId(1);
        doc1.setDocName("Doctor One");

        Doctor doc2 = new Doctor();
        doc2.setDocId(2);
        doc2.setDocName("Doctor Two");

        when(doctorRepo.save(doc1)).thenReturn(doc1);
        when(doctorRepo.save(doc2)).thenReturn(doc2);

        // Act
        String result1 = doctorMgmtService.registerDoctor(doc1);
        String result2 = doctorMgmtService.registerDoctor(doc2);

        // Assert
        assertEquals("Doctor obj is saved with id value :1", result1);
        assertEquals("Doctor obj is saved with id value :2", result2);
        verify(doctorRepo, times(2)).save(any(Doctor.class));
    }
}
