package com.sit.Service;

import com.sit.entity.Doctor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for IDoctorService interface contract.
 * Verifies the interface contract using an anonymous implementation.
 */
@DisplayName("IDoctorService Interface Tests")
class IDoctorServiceTest {

    @Test
    @DisplayName("IDoctorService implementation returns non-null result for registerDoctor")
    void registerDoctor_validDoctor_returnsNonNull() {
        // Arrange
        IDoctorService service = doctor -> "Doctor obj is saved with id value :" + 1;
        Doctor doc = new Doctor();
        doc.setDocName("TestDoctor");
        doc.setSpecialization("General");
        doc.setIncome(50000.0);

        // Act
        String result = service.registerDoctor(doc);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("IDoctorService lambda implementation returns expected message format")
    void registerDoctor_lambdaImpl_returnsExpectedFormat() {
        // Arrange
        IDoctorService service = doctor -> "Doctor obj is saved with id value :203";
        Doctor doc = new Doctor();

        // Act
        String result = service.registerDoctor(doc);

        // Assert
        assertEquals("Doctor obj is saved with id value :203", result);
    }

    @Test
    @DisplayName("IDoctorService is a functional interface with single abstract method")
    void iDoctorService_isFunctionalInterface_canBeUsedAsLambda() {
        // Arrange & Act
        IDoctorService service = doctor -> "saved";

        // Assert
        assertNotNull(service);
    }

    @Test
    @DisplayName("IDoctorService registerDoctor with null doctor does not throw by default")
    void registerDoctor_nullDoctor_implementationHandlesIt() {
        // Arrange
        IDoctorService service = doctor -> {
            if (doctor == null) return "null doctor";
            return "Doctor obj is saved with id value :0";
        };

        // Act
        String result = service.registerDoctor(null);

        // Assert
        assertEquals("null doctor", result);
    }
}
