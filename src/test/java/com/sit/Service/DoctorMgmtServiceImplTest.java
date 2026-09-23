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
        doctor.setDocId(101);
        doctor.setDocName("sairam");
        doctor.setSpecialization("MD_Cardio");
        doctor.setIncome(90000.00);
    }

    // ─── registerDoctor Tests ─────────────────────────────────────────────────

    @Test
    @DisplayName("registerDoctor returns success message with doctor ID")
    void testRegisterDoctor_validDoctor_returnsSuccessMessage() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertNotNull(result);
        assertEquals("Doctor obj is saved with id value :101", result);
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
    @DisplayName("registerDoctor passes the correct doctor object to repository")
    void testRegisterDoctor_passesCorrectDoctorToRepo() {
        // Arrange
        when(doctorRepo.save(doctor)).thenReturn(doctor);

        // Act
        doctorMgmtService.registerDoctor(doctor);

        // Assert
        verify(doctorRepo).save(doctor);
    }

    @Test
    @DisplayName("registerDoctor returns message containing correct ID for different doctor")
    void testRegisterDoctor_differentDoctorId_returnsCorrectMessage() {
        // Arrange
        Doctor anotherDoctor = new Doctor();
        anotherDoctor.setDocId(205);
        anotherDoctor.setDocName("Dr. Alice");
        anotherDoctor.setSpecialization("Neurology");
        anotherDoctor.setIncome(120000.00);

        when(doctorRepo.save(any(Doctor.class))).thenReturn(anotherDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(anotherDoctor);

        // Assert
        assertNotNull(result);
        assertEquals("Doctor obj is saved with id value :205", result);
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
    @DisplayName("registerDoctor result contains the doctor ID as string")
    void testRegisterDoctor_resultContainsDoctorId() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertTrue(result.contains(String.valueOf(doctor.getDocId())));
    }

    @Test
    @DisplayName("registerDoctor with doctor havingId 203 returns correct message")
    void testRegisterDoctor_doctorWithId203_returnsCorrectMessage() {
        // Arrange
        Doctor doc203 = new Doctor();
        doc203.setDocId(203);
        doc203.setDocName("sairam");
        doc203.setSpecialization("MD_Cardio");
        doc203.setIncome(90000.00);

        when(doctorRepo.save(any(Doctor.class))).thenReturn(doc203);

        // Act
        String result = doctorMgmtService.registerDoctor(doc203);

        // Assert
        assertEquals("Doctor obj is saved with id value :203", result);
    }

    @Test
    @DisplayName("registerDoctor with null doctor name still processes correctly")
    void testRegisterDoctor_doctorWithNullName_processesCorrectly() {
        // Arrange
        Doctor docNullName = new Doctor();
        docNullName.setDocId(300);
        docNullName.setDocName(null);
        docNullName.setSpecialization("General");
        docNullName.setIncome(50000.00);

        when(doctorRepo.save(any(Doctor.class))).thenReturn(docNullName);

        // Act
        String result = doctorMgmtService.registerDoctor(docNullName);

        // Assert
        assertNotNull(result);
        assertEquals("Doctor obj is saved with id value :300", result);
    }

    @Test
    @DisplayName("registerDoctor propagates exception from repository")
    void testRegisterDoctor_repoThrowsException_propagatesException() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> doctorMgmtService.registerDoctor(doctor));
    }

    @Test
    @DisplayName("registerDoctor does not call repo more than once")
    void testRegisterDoctor_doesNotCallRepoMoreThanOnce() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        doctorMgmtService.registerDoctor(doctor);

        // Assert
        verify(doctorRepo, atMostOnce()).save(any(Doctor.class));
    }

    @Test
    @DisplayName("registerDoctor with zero income doctor returns correct message")
    void testRegisterDoctor_doctorWithZeroIncome_returnsCorrectMessage() {
        // Arrange
        Doctor zeroIncomeDoc = new Doctor();
        zeroIncomeDoc.setDocId(400);
        zeroIncomeDoc.setDocName("Dr. Zero");
        zeroIncomeDoc.setSpecialization("Intern");
        zeroIncomeDoc.setIncome(0.0);

        when(doctorRepo.save(any(Doctor.class))).thenReturn(zeroIncomeDoc);

        // Act
        String result = doctorMgmtService.registerDoctor(zeroIncomeDoc);

        // Assert
        assertEquals("Doctor obj is saved with id value :400", result);
    }

    @Test
    @DisplayName("registerDoctor returns non-empty string")
    void testRegisterDoctor_returnsNonEmptyString() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("registerDoctor with multiple doctors saves each correctly")
    void testRegisterDoctor_multipleDoctors_eachSavedCorrectly() {
        // Arrange
        Doctor doc1 = new Doctor();
        doc1.setDocId(501);
        doc1.setDocName("Dr. One");

        Doctor doc2 = new Doctor();
        doc2.setDocId(502);
        doc2.setDocName("Dr. Two");

        when(doctorRepo.save(doc1)).thenReturn(doc1);
        when(doctorRepo.save(doc2)).thenReturn(doc2);

        // Act
        String result1 = doctorMgmtService.registerDoctor(doc1);
        String result2 = doctorMgmtService.registerDoctor(doc2);

        // Assert
        assertEquals("Doctor obj is saved with id value :501", result1);
        assertEquals("Doctor obj is saved with id value :502", result2);
        verify(doctorRepo, times(1)).save(doc1);
        verify(doctorRepo, times(1)).save(doc2);
    }
}
