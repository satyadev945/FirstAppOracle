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
 * Uses Mockito to mock IDoctorRepo and verifies service behaviour.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("DoctorMgmtServiceImpl Tests")
class DoctorMgmtServiceImplTest {

    @Mock
    private IDoctorRepo doctorRepo;

    @InjectMocks
    private DoctorMgmtServiceImpl doctorMgmtService;

    private Doctor sampleDoctor;

    @BeforeEach
    void setUp() {
        sampleDoctor = new Doctor();
        sampleDoctor.setDocId(203);
        sampleDoctor.setDocName("sairam");
        sampleDoctor.setSpecialization("MD_Cardio");
        sampleDoctor.setIncome(90000.00);
    }

    // ─── registerDoctor Tests ─────────────────────────────────────────────────

    @Test
    @DisplayName("registerDoctor returns success message with saved doctor id")
    void testRegisterDoctor_returnsSuccessMessageWithId() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(sampleDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(sampleDoctor);

        // Assert
        assertNotNull(result, "Result message should not be null");
        assertTrue(result.contains("203"),
                "Result message should contain the saved doctor's id");
    }

    @Test
    @DisplayName("registerDoctor returns expected message format")
    void testRegisterDoctor_returnsExpectedMessageFormat() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(sampleDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(sampleDoctor);

        // Assert
        assertEquals("Doctor obj is saved with id value :203", result,
                "Result message should match expected format");
    }

    @Test
    @DisplayName("registerDoctor calls doctorRepo.save exactly once")
    void testRegisterDoctor_callsRepoSaveOnce() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(sampleDoctor);

        // Act
        doctorMgmtService.registerDoctor(sampleDoctor);

        // Assert
        verify(doctorRepo, times(1)).save(sampleDoctor);
    }

    @Test
    @DisplayName("registerDoctor passes the correct Doctor object to repository")
    void testRegisterDoctor_passesCorrectDoctorToRepo() {
        // Arrange
        when(doctorRepo.save(sampleDoctor)).thenReturn(sampleDoctor);

        // Act
        doctorMgmtService.registerDoctor(sampleDoctor);

        // Assert
        verify(doctorRepo).save(sampleDoctor);
    }

    @Test
    @DisplayName("registerDoctor with different doctor id returns correct message")
    void testRegisterDoctor_withDifferentDoctorId_returnsCorrectMessage() {
        // Arrange
        Doctor anotherDoctor = new Doctor();
        anotherDoctor.setDocId(999);
        anotherDoctor.setDocName("Dr. Jane");
        anotherDoctor.setSpecialization("Neurology");
        anotherDoctor.setIncome(150000.00);

        when(doctorRepo.save(any(Doctor.class))).thenReturn(anotherDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(anotherDoctor);

        // Assert
        assertEquals("Doctor obj is saved with id value :999", result,
                "Result message should contain the correct doctor id");
    }

    @Test
    @DisplayName("registerDoctor with doctor having null id returns message with null")
    void testRegisterDoctor_withNullDocId_returnsMessageWithNull() {
        // Arrange
        Doctor doctorWithNullId = new Doctor();
        doctorWithNullId.setDocName("Unknown");
        doctorWithNullId.setSpecialization("General");
        doctorWithNullId.setIncome(40000.00);
        // docId is null

        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctorWithNullId);

        // Act
        String result = doctorMgmtService.registerDoctor(doctorWithNullId);

        // Assert
        assertNotNull(result, "Result should not be null even when docId is null");
        assertTrue(result.startsWith("Doctor obj is saved with id value :"),
                "Result should start with expected prefix");
    }

    @Test
    @DisplayName("registerDoctor propagates RuntimeException from repository")
    void testRegisterDoctor_whenRepoThrowsException_propagatesException() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class)))
                .thenThrow(new RuntimeException("Database connection failed"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> doctorMgmtService.registerDoctor(sampleDoctor),
                "Should propagate RuntimeException from repository");

        assertEquals("Database connection failed", exception.getMessage());
    }

    @Test
    @DisplayName("registerDoctor does not call repo.save more than once")
    void testRegisterDoctor_doesNotCallSaveMoreThanOnce() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(sampleDoctor);

        // Act
        doctorMgmtService.registerDoctor(sampleDoctor);

        // Assert
        verify(doctorRepo, atMostOnce()).save(any(Doctor.class));
    }

    @Test
    @DisplayName("registerDoctor result is non-empty string")
    void testRegisterDoctor_resultIsNonEmptyString() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(sampleDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(sampleDoctor);

        // Assert
        assertFalse(result.isEmpty(), "Result message should not be empty");
    }

    @Test
    @DisplayName("registerDoctor with minimum income doctor saves successfully")
    void testRegisterDoctor_withMinimumIncomeDoctor_savesSuccessfully() {
        // Arrange
        Doctor minIncomeDoctor = new Doctor();
        minIncomeDoctor.setDocId(300);
        minIncomeDoctor.setDocName("Dr. Min");
        minIncomeDoctor.setSpecialization("General");
        minIncomeDoctor.setIncome(0.0);

        when(doctorRepo.save(any(Doctor.class))).thenReturn(minIncomeDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(minIncomeDoctor);

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("300"));
    }

    @Test
    @DisplayName("registerDoctor verifies no other interactions with repo")
    void testRegisterDoctor_noOtherRepoInteractions() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(sampleDoctor);

        // Act
        doctorMgmtService.registerDoctor(sampleDoctor);

        // Assert
        verify(doctorRepo, times(1)).save(any(Doctor.class));
        verifyNoMoreInteractions(doctorRepo);
    }

    // ─── Service implements IDoctorService ───────────────────────────────────

    @Test
    @DisplayName("DoctorMgmtServiceImpl implements IDoctorService interface")
    void testDoctorMgmtServiceImpl_implementsIDoctorService() {
        // Assert
        assertTrue(doctorMgmtService instanceof IDoctorService,
                "DoctorMgmtServiceImpl should implement IDoctorService");
    }
}
