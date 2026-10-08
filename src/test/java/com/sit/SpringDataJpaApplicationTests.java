package com.sit;

import com.sit.Service.IDoctorService;
import com.sit.entity.Doctor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for SpringBootDataJpa application context.
 * Uses H2 in-memory database via test application.properties.
 */
@SpringBootTest
@DisplayName("Spring Application Context Integration Tests")
class SpringDataJpaApplicationTests {

    @Autowired(required = false)
    private IDoctorService doctorService;

    @Test
    @DisplayName("Application context loads successfully")
    void contextLoads() {
        // Verifies that the Spring application context starts without errors
    }

    @Test
    @DisplayName("IDoctorService bean is available in context")
    void doctorService_shouldBeAvailableInContext() {
        assertNotNull(doctorService);
    }

    @Test
    @DisplayName("registerDoctor integration test with H2 database")
    void registerDoctor_integrationTest_shouldReturnSuccessMessage() {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setDocName("IntegrationTestDoc");
        doctor.setSpecialization("Cardiology");
        doctor.setIncome(85000.00);

        // Act
        String result = doctorService.registerDoctor(doctor);

        // Assert
        assertNotNull(result);
        assertTrue(result.startsWith("Doctor obj is saved with id value :"));
    }
}
