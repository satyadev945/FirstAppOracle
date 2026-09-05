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

    private Doctor inputDoctor;
    private Doctor savedDoctor;

    @BeforeEach
    void setUp() {
        inputDoctor = new Doctor();
        inputDoctor.setDocName("sairam");
        inputDoctor.setSpecialization("MD_Cardio");
        inputDoctor.setIncome(90000.00);

        savedDoctor = new Doctor();
        savedDoctor.setDocId(203);
        savedDoctor.setDocName("sairam");
        savedDoctor.setSpecialization("MD_Cardio");
        savedDoctor.setIncome(90000.00);
    }

    // ─── registerDoctor() Tests ───────────────────────────────────────────────

    @Test
    @DisplayName("registerDoctor returns success message with saved doctor ID")
    void registerDoctor_validDoctor_returnsSuccessMessageWithId() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(inputDoctor);

        // Assert
        assertNotNull(result);
        assertEquals("Doctor obj is saved with id value :203", result);
    }

    @Test
    @DisplayName("registerDoctor calls repository save exactly once")
    void registerDoctor_validDoctor_callsRepoSaveOnce() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoctor);

        // Act
        doctorMgmtService.registerDoctor(inputDoctor);

        // Assert
        verify(doctorRepo, times(1)).save(inputDoctor);
    }

    @Test
    @DisplayName("registerDoctor passes the correct doctor object to repository")
    void registerDoctor_validDoctor_passesCorrectDoctorToRepo() {
        // Arrange
        when(doctorRepo.save(inputDoctor)).thenReturn(savedDoctor);

        // Act
        doctorMgmtService.registerDoctor(inputDoctor);

        // Assert
        verify(doctorRepo).save(inputDoctor);
    }

    @Test
    @DisplayName("registerDoctor result contains correct ID from saved entity")
    void registerDoctor_savedDoctorHasId_resultContainsId() {
        // Arrange
        Doctor docWithDifferentId = new Doctor();
        docWithDifferentId.setDocId(999);
        docWithDifferentId.setDocName("Test");
        when(doctorRepo.save(any(Doctor.class))).thenReturn(docWithDifferentId);

        // Act
        String result = doctorMgmtService.registerDoctor(inputDoctor);

        // Assert
        assertTrue(result.contains("999"));
    }

    @Test
    @DisplayName("registerDoctor result starts with expected prefix")
    void registerDoctor_validDoctor_resultStartsWithExpectedPrefix() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(inputDoctor);

        // Assert
        assertTrue(result.startsWith("Doctor obj is saved with id value :"));
    }

    @Test
    @DisplayName("registerDoctor with doctor havingId 1 returns correct message")
    void registerDoctor_doctorWithId1_returnsMessageWithId1() {
        // Arrange
        Doctor docId1 = new Doctor();
        docId1.setDocId(1);
        docId1.setDocName("Alice");
        docId1.setSpecialization("Pediatrics");
        docId1.setIncome(75000.0);
        when(doctorRepo.save(any(Doctor.class))).thenReturn(docId1);

        // Act
        String result = doctorMgmtService.registerDoctor(inputDoctor);

        // Assert
        assertEquals("Doctor obj is saved with id value :1", result);
    }

    @Test
    @DisplayName("registerDoctor with null doctor name still saves and returns message")
    void registerDoctor_doctorWithNullName_savesAndReturnsMessage() {
        // Arrange
        Doctor nullNameDoctor = new Doctor();
        nullNameDoctor.setDocId(204);
        nullNameDoctor.setDocName(null);
        when(doctorRepo.save(any(Doctor.class))).thenReturn(nullNameDoctor);

        Doctor inputNullName = new Doctor();
        inputNullName.setDocName(null);

        // Act
        String result = doctorMgmtService.registerDoctor(inputNullName);

        // Assert
        assertNotNull(result);
        assertEquals("Doctor obj is saved with id value :204", result);
    }

    @Test
    @DisplayName("registerDoctor when repo throws exception propagates exception")
    void registerDoctor_repoThrowsException_exceptionPropagates() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenThrow(new RuntimeException("DB connection failed"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> doctorMgmtService.registerDoctor(inputDoctor));
        assertEquals("DB connection failed", exception.getMessage());
    }

    @Test
    @DisplayName("registerDoctor result is not empty string")
    void registerDoctor_validDoctor_resultIsNotEmpty() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(inputDoctor);

        // Assert
        assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("registerDoctor with multiple doctors saves each independently")
    void registerDoctor_multipleDoctors_eachSavedIndependently() {
        // Arrange
        Doctor savedDoc1 = new Doctor();
        savedDoc1.setDocId(101);

        Doctor savedDoc2 = new Doctor();
        savedDoc2.setDocId(102);

        Doctor input1 = new Doctor();
        input1.setDocName("Doctor One");

        Doctor input2 = new Doctor();
        input2.setDocName("Doctor Two");

        when(doctorRepo.save(input1)).thenReturn(savedDoc1);
        when(doctorRepo.save(input2)).thenReturn(savedDoc2);

        // Act
        String result1 = doctorMgmtService.registerDoctor(input1);
        String result2 = doctorMgmtService.registerDoctor(input2);

        // Assert
        assertEquals("Doctor obj is saved with id value :101", result1);
        assertEquals("Doctor obj is saved with id value :102", result2);
        verify(doctorRepo, times(1)).save(input1);
        verify(doctorRepo, times(1)).save(input2);
    }

    // ─── Service Bean Tests ───────────────────────────────────────────────────

    @Test
    @DisplayName("DoctorMgmtServiceImpl implements IDoctorService interface")
    void serviceImpl_implementsIDoctorService() {
        // Assert
        assertTrue(doctorMgmtService instanceof IDoctorService);
    }

    @Test
    @DisplayName("DoctorMgmtServiceImpl instance is not null after injection")
    void serviceImpl_afterInjection_isNotNull() {
        // Assert
        assertNotNull(doctorMgmtService);
    }
}
