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

@ExtendWith(MockitoExtension.class)
@DisplayName("DoctorMgmtServiceImpl Tests")
class DoctorMgmtServiceImplTest {

    @Mock
    private IDoctorRepo doctorRepo;

    @InjectMocks
    private DoctorMgmtServiceImpl doctorMgmtServiceImpl;

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
        doctor.setDocId(203);
        doctor.setDocName("sairam");
        doctor.setSpecialization("MD_Cardio");
        doctor.setIncome(90000.00);
    }

    // ─── registerDoctor Tests ────────────────────────────────────────────────

    @Test
    @DisplayName("registerDoctor returns success message with saved doctor id")
    void registerDoctor_validDoctor_returnsSuccessMessage() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtServiceImpl.registerDoctor(doctor);

        // Assert
        assertNotNull(result);
        assertEquals("Doctor obj is saved with id value :203", result);
    }

    @Test
    @DisplayName("registerDoctor calls repository save exactly once")
    void registerDoctor_validDoctor_callsRepoSaveOnce() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        doctorMgmtServiceImpl.registerDoctor(doctor);

        // Assert
        verify(doctorRepo, times(1)).save(doctor);
    }

    @Test
    @DisplayName("registerDoctor result contains correct id from saved entity")
    void registerDoctor_savedDoctorWithId_resultContainsId() {
        // Arrange
        Doctor savedDoctor = new Doctor();
        savedDoctor.setDocId(999);
        savedDoctor.setDocName("TestDoc");
        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoctor);

        // Act
        String result = doctorMgmtServiceImpl.registerDoctor(doctor);

        // Assert
        assertTrue(result.contains("999"));
    }

    @Test
    @DisplayName("registerDoctor result starts with expected prefix")
    void registerDoctor_validDoctor_resultStartsWithExpectedPrefix() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtServiceImpl.registerDoctor(doctor);

        // Assert
        assertTrue(result.startsWith("Doctor obj is saved with id value :"));
    }

    @Test
    @DisplayName("registerDoctor with different doctor id returns correct message")
    void registerDoctor_differentDoctorId_returnsCorrectMessage() {
        // Arrange
        Doctor anotherDoctor = new Doctor();
        anotherDoctor.setDocId(500);
        anotherDoctor.setDocName("Dr. Jones");
        anotherDoctor.setSpecialization("Neurology");
        anotherDoctor.setIncome(120000.00);
        when(doctorRepo.save(any(Doctor.class))).thenReturn(anotherDoctor);

        // Act
        String result = doctorMgmtServiceImpl.registerDoctor(anotherDoctor);

        // Assert
        assertEquals("Doctor obj is saved with id value :500", result);
    }

    @Test
    @DisplayName("registerDoctor passes the correct doctor object to repository")
    void registerDoctor_validDoctor_passesCorrectObjectToRepo() {
        // Arrange
        when(doctorRepo.save(doctor)).thenReturn(doctor);

        // Act
        doctorMgmtServiceImpl.registerDoctor(doctor);

        // Assert
        verify(doctorRepo).save(doctor);
    }

    @Test
    @DisplayName("registerDoctor with null doctor id in saved entity returns message with null")
    void registerDoctor_savedDoctorWithNullId_returnsMessageWithNull() {
        // Arrange
        Doctor savedDoctorNullId = new Doctor();
        savedDoctorNullId.setDocId(null);
        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoctorNullId);

        // Act
        String result = doctorMgmtServiceImpl.registerDoctor(doctor);

        // Assert
        assertNotNull(result);
        assertEquals("Doctor obj is saved with id value :null", result);
    }

    @Test
    @DisplayName("registerDoctor when repo throws exception propagates exception")
    void registerDoctor_repoThrowsException_propagatesException() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenThrow(new RuntimeException("DB error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> doctorMgmtServiceImpl.registerDoctor(doctor));
    }

    @Test
    @DisplayName("registerDoctor does not call any other repo method")
    void registerDoctor_validDoctor_noOtherRepoInteractions() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        doctorMgmtServiceImpl.registerDoctor(doctor);

        // Assert
        verify(doctorRepo, times(1)).save(any(Doctor.class));
        verifyNoMoreInteractions(doctorRepo);
    }

    @Test
    @DisplayName("registerDoctor with minimum income doctor returns correct message")
    void registerDoctor_minimumIncomeDoctor_returnsCorrectMessage() {
        // Arrange
        Doctor minIncomeDoc = new Doctor();
        minIncomeDoc.setDocId(1);
        minIncomeDoc.setDocName("Dr. Min");
        minIncomeDoc.setSpecialization("GP");
        minIncomeDoc.setIncome(0.0);
        when(doctorRepo.save(any(Doctor.class))).thenReturn(minIncomeDoc);

        // Act
        String result = doctorMgmtServiceImpl.registerDoctor(minIncomeDoc);

        // Assert
        assertEquals("Doctor obj is saved with id value :1", result);
    }

    @Test
    @DisplayName("registerDoctor implements IDoctorService interface")
    void doctorMgmtServiceImpl_implementsIDoctorService() {
        // Assert
        assertTrue(doctorMgmtServiceImpl instanceof IDoctorService);
    }
}
