package com.sit.service;

import com.sit.entity.Doctor;
import com.sit.repository.IDoctorRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Comprehensive test class for DoctorMgmtServiceImpl
 * Tests service layer logic with mocked repository
 */
@ExtendWith(MockitoExtension.class)
class DoctorMgmtServiceImplTest {

    @Mock
    private IDoctorRepo doctorRepo;

    @InjectMocks
    private DoctorMgmtServiceImpl doctorService;

    private Doctor testDoctor;

    @BeforeEach
    void setUp() {
        testDoctor = new Doctor();
        testDoctor.setDocName("Dr. John Smith");
        testDoctor.setSpecialization("Cardiology");
        testDoctor.setIncome(90000.00);
    }

    @Test
    void testRegisterDoctor_withValidDoctor_returnsSuccessMessage() {
        // Arrange
        Doctor savedDoctor = new Doctor();
        savedDoctor.setDocId(100);
        savedDoctor.setDocName("Dr. John Smith");
        savedDoctor.setSpecialization("Cardiology");
        savedDoctor.setIncome(90000.00);
        
        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoctor);
        
        // Act
        String result = doctorService.registerDoctor(testDoctor);
        
        // Assert
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("Doctor obj is saved with id value"), "Result should contain success message");
        assertTrue(result.contains("100"), "Result should contain the doctor ID");
        verify(doctorRepo, times(1)).save(testDoctor);
    }

    @Test
    void testRegisterDoctor_withNullId_returnsMessageWithNullId() {
        // Arrange
        Doctor savedDoctor = new Doctor();
        savedDoctor.setDocId(null);
        savedDoctor.setDocName("Dr. Jane Doe");
        savedDoctor.setSpecialization("Neurology");
        savedDoctor.setIncome(85000.00);
        
        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoctor);
        
        // Act
        String result = doctorService.registerDoctor(testDoctor);
        
        // Assert
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("Doctor obj is saved with id value"), "Result should contain success message");
        verify(doctorRepo, times(1)).save(testDoctor);
    }

    @Test
    void testRegisterDoctor_withDifferentSpecializations_savesCorrectly() {
        // Arrange
        String[] specializations = {"Cardiology", "Neurology", "Orthopedics", "Pediatrics", "Dermatology"};
        
        for (int i = 0; i < specializations.length; i++) {
            Doctor doctor = new Doctor();
            doctor.setDocName("Dr. Test " + i);
            doctor.setSpecialization(specializations[i]);
            doctor.setIncome(80000.00 + i * 10000);
            
            Doctor savedDoctor = new Doctor();
            savedDoctor.setDocId(200 + i);
            savedDoctor.setDocName(doctor.getDocName());
            savedDoctor.setSpecialization(doctor.getSpecialization());
            savedDoctor.setIncome(doctor.getIncome());
            
            when(doctorRepo.save(doctor)).thenReturn(savedDoctor);
            
            // Act
            String result = doctorService.registerDoctor(doctor);
            
            // Assert
            assertNotNull(result, "Result should not be null for specialization: " + specializations[i]);
            assertTrue(result.contains(String.valueOf(200 + i)), "Result should contain correct ID");
        }
        
        verify(doctorRepo, times(5)).save(any(Doctor.class));
    }

    @Test
    void testRegisterDoctor_withMinimumIncome_savesSuccessfully() {
        // Arrange
        testDoctor.setIncome(0.0);
        Doctor savedDoctor = new Doctor();
        savedDoctor.setDocId(150);
        savedDoctor.setDocName(testDoctor.getDocName());
        savedDoctor.setSpecialization(testDoctor.getSpecialization());
        savedDoctor.setIncome(0.0);
        
        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoctor);
        
        // Act
        String result = doctorService.registerDoctor(testDoctor);
        
        // Assert
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("150"), "Result should contain the doctor ID");
        verify(doctorRepo, times(1)).save(testDoctor);
    }

    @Test
    void testRegisterDoctor_withMaximumIncome_savesSuccessfully() {
        // Arrange
        testDoctor.setIncome(999999.99);
        Doctor savedDoctor = new Doctor();
        savedDoctor.setDocId(175);
        savedDoctor.setDocName(testDoctor.getDocName());
        savedDoctor.setSpecialization(testDoctor.getSpecialization());
        savedDoctor.setIncome(999999.99);
        
        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoctor);
        
        // Act
        String result = doctorService.registerDoctor(testDoctor);
        
        // Assert
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("175"), "Result should contain the doctor ID");
        verify(doctorRepo, times(1)).save(testDoctor);
    }

    @Test
    void testRegisterDoctor_withLongName_savesSuccessfully() {
        // Arrange
        testDoctor.setDocName("Dr. Christopher Alexander");
        Doctor savedDoctor = new Doctor();
        savedDoctor.setDocId(180);
        savedDoctor.setDocName(testDoctor.getDocName());
        savedDoctor.setSpecialization(testDoctor.getSpecialization());
        savedDoctor.setIncome(testDoctor.getIncome());
        
        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoctor);
        
        // Act
        String result = doctorService.registerDoctor(testDoctor);
        
        // Assert
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("180"), "Result should contain the doctor ID");
        verify(doctorRepo, times(1)).save(testDoctor);
    }

    @Test
    void testRegisterDoctor_withSpecialCharactersInName_savesSuccessfully() {
        // Arrange
        testDoctor.setDocName("Dr. O'Brien-Smith");
        Doctor savedDoctor = new Doctor();
        savedDoctor.setDocId(190);
        savedDoctor.setDocName(testDoctor.getDocName());
        savedDoctor.setSpecialization(testDoctor.getSpecialization());
        savedDoctor.setIncome(testDoctor.getIncome());
        
        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoctor);
        
        // Act
        String result = doctorService.registerDoctor(testDoctor);
        
        // Assert
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("190"), "Result should contain the doctor ID");
        verify(doctorRepo, times(1)).save(testDoctor);
    }

    @Test
    void testRegisterDoctor_multipleRegistrations_eachSavesIndependently() {
        // Arrange
        Doctor doctor1 = new Doctor();
        doctor1.setDocName("Dr. First");
        doctor1.setSpecialization("Cardiology");
        doctor1.setIncome(90000.00);
        
        Doctor doctor2 = new Doctor();
        doctor2.setDocName("Dr. Second");
        doctor2.setSpecialization("Neurology");
        doctor2.setIncome(95000.00);
        
        Doctor savedDoctor1 = new Doctor();
        savedDoctor1.setDocId(201);
        savedDoctor1.setDocName(doctor1.getDocName());
        
        Doctor savedDoctor2 = new Doctor();
        savedDoctor2.setDocId(202);
        savedDoctor2.setDocName(doctor2.getDocName());
        
        when(doctorRepo.save(doctor1)).thenReturn(savedDoctor1);
        when(doctorRepo.save(doctor2)).thenReturn(savedDoctor2);
        
        // Act
        String result1 = doctorService.registerDoctor(doctor1);
        String result2 = doctorService.registerDoctor(doctor2);
        
        // Assert
        assertTrue(result1.contains("201"), "First result should contain ID 201");
        assertTrue(result2.contains("202"), "Second result should contain ID 202");
        verify(doctorRepo, times(1)).save(doctor1);
        verify(doctorRepo, times(1)).save(doctor2);
    }

    @Test
    void testRegisterDoctor_verifyRepositoryInteraction() {
        // Arrange
        Doctor savedDoctor = new Doctor();
        savedDoctor.setDocId(250);
        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoctor);
        
        // Act
        doctorService.registerDoctor(testDoctor);
        
        // Assert
        verify(doctorRepo, times(1)).save(testDoctor);
        verifyNoMoreInteractions(doctorRepo);
    }

    @Test
    void testRegisterDoctor_withNullIncome_savesSuccessfully() {
        // Arrange
        testDoctor.setIncome(null);
        Doctor savedDoctor = new Doctor();
        savedDoctor.setDocId(260);
        savedDoctor.setDocName(testDoctor.getDocName());
        savedDoctor.setSpecialization(testDoctor.getSpecialization());
        savedDoctor.setIncome(null);
        
        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoctor);
        
        // Act
        String result = doctorService.registerDoctor(testDoctor);
        
        // Assert
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("260"), "Result should contain the doctor ID");
        verify(doctorRepo, times(1)).save(testDoctor);
    }

    @Test
    void testRegisterDoctor_returnsCorrectMessageFormat() {
        // Arrange
        Doctor savedDoctor = new Doctor();
        savedDoctor.setDocId(300);
        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoctor);
        
        // Act
        String result = doctorService.registerDoctor(testDoctor);
        
        // Assert
        assertEquals("Doctor obj is saved with id value :300", result, "Message format should match exactly");
    }

    @Test
    void testRegisterDoctor_withEmptyName_savesSuccessfully() {
        // Arrange
        testDoctor.setDocName("");
        Doctor savedDoctor = new Doctor();
        savedDoctor.setDocId(310);
        savedDoctor.setDocName("");
        savedDoctor.setSpecialization(testDoctor.getSpecialization());
        savedDoctor.setIncome(testDoctor.getIncome());
        
        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoctor);
        
        // Act
        String result = doctorService.registerDoctor(testDoctor);
        
        // Assert
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("310"), "Result should contain the doctor ID");
        verify(doctorRepo, times(1)).save(testDoctor);
    }

    @Test
    void testRegisterDoctor_withEmptySpecialization_savesSuccessfully() {
        // Arrange
        testDoctor.setSpecialization("");
        Doctor savedDoctor = new Doctor();
        savedDoctor.setDocId(320);
        savedDoctor.setDocName(testDoctor.getDocName());
        savedDoctor.setSpecialization("");
        savedDoctor.setIncome(testDoctor.getIncome());
        
        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoctor);
        
        // Act
        String result = doctorService.registerDoctor(testDoctor);
        
        // Assert
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("320"), "Result should contain the doctor ID");
        verify(doctorRepo, times(1)).save(testDoctor);
    }
}
