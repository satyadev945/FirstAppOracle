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
@DisplayName("DoctorMgmtServiceImpl Tests")
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
    @DisplayName("registerDoctor returns success message with doctor ID")
    void registerDoctor_withValidDoctor_shouldReturnSuccessMessage() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("203"));
    }

    @Test
    @DisplayName("registerDoctor returns message containing expected prefix")
    void registerDoctor_withValidDoctor_shouldReturnMessageWithPrefix() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertTrue(result.startsWith("Doctor obj is saved with id value :"));
    }

    @Test
    @DisplayName("registerDoctor returns exact expected message")
    void registerDoctor_withValidDoctor_shouldReturnExactMessage() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertEquals("Doctor obj is saved with id value :203", result);
    }

    @Test
    @DisplayName("registerDoctor calls doctorRepo.save exactly once")
    void registerDoctor_shouldCallRepoSaveExactlyOnce() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        doctorMgmtService.registerDoctor(doctor);

        // Assert
        verify(doctorRepo, times(1)).save(doctor);
    }

    @Test
    @DisplayName("registerDoctor passes the correct doctor object to repo")
    void registerDoctor_shouldPassCorrectDoctorToRepo() {
        // Arrange
        when(doctorRepo.save(doctor)).thenReturn(doctor);

        // Act
        doctorMgmtService.registerDoctor(doctor);

        // Assert
        verify(doctorRepo).save(doctor);
    }

    @Test
    @DisplayName("registerDoctor with different doctor ID returns correct message")
    void registerDoctor_withDifferentDoctorId_shouldReturnCorrectMessage() {
        // Arrange
        Doctor anotherDoctor = new Doctor();
        anotherDoctor.setDocId(500);
        anotherDoctor.setDocName("Dr. Jane");
        anotherDoctor.setSpecialization("Neurology");
        anotherDoctor.setIncome(120000.00);

        when(doctorRepo.save(any(Doctor.class))).thenReturn(anotherDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(anotherDoctor);

        // Assert
        assertEquals("Doctor obj is saved with id value :500", result);
    }

    @Test
    @DisplayName("registerDoctor with doctor having null ID returns message with null")
    void registerDoctor_withNullDocId_shouldReturnMessageWithNull() {
        // Arrange
        Doctor doctorWithNullId = new Doctor();
        doctorWithNullId.setDocName("Unknown");
        doctorWithNullId.setSpecialization("General");
        doctorWithNullId.setIncome(50000.00);
        // docId is null

        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctorWithNullId);

        // Act
        String result = doctorMgmtService.registerDoctor(doctorWithNullId);

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("null"));
    }

    @Test
    @DisplayName("registerDoctor propagates exception from repo")
    void registerDoctor_whenRepoThrowsException_shouldPropagateException() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class)))
                .thenThrow(new RuntimeException("Database connection failed"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> doctorMgmtService.registerDoctor(doctor));

        assertEquals("Database connection failed", exception.getMessage());
    }

    @Test
    @DisplayName("registerDoctor does not call repo more than once")
    void registerDoctor_shouldNotCallRepoMoreThanOnce() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        doctorMgmtService.registerDoctor(doctor);

        // Assert
        verify(doctorRepo, atMostOnce()).save(any(Doctor.class));
        verifyNoMoreInteractions(doctorRepo);
    }

    @Test
    @DisplayName("registerDoctor with minimum income doctor returns correct message")
    void registerDoctor_withMinimumIncomeDoctor_shouldReturnCorrectMessage() {
        // Arrange
        Doctor minIncomeDoctor = new Doctor();
        minIncomeDoctor.setDocId(1);
        minIncomeDoctor.setDocName("Junior Doc");
        minIncomeDoctor.setSpecialization("General");
        minIncomeDoctor.setIncome(0.0);

        when(doctorRepo.save(any(Doctor.class))).thenReturn(minIncomeDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(minIncomeDoctor);

        // Assert
        assertEquals("Doctor obj is saved with id value :1", result);
    }

    @Test
    @DisplayName("registerDoctor returns non-empty string")
    void registerDoctor_shouldReturnNonEmptyString() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("DoctorMgmtServiceImpl implements IDoctorService interface")
    void doctorMgmtServiceImpl_shouldImplementIDoctorService() {
        // Assert
        assertTrue(doctorMgmtService instanceof IDoctorService);
    }
}
