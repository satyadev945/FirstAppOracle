package com.sit.runner;

import com.sit.entity.Doctor;
import com.sit.service.IDoctorService;
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
 * Comprehensive test class for BootDataJPAProj1CrudRepoApp
 * Tests CommandLineRunner implementation
 */
@ExtendWith(MockitoExtension.class)
class BootDataJPAProj1CrudRepoAppTest {

    @Mock
    private IDoctorService service;

    @InjectMocks
    private BootDataJPAProj1CrudRepoApp commandLineRunner;

    @BeforeEach
    void setUp() {
        // Setup is handled by MockitoExtension
    }

    @Test
    void testRun_withValidService_registersDoctor() throws Exception {
        // Arrange
        String expectedResult = "Doctor obj is saved with id value :100";
        when(service.registerDoctor(any(Doctor.class))).thenReturn(expectedResult);
        
        // Act
        commandLineRunner.run();
        
        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    void testRun_withValidService_createsCorrectDoctorObject() throws Exception {
        // Arrange
        String expectedResult = "Doctor obj is saved with id value :200";
        when(service.registerDoctor(any(Doctor.class))).thenReturn(expectedResult);
        
        // Act
        commandLineRunner.run();
        
        // Assert - Verify the doctor object has correct properties
        verify(service).registerDoctor(argThat(doctor -> 
            doctor.getDocName().equals("sairam") &&
            doctor.getSpecialization().equals("MD_Cardio") &&
            doctor.getIncome().equals(90000.00)
        ));
    }

    @Test
    void testRun_withServiceException_handlesException() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
            .thenThrow(new RuntimeException("Database connection failed"));
        
        // Act & Assert - Should not throw exception (caught internally)
        assertDoesNotThrow(() -> commandLineRunner.run());
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    void testRun_withNullPointerException_handlesException() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
            .thenThrow(new NullPointerException("Service is null"));
        
        // Act & Assert - Should not throw exception (caught internally)
        assertDoesNotThrow(() -> commandLineRunner.run());
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    void testRun_withMultipleArgs_executesSuccessfully() throws Exception {
        // Arrange
        String expectedResult = "Doctor obj is saved with id value :300";
        when(service.registerDoctor(any(Doctor.class))).thenReturn(expectedResult);
        String[] args = {"arg1", "arg2", "arg3"};
        
        // Act
        commandLineRunner.run(args);
        
        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    void testRun_withEmptyArgs_executesSuccessfully() throws Exception {
        // Arrange
        String expectedResult = "Doctor obj is saved with id value :400";
        when(service.registerDoctor(any(Doctor.class))).thenReturn(expectedResult);
        String[] args = {};
        
        // Act
        commandLineRunner.run(args);
        
        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    void testRun_withNullArgs_executesSuccessfully() throws Exception {
        // Arrange
        String expectedResult = "Doctor obj is saved with id value :500";
        when(service.registerDoctor(any(Doctor.class))).thenReturn(expectedResult);
        
        // Act
        commandLineRunner.run((String[]) null);
        
        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    void testRun_verifyDoctorNameIsSetCorrectly() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Success");
        
        // Act
        commandLineRunner.run();
        
        // Assert
        verify(service).registerDoctor(argThat(doctor -> 
            "sairam".equals(doctor.getDocName())
        ));
    }

    @Test
    void testRun_verifyDoctorSpecializationIsSetCorrectly() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Success");
        
        // Act
        commandLineRunner.run();
        
        // Assert
        verify(service).registerDoctor(argThat(doctor -> 
            "MD_Cardio".equals(doctor.getSpecialization())
        ));
    }

    @Test
    void testRun_verifyDoctorIncomeIsSetCorrectly() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Success");
        
        // Act
        commandLineRunner.run();
        
        // Assert
        verify(service).registerDoctor(argThat(doctor -> 
            Double.valueOf(90000.00).equals(doctor.getIncome())
        ));
    }

    @Test
    void testRun_verifyServiceCalledOnlyOnce() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Success");
        
        // Act
        commandLineRunner.run();
        
        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    void testRun_withIllegalArgumentException_handlesException() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
            .thenThrow(new IllegalArgumentException("Invalid doctor data"));
        
        // Act & Assert
        assertDoesNotThrow(() -> commandLineRunner.run());
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    void testRun_withIllegalStateException_handlesException() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
            .thenThrow(new IllegalStateException("Service not ready"));
        
        // Act & Assert
        assertDoesNotThrow(() -> commandLineRunner.run());
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    void testRun_verifyDoctorObjectIsNotNull() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Success");
        
        // Act
        commandLineRunner.run();
        
        // Assert
        verify(service).registerDoctor(argThat(doctor -> doctor != null));
    }

    @Test
    void testRun_verifyAllDoctorFieldsAreSet() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Success");
        
        // Act
        commandLineRunner.run();
        
        // Assert
        verify(service).registerDoctor(argThat(doctor -> 
            doctor.getDocName() != null &&
            doctor.getSpecialization() != null &&
            doctor.getIncome() != null
        ));
    }

    @Test
    void testRun_withServiceReturningNull_handlesGracefully() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenReturn(null);
        
        // Act & Assert
        assertDoesNotThrow(() -> commandLineRunner.run());
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    void testRun_withServiceReturningEmptyString_handlesGracefully() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenReturn("");
        
        // Act & Assert
        assertDoesNotThrow(() -> commandLineRunner.run());
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }
}
