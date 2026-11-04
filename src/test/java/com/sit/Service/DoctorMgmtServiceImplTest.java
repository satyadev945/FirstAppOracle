package com.sit.Service;

import com.sit.Repository.IDoctorRepo;
import com.sit.entity.Doctor;
import org.junit.jupiter.api.BeforeEach;
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
class DoctorMgmtServiceImplTest {

    @Mock
    private IDoctorRepo doctorRepo;

    @InjectMocks
    private DoctorMgmtServiceImpl doctorMgmtService;

    private Doctor sampleDoctor;

    @BeforeEach
    void setUp() {
        sampleDoctor = new Doctor();
        sampleDoctor.setDocId(1);
        sampleDoctor.setDocName("sairam");
        sampleDoctor.setSpecialization("MD_Cardio");
        sampleDoctor.setIncome(90000.00);
    }

    // ─── registerDoctor – happy path ─────────────────────────────────────────

    @Test
    void registerDoctor_withValidDoctor_returnsSuccessMessage() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(sampleDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(sampleDoctor);

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("Doctor obj is saved with id value :"));
        assertTrue(result.contains("1"));
    }

    @Test
    void registerDoctor_withValidDoctor_messageContainsDocId() {
        // Arrange
        Doctor savedDoctor = new Doctor();
        savedDoctor.setDocId(203);
        savedDoctor.setDocName("sairam");
        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(sampleDoctor);

        // Assert
        assertEquals("Doctor obj is saved with id value :203", result);
    }

    @Test
    void registerDoctor_callsRepoSaveExactlyOnce() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(sampleDoctor);

        // Act
        doctorMgmtService.registerDoctor(sampleDoctor);

        // Assert
        verify(doctorRepo, times(1)).save(sampleDoctor);
    }

    @Test
    void registerDoctor_passesCorrectDoctorToRepo() {
        // Arrange
        when(doctorRepo.save(sampleDoctor)).thenReturn(sampleDoctor);

        // Act
        doctorMgmtService.registerDoctor(sampleDoctor);

        // Assert
        verify(doctorRepo).save(sampleDoctor);
    }

    // ─── registerDoctor – different doctor IDs ───────────────────────────────

    @Test
    void registerDoctor_withDoctorId100_returnsMessageWithId100() {
        // Arrange
        Doctor savedDoc = new Doctor();
        savedDoc.setDocId(100);
        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoc);

        // Act
        String result = doctorMgmtService.registerDoctor(new Doctor());

        // Assert
        assertEquals("Doctor obj is saved with id value :100", result);
    }

    @Test
    void registerDoctor_withDoctorIdNull_returnsMessageWithNull() {
        // Arrange
        Doctor savedDoc = new Doctor();
        savedDoc.setDocId(null);
        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoc);

        // Act
        String result = doctorMgmtService.registerDoctor(new Doctor());

        // Assert
        assertEquals("Doctor obj is saved with id value :null", result);
    }

    // ─── registerDoctor – repo throws exception ───────────────────────────────

    @Test
    void registerDoctor_whenRepoThrowsRuntimeException_propagatesException() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class)))
                .thenThrow(new RuntimeException("DB connection failed"));

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> doctorMgmtService.registerDoctor(sampleDoctor));
        assertEquals("DB connection failed", ex.getMessage());
    }

    @Test
    void registerDoctor_whenRepoThrowsIllegalArgumentException_propagatesException() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class)))
                .thenThrow(new IllegalArgumentException("Invalid doctor data"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> doctorMgmtService.registerDoctor(sampleDoctor));
    }

    // ─── registerDoctor – return value format ────────────────────────────────

    @Test
    void registerDoctor_returnValueStartsWithExpectedPrefix() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(sampleDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(sampleDoctor);

        // Assert
        assertTrue(result.startsWith("Doctor obj is saved with id value :"));
    }

    @Test
    void registerDoctor_returnValueIsNotEmpty() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(sampleDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(sampleDoctor);

        // Assert
        assertFalse(result.isEmpty());
    }

    // ─── Service implements IDoctorService ───────────────────────────────────

    @Test
    void doctorMgmtServiceImpl_implementsIDoctorService() {
        // Assert
        assertTrue(doctorMgmtService instanceof IDoctorService);
    }

    // ─── Multiple registrations ───────────────────────────────────────────────

    @Test
    void registerDoctor_calledMultipleTimes_eachCallSavesDoctor() {
        // Arrange
        Doctor doc1 = new Doctor();
        doc1.setDocId(1);
        Doctor doc2 = new Doctor();
        doc2.setDocId(2);

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
