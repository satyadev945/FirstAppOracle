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

    // ─── registerDoctor Tests ────────────────────────────────────────────────

    @Test
    @DisplayName("registerDoctor returns success message with doctor ID")
    void testRegisterDoctor_returnsSuccessMessageWithId() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("203"));
    }

    @Test
    @DisplayName("registerDoctor returns correct message format")
    void testRegisterDoctor_returnsCorrectMessageFormat() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertEquals("Doctor obj is saved with id value :203", result);
    }

    @Test
    @DisplayName("registerDoctor calls doctorRepo.save exactly once")
    void testRegisterDoctor_callsRepoSaveOnce() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        doctorMgmtService.registerDoctor(doctor);

        // Assert
        verify(doctorRepo, times(1)).save(doctor);
    }

    @Test
    @DisplayName("registerDoctor passes the correct doctor object to repo")
    void testRegisterDoctor_passesCorrectDoctorToRepo() {
        // Arrange
        when(doctorRepo.save(doctor)).thenReturn(doctor);

        // Act
        doctorMgmtService.registerDoctor(doctor);

        // Assert
        verify(doctorRepo).save(doctor);
    }

    @Test
    @DisplayName("registerDoctor with different doctor ID returns correct message")
    void testRegisterDoctor_withDifferentId_returnsCorrectMessage() {
        // Arrange
        Doctor anotherDoctor = new Doctor();
        anotherDoctor.setDocId(999);
        anotherDoctor.setDocName("John");
        anotherDoctor.setSpecialization("Neurology");
        anotherDoctor.setIncome(120000.00);

        when(doctorRepo.save(any(Doctor.class))).thenReturn(anotherDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(anotherDoctor);

        // Assert
        assertEquals("Doctor obj is saved with id value :999", result);
    }

    @Test
    @DisplayName("registerDoctor result is not empty")
    void testRegisterDoctor_resultIsNotEmpty() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("registerDoctor result starts with expected prefix")
    void testRegisterDoctor_resultStartsWithExpectedPrefix() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertTrue(result.startsWith("Doctor obj is saved with id value :"));
    }

    @Test
    @DisplayName("registerDoctor with doctor having null ID returns message with null")
    void testRegisterDoctor_withNullId_returnsMessageWithNull() {
        // Arrange
        Doctor docWithNullId = new Doctor();
        docWithNullId.setDocId(null);
        docWithNullId.setDocName("TestDoc");

        when(doctorRepo.save(any(Doctor.class))).thenReturn(docWithNullId);

        // Act
        String result = doctorMgmtService.registerDoctor(docWithNullId);

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("null"));
    }

    @Test
    @DisplayName("registerDoctor propagates exception from repo")
    void testRegisterDoctor_whenRepoThrowsException_propagatesException() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenThrow(new RuntimeException("DB connection failed"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> doctorMgmtService.registerDoctor(doctor));
        assertEquals("DB connection failed", exception.getMessage());
    }

    @Test
    @DisplayName("registerDoctor with minimum doctor data")
    void testRegisterDoctor_withMinimumDoctorData() {
        // Arrange
        Doctor minDoctor = new Doctor();
        minDoctor.setDocId(1);

        when(doctorRepo.save(any(Doctor.class))).thenReturn(minDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(minDoctor);

        // Assert
        assertNotNull(result);
        assertEquals("Doctor obj is saved with id value :1", result);
    }

    @Test
    @DisplayName("registerDoctor verifies no other interactions with repo")
    void testRegisterDoctor_noOtherRepoInteractions() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        doctorMgmtService.registerDoctor(doctor);

        // Assert
        verify(doctorRepo, times(1)).save(any(Doctor.class));
        verifyNoMoreInteractions(doctorRepo);
    }

    @Test
    @DisplayName("registerDoctor with doctor having zero income")
    void testRegisterDoctor_withZeroIncome() {
        // Arrange
        Doctor zeroIncomeDoc = new Doctor();
        zeroIncomeDoc.setDocId(500);
        zeroIncomeDoc.setDocName("ZeroDoc");
        zeroIncomeDoc.setIncome(0.0);

        when(doctorRepo.save(any(Doctor.class))).thenReturn(zeroIncomeDoc);

        // Act
        String result = doctorMgmtService.registerDoctor(zeroIncomeDoc);

        // Assert
        assertEquals("Doctor obj is saved with id value :500", result);
    }

    @Test
    @DisplayName("registerDoctor with large doctor ID")
    void testRegisterDoctor_withLargeId_returnsCorrectMessage() {
        // Arrange
        Doctor largeIdDoc = new Doctor();
        largeIdDoc.setDocId(Integer.MAX_VALUE);
        largeIdDoc.setDocName("LargeIdDoc");

        when(doctorRepo.save(any(Doctor.class))).thenReturn(largeIdDoc);

        // Act
        String result = doctorMgmtService.registerDoctor(largeIdDoc);

        // Assert
        assertEquals("Doctor obj is saved with id value :" + Integer.MAX_VALUE, result);
    }

    @Test
    @DisplayName("registerDoctor called multiple times works correctly")
    void testRegisterDoctor_calledMultipleTimes_worksCorrectly() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result1 = doctorMgmtService.registerDoctor(doctor);
        String result2 = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertEquals(result1, result2);
        verify(doctorRepo, times(2)).save(any(Doctor.class));
    }

    // ─── Service implements IDoctorService ───────────────────────────────────

    @Test
    @DisplayName("DoctorMgmtServiceImpl implements IDoctorService")
    void testDoctorMgmtServiceImpl_implementsIDoctorService() {
        // Assert
        assertTrue(doctorMgmtService instanceof IDoctorService);
    }

    @Test
    @DisplayName("DoctorMgmtServiceImpl is not null after injection")
    void testDoctorMgmtServiceImpl_isNotNull() {
        // Assert
        assertNotNull(doctorMgmtService);
    }

    @Test
    @DisplayName("registerDoctor with doctor having negative income")
    void testRegisterDoctor_withNegativeIncome() {
        // Arrange
        Doctor negIncomeDoc = new Doctor();
        negIncomeDoc.setDocId(600);
        negIncomeDoc.setDocName("NegDoc");
        negIncomeDoc.setIncome(-5000.00);

        when(doctorRepo.save(any(Doctor.class))).thenReturn(negIncomeDoc);

        // Act
        String result = doctorMgmtService.registerDoctor(negIncomeDoc);

        // Assert
        assertEquals("Doctor obj is saved with id value :600", result);
    }

    @Test
    @DisplayName("registerDoctor result contains 'Doctor obj is saved'")
    void testRegisterDoctor_resultContainsExpectedText() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertTrue(result.contains("Doctor obj is saved"));
    }

    @Test
    @DisplayName("registerDoctor result contains 'id value'")
    void testRegisterDoctor_resultContainsIdValue() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertTrue(result.contains("id value"));
    }
}
