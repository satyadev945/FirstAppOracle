package com.sit.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Doctor entity class.
 * Tests all getters, setters, constructors, and Lombok-generated methods.
 */
@DisplayName("Doctor Entity Tests")
class DoctorTest {

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
    }

    // ─── Constructor Tests ───────────────────────────────────────────────────

    @Test
    @DisplayName("Default constructor creates non-null Doctor instance")
    void constructor_default_createsNonNullInstance() {
        // Arrange & Act
        Doctor doc = new Doctor();

        // Assert
        assertNotNull(doc);
    }

    @Test
    @DisplayName("Default constructor initializes fields to null")
    void constructor_default_fieldsAreNull() {
        // Arrange & Act
        Doctor doc = new Doctor();

        // Assert
        assertNull(doc.getDocId());
        assertNull(doc.getDocName());
        assertNull(doc.getSpecialization());
        assertNull(doc.getIncome());
    }

    // ─── DocId Tests ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("setDocId and getDocId work correctly with valid integer")
    void setDocId_validInteger_getDocIdReturnsCorrectValue() {
        // Arrange
        Integer expectedId = 101;

        // Act
        doctor.setDocId(expectedId);

        // Assert
        assertEquals(expectedId, doctor.getDocId());
    }

    @Test
    @DisplayName("setDocId with null value sets docId to null")
    void setDocId_null_getDocIdReturnsNull() {
        // Arrange
        doctor.setDocId(203);

        // Act
        doctor.setDocId(null);

        // Assert
        assertNull(doctor.getDocId());
    }

    @Test
    @DisplayName("setDocId with zero value is stored correctly")
    void setDocId_zero_getDocIdReturnsZero() {
        // Arrange & Act
        doctor.setDocId(0);

        // Assert
        assertEquals(0, doctor.getDocId());
    }

    @Test
    @DisplayName("setDocId with negative value is stored correctly")
    void setDocId_negativeValue_getDocIdReturnsNegativeValue() {
        // Arrange & Act
        doctor.setDocId(-1);

        // Assert
        assertEquals(-1, doctor.getDocId());
    }

    @Test
    @DisplayName("setDocId with max integer value is stored correctly")
    void setDocId_maxIntegerValue_getDocIdReturnsMaxValue() {
        // Arrange & Act
        doctor.setDocId(Integer.MAX_VALUE);

        // Assert
        assertEquals(Integer.MAX_VALUE, doctor.getDocId());
    }

    // ─── DocName Tests ────────────────────────────────────────────────────────

    @Test
    @DisplayName("setDocName and getDocName work correctly with valid string")
    void setDocName_validString_getDocNameReturnsCorrectValue() {
        // Arrange
        String expectedName = "Dr. John Smith";

        // Act
        doctor.setDocName(expectedName);

        // Assert
        assertEquals(expectedName, doctor.getDocName());
    }

    @Test
    @DisplayName("setDocName with null value sets docName to null")
    void setDocName_null_getDocNameReturnsNull() {
        // Arrange
        doctor.setDocName("sairam");

        // Act
        doctor.setDocName(null);

        // Assert
        assertNull(doctor.getDocName());
    }

    @Test
    @DisplayName("setDocName with empty string is stored correctly")
    void setDocName_emptyString_getDocNameReturnsEmptyString() {
        // Arrange & Act
        doctor.setDocName("");

        // Assert
        assertEquals("", doctor.getDocName());
    }

    @Test
    @DisplayName("setDocName with whitespace string is stored correctly")
    void setDocName_whitespaceString_getDocNameReturnsWhitespace() {
        // Arrange & Act
        doctor.setDocName("   ");

        // Assert
        assertEquals("   ", doctor.getDocName());
    }

    @Test
    @DisplayName("setDocName with 25-character string (max length) is stored correctly")
    void setDocName_maxLengthString_getDocNameReturnsCorrectValue() {
        // Arrange
        String maxLengthName = "ABCDEFGHIJKLMNOPQRSTUVWXY"; // 25 chars

        // Act
        doctor.setDocName(maxLengthName);

        // Assert
        assertEquals(maxLengthName, doctor.getDocName());
        assertEquals(25, doctor.getDocName().length());
    }

    // ─── Specialization Tests ─────────────────────────────────────────────────

    @Test
    @DisplayName("setSpecialization and getSpecialization work correctly")
    void setSpecialization_validString_getSpecializationReturnsCorrectValue() {
        // Arrange
        String expectedSpec = "MD_Cardio";

        // Act
        doctor.setSpecialization(expectedSpec);

        // Assert
        assertEquals(expectedSpec, doctor.getSpecialization());
    }

    @Test
    @DisplayName("setSpecialization with null value sets specialization to null")
    void setSpecialization_null_getSpecializationReturnsNull() {
        // Arrange
        doctor.setSpecialization("MD_Cardio");

        // Act
        doctor.setSpecialization(null);

        // Assert
        assertNull(doctor.getSpecialization());
    }

    @Test
    @DisplayName("setSpecialization with empty string is stored correctly")
    void setSpecialization_emptyString_getSpecializationReturnsEmptyString() {
        // Arrange & Act
        doctor.setSpecialization("");

        // Assert
        assertEquals("", doctor.getSpecialization());
    }

    @Test
    @DisplayName("setSpecialization with 20-character string (max length) is stored correctly")
    void setSpecialization_maxLengthString_getSpecializationReturnsCorrectValue() {
        // Arrange
        String maxLengthSpec = "ABCDEFGHIJKLMNOPQRST"; // 20 chars

        // Act
        doctor.setSpecialization(maxLengthSpec);

        // Assert
        assertEquals(maxLengthSpec, doctor.getSpecialization());
        assertEquals(20, doctor.getSpecialization().length());
    }

    // ─── Income Tests ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("setIncome and getIncome work correctly with valid double")
    void setIncome_validDouble_getIncomeReturnsCorrectValue() {
        // Arrange
        Double expectedIncome = 90000.00;

        // Act
        doctor.setIncome(expectedIncome);

        // Assert
        assertEquals(expectedIncome, doctor.getIncome());
    }

    @Test
    @DisplayName("setIncome with null value sets income to null")
    void setIncome_null_getIncomeReturnsNull() {
        // Arrange
        doctor.setIncome(90000.00);

        // Act
        doctor.setIncome(null);

        // Assert
        assertNull(doctor.getIncome());
    }

    @Test
    @DisplayName("setIncome with zero value is stored correctly")
    void setIncome_zero_getIncomeReturnsZero() {
        // Arrange & Act
        doctor.setIncome(0.0);

        // Assert
        assertEquals(0.0, doctor.getIncome());
    }

    @Test
    @DisplayName("setIncome with negative value is stored correctly")
    void setIncome_negativeValue_getIncomeReturnsNegativeValue() {
        // Arrange & Act
        doctor.setIncome(-5000.00);

        // Assert
        assertEquals(-5000.00, doctor.getIncome());
    }

    @Test
    @DisplayName("setIncome with large value is stored correctly")
    void setIncome_largeValue_getIncomeReturnsLargeValue() {
        // Arrange & Act
        doctor.setIncome(Double.MAX_VALUE);

        // Assert
        assertEquals(Double.MAX_VALUE, doctor.getIncome());
    }

    @Test
    @DisplayName("setIncome with decimal precision is stored correctly")
    void setIncome_decimalPrecision_getIncomeReturnsCorrectValue() {
        // Arrange & Act
        doctor.setIncome(12345.6789);

        // Assert
        assertEquals(12345.6789, doctor.getIncome(), 0.0001);
    }

    // ─── Lombok @Data Generated Methods Tests ─────────────────────────────────

    @Test
    @DisplayName("equals returns true for two doctors with same field values")
    void equals_sameDoctorFields_returnsTrue() {
        // Arrange
        Doctor doc1 = new Doctor();
        doc1.setDocId(1);
        doc1.setDocName("sairam");
        doc1.setSpecialization("MD_Cardio");
        doc1.setIncome(90000.00);

        Doctor doc2 = new Doctor();
        doc2.setDocId(1);
        doc2.setDocName("sairam");
        doc2.setSpecialization("MD_Cardio");
        doc2.setIncome(90000.00);

        // Act & Assert
        assertEquals(doc1, doc2);
    }

    @Test
    @DisplayName("equals returns false for two doctors with different docId")
    void equals_differentDocId_returnsFalse() {
        // Arrange
        Doctor doc1 = new Doctor();
        doc1.setDocId(1);
        doc1.setDocName("sairam");

        Doctor doc2 = new Doctor();
        doc2.setDocId(2);
        doc2.setDocName("sairam");

        // Act & Assert
        assertNotEquals(doc1, doc2);
    }

    @Test
    @DisplayName("equals returns false when compared to null")
    void equals_null_returnsFalse() {
        // Arrange
        Doctor doc = new Doctor();
        doc.setDocId(1);

        // Act & Assert
        assertNotEquals(null, doc);
    }

    @Test
    @DisplayName("hashCode is consistent for same doctor fields")
    void hashCode_sameDoctorFields_returnsSameHashCode() {
        // Arrange
        Doctor doc1 = new Doctor();
        doc1.setDocId(1);
        doc1.setDocName("sairam");
        doc1.setSpecialization("MD_Cardio");
        doc1.setIncome(90000.00);

        Doctor doc2 = new Doctor();
        doc2.setDocId(1);
        doc2.setDocName("sairam");
        doc2.setSpecialization("MD_Cardio");
        doc2.setIncome(90000.00);

        // Act & Assert
        assertEquals(doc1.hashCode(), doc2.hashCode());
    }

    @Test
    @DisplayName("toString returns non-null string representation")
    void toString_validDoctor_returnsNonNullString() {
        // Arrange
        doctor.setDocId(1);
        doctor.setDocName("sairam");
        doctor.setSpecialization("MD_Cardio");
        doctor.setIncome(90000.00);

        // Act
        String result = doctor.toString();

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("toString contains docName value")
    void toString_validDoctor_containsDocName() {
        // Arrange
        doctor.setDocId(1);
        doctor.setDocName("sairam");
        doctor.setSpecialization("MD_Cardio");
        doctor.setIncome(90000.00);

        // Act
        String result = doctor.toString();

        // Assert
        assertTrue(result.contains("sairam"));
    }

    @Test
    @DisplayName("toString contains specialization value")
    void toString_validDoctor_containsSpecialization() {
        // Arrange
        doctor.setDocId(1);
        doctor.setDocName("sairam");
        doctor.setSpecialization("MD_Cardio");
        doctor.setIncome(90000.00);

        // Act
        String result = doctor.toString();

        // Assert
        assertTrue(result.contains("MD_Cardio"));
    }

    // ─── Full Object Population Tests ─────────────────────────────────────────

    @Test
    @DisplayName("Fully populated Doctor object has all fields set correctly")
    void doctor_fullyPopulated_allFieldsCorrect() {
        // Arrange & Act
        doctor.setDocId(203);
        doctor.setDocName("sairam");
        doctor.setSpecialization("MD_Cardio");
        doctor.setIncome(90000.00);

        // Assert
        assertAll("Doctor fields",
            () -> assertEquals(203, doctor.getDocId()),
            () -> assertEquals("sairam", doctor.getDocName()),
            () -> assertEquals("MD_Cardio", doctor.getSpecialization()),
            () -> assertEquals(90000.00, doctor.getIncome())
        );
    }

    @Test
    @DisplayName("Doctor fields can be updated after initial set")
    void doctor_updateFields_fieldsUpdatedCorrectly() {
        // Arrange
        doctor.setDocId(1);
        doctor.setDocName("OldName");
        doctor.setSpecialization("OldSpec");
        doctor.setIncome(50000.00);

        // Act
        doctor.setDocId(2);
        doctor.setDocName("NewName");
        doctor.setSpecialization("NewSpec");
        doctor.setIncome(75000.00);

        // Assert
        assertAll("Updated Doctor fields",
            () -> assertEquals(2, doctor.getDocId()),
            () -> assertEquals("NewName", doctor.getDocName()),
            () -> assertEquals("NewSpec", doctor.getSpecialization()),
            () -> assertEquals(75000.00, doctor.getIncome())
        );
    }
}
