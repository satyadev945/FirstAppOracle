package com.sit.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test class for Doctor entity
 * Tests all getters, setters, and entity behavior
 */
class DoctorTest {

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
    }

    @Test
    void testDoctorConstructor() {
        // Test default constructor
        Doctor newDoctor = new Doctor();
        assertNotNull(newDoctor, "Doctor object should not be null");
    }

    @Test
    void testSetAndGetDocId() {
        // Arrange
        Integer expectedId = 100;
        
        // Act
        doctor.setDocId(expectedId);
        Integer actualId = doctor.getDocId();
        
        // Assert
        assertEquals(expectedId, actualId, "Doc ID should match the set value");
    }

    @Test
    void testSetAndGetDocIdWithNull() {
        // Act
        doctor.setDocId(null);
        Integer actualId = doctor.getDocId();
        
        // Assert
        assertNull(actualId, "Doc ID should be null when set to null");
    }

    @Test
    void testSetAndGetDocName() {
        // Arrange
        String expectedName = "Dr. John Smith";
        
        // Act
        doctor.setDocName(expectedName);
        String actualName = doctor.getDocName();
        
        // Assert
        assertEquals(expectedName, actualName, "Doc name should match the set value");
    }

    @Test
    void testSetAndGetDocNameWithNull() {
        // Act
        doctor.setDocName(null);
        String actualName = doctor.getDocName();
        
        // Assert
        assertNull(actualName, "Doc name should be null when set to null");
    }

    @Test
    void testSetAndGetDocNameWithEmptyString() {
        // Arrange
        String expectedName = "";
        
        // Act
        doctor.setDocName(expectedName);
        String actualName = doctor.getDocName();
        
        // Assert
        assertEquals(expectedName, actualName, "Doc name should handle empty string");
    }

    @Test
    void testSetAndGetDocNameWithMaxLength() {
        // Arrange - Column length is 25
        String expectedName = "Dr. John Michael Smith";
        
        // Act
        doctor.setDocName(expectedName);
        String actualName = doctor.getDocName();
        
        // Assert
        assertEquals(expectedName, actualName, "Doc name should handle max length");
    }

    @Test
    void testSetAndGetSpecialization() {
        // Arrange
        String expectedSpecialization = "Cardiology";
        
        // Act
        doctor.setSpecialization(expectedSpecialization);
        String actualSpecialization = doctor.getSpecialization();
        
        // Assert
        assertEquals(expectedSpecialization, actualSpecialization, "Specialization should match the set value");
    }

    @Test
    void testSetAndGetSpecializationWithNull() {
        // Act
        doctor.setSpecialization(null);
        String actualSpecialization = doctor.getSpecialization();
        
        // Assert
        assertNull(actualSpecialization, "Specialization should be null when set to null");
    }

    @Test
    void testSetAndGetSpecializationWithEmptyString() {
        // Arrange
        String expectedSpecialization = "";
        
        // Act
        doctor.setSpecialization(expectedSpecialization);
        String actualSpecialization = doctor.getSpecialization();
        
        // Assert
        assertEquals(expectedSpecialization, actualSpecialization, "Specialization should handle empty string");
    }

    @Test
    void testSetAndGetSpecializationWithMaxLength() {
        // Arrange - Column length is 20
        String expectedSpecialization = "MD_Cardio_Surgery";
        
        // Act
        doctor.setSpecialization(expectedSpecialization);
        String actualSpecialization = doctor.getSpecialization();
        
        // Assert
        assertEquals(expectedSpecialization, actualSpecialization, "Specialization should handle max length");
    }

    @Test
    void testSetAndGetIncome() {
        // Arrange
        Double expectedIncome = 90000.00;
        
        // Act
        doctor.setIncome(expectedIncome);
        Double actualIncome = doctor.getIncome();
        
        // Assert
        assertEquals(expectedIncome, actualIncome, "Income should match the set value");
    }

    @Test
    void testSetAndGetIncomeWithNull() {
        // Act
        doctor.setIncome(null);
        Double actualIncome = doctor.getIncome();
        
        // Assert
        assertNull(actualIncome, "Income should be null when set to null");
    }

    @Test
    void testSetAndGetIncomeWithZero() {
        // Arrange
        Double expectedIncome = 0.0;
        
        // Act
        doctor.setIncome(expectedIncome);
        Double actualIncome = doctor.getIncome();
        
        // Assert
        assertEquals(expectedIncome, actualIncome, "Income should handle zero value");
    }

    @Test
    void testSetAndGetIncomeWithNegativeValue() {
        // Arrange
        Double expectedIncome = -1000.00;
        
        // Act
        doctor.setIncome(expectedIncome);
        Double actualIncome = doctor.getIncome();
        
        // Assert
        assertEquals(expectedIncome, actualIncome, "Income should handle negative value");
    }

    @Test
    void testSetAndGetIncomeWithLargeValue() {
        // Arrange
        Double expectedIncome = 999999999.99;
        
        // Act
        doctor.setIncome(expectedIncome);
        Double actualIncome = doctor.getIncome();
        
        // Assert
        assertEquals(expectedIncome, actualIncome, "Income should handle large value");
    }

    @Test
    void testDoctorWithAllFieldsSet() {
        // Arrange
        Integer expectedId = 203;
        String expectedName = "Dr. Sarah Johnson";
        String expectedSpecialization = "Neurology";
        Double expectedIncome = 120000.00;
        
        // Act
        doctor.setDocId(expectedId);
        doctor.setDocName(expectedName);
        doctor.setSpecialization(expectedSpecialization);
        doctor.setIncome(expectedIncome);
        
        // Assert
        assertEquals(expectedId, doctor.getDocId(), "Doc ID should match");
        assertEquals(expectedName, doctor.getDocName(), "Doc name should match");
        assertEquals(expectedSpecialization, doctor.getSpecialization(), "Specialization should match");
        assertEquals(expectedIncome, doctor.getIncome(), "Income should match");
    }

    @Test
    void testDoctorEqualsAndHashCode() {
        // Arrange
        Doctor doctor1 = new Doctor();
        doctor1.setDocId(1);
        doctor1.setDocName("Dr. Smith");
        doctor1.setSpecialization("Cardiology");
        doctor1.setIncome(90000.00);
        
        Doctor doctor2 = new Doctor();
        doctor2.setDocId(1);
        doctor2.setDocName("Dr. Smith");
        doctor2.setSpecialization("Cardiology");
        doctor2.setIncome(90000.00);
        
        // Assert - Lombok @Data generates equals and hashCode
        assertEquals(doctor1, doctor2, "Doctors with same data should be equal");
        assertEquals(doctor1.hashCode(), doctor2.hashCode(), "Hash codes should match for equal objects");
    }

    @Test
    void testDoctorToString() {
        // Arrange
        doctor.setDocId(100);
        doctor.setDocName("Dr. Test");
        doctor.setSpecialization("Testing");
        doctor.setIncome(50000.00);
        
        // Act
        String result = doctor.toString();
        
        // Assert - Lombok @Data generates toString
        assertNotNull(result, "toString should not return null");
        assertTrue(result.contains("Doctor"), "toString should contain class name");
    }

    @Test
    void testDoctorFieldsInitiallyNull() {
        // Arrange
        Doctor newDoctor = new Doctor();
        
        // Assert
        assertNull(newDoctor.getDocId(), "Initial doc ID should be null");
        assertNull(newDoctor.getDocName(), "Initial doc name should be null");
        assertNull(newDoctor.getSpecialization(), "Initial specialization should be null");
        assertNull(newDoctor.getIncome(), "Initial income should be null");
    }

    @Test
    void testSetIncomeWithDecimalPrecision() {
        // Arrange
        Double expectedIncome = 90000.55;
        
        // Act
        doctor.setIncome(expectedIncome);
        Double actualIncome = doctor.getIncome();
        
        // Assert
        assertEquals(expectedIncome, actualIncome, 0.001, "Income should preserve decimal precision");
    }

    @Test
    void testMultipleSettersOnSameField() {
        // Act
        doctor.setDocName("First Name");
        doctor.setDocName("Second Name");
        doctor.setDocName("Final Name");
        
        // Assert
        assertEquals("Final Name", doctor.getDocName(), "Should retain the last set value");
    }
}
