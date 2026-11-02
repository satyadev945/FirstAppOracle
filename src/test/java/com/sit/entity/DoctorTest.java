package com.sit.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

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

    // ─── setDocId / getDocId Tests ───────────────────────────────────────────

    @Test
    @DisplayName("setDocId stores and getDocId retrieves the value")
    void setDocId_validId_getDocIdReturnsValue() {
        // Arrange
        Integer expectedId = 101;

        // Act
        doctor.setDocId(expectedId);

        // Assert
        assertEquals(expectedId, doctor.getDocId());
    }

    @Test
    @DisplayName("setDocId with null value stores null")
    void setDocId_null_getDocIdReturnsNull() {
        // Arrange & Act
        doctor.setDocId(null);

        // Assert
        assertNull(doctor.getDocId());
    }

    @Test
    @DisplayName("setDocId with zero value stores zero")
    void setDocId_zero_getDocIdReturnsZero() {
        // Arrange & Act
        doctor.setDocId(0);

        // Assert
        assertEquals(0, doctor.getDocId());
    }

    @Test
    @DisplayName("setDocId with negative value stores negative")
    void setDocId_negativeValue_getDocIdReturnsNegative() {
        // Arrange & Act
        doctor.setDocId(-1);

        // Assert
        assertEquals(-1, doctor.getDocId());
    }

    @Test
    @DisplayName("setDocId with max integer value stores correctly")
    void setDocId_maxIntValue_getDocIdReturnsMaxInt() {
        // Arrange & Act
        doctor.setDocId(Integer.MAX_VALUE);

        // Assert
        assertEquals(Integer.MAX_VALUE, doctor.getDocId());
    }

    // ─── setDocName / getDocName Tests ──────────────────────────────────────

    @Test
    @DisplayName("setDocName stores and getDocName retrieves the value")
    void setDocName_validName_getDocNameReturnsValue() {
        // Arrange
        String expectedName = "Dr. Smith";

        // Act
        doctor.setDocName(expectedName);

        // Assert
        assertEquals(expectedName, doctor.getDocName());
    }

    @Test
    @DisplayName("setDocName with null stores null")
    void setDocName_null_getDocNameReturnsNull() {
        // Arrange & Act
        doctor.setDocName(null);

        // Assert
        assertNull(doctor.getDocName());
    }

    @Test
    @DisplayName("setDocName with empty string stores empty string")
    void setDocName_emptyString_getDocNameReturnsEmpty() {
        // Arrange & Act
        doctor.setDocName("");

        // Assert
        assertEquals("", doctor.getDocName());
    }

    @Test
    @DisplayName("setDocName with whitespace stores whitespace")
    void setDocName_whitespace_getDocNameReturnsWhitespace() {
        // Arrange & Act
        doctor.setDocName("   ");

        // Assert
        assertEquals("   ", doctor.getDocName());
    }

    @Test
    @DisplayName("setDocName with 25-char string (max column length) stores correctly")
    void setDocName_maxLength_getDocNameReturnsValue() {
        // Arrange
        String maxName = "A".repeat(25);

        // Act
        doctor.setDocName(maxName);

        // Assert
        assertEquals(maxName, doctor.getDocName());
    }

    // ─── setSpecialization / getSpecialization Tests ─────────────────────────

    @Test
    @DisplayName("setSpecialization stores and getSpecialization retrieves the value")
    void setSpecialization_validValue_getSpecializationReturnsValue() {
        // Arrange
        String expectedSpec = "MD_Cardio";

        // Act
        doctor.setSpecialization(expectedSpec);

        // Assert
        assertEquals(expectedSpec, doctor.getSpecialization());
    }

    @Test
    @DisplayName("setSpecialization with null stores null")
    void setSpecialization_null_getSpecializationReturnsNull() {
        // Arrange & Act
        doctor.setSpecialization(null);

        // Assert
        assertNull(doctor.getSpecialization());
    }

    @Test
    @DisplayName("setSpecialization with empty string stores empty string")
    void setSpecialization_emptyString_getSpecializationReturnsEmpty() {
        // Arrange & Act
        doctor.setSpecialization("");

        // Assert
        assertEquals("", doctor.getSpecialization());
    }

    @Test
    @DisplayName("setSpecialization with 20-char string (max column length) stores correctly")
    void setSpecialization_maxLength_getSpecializationReturnsValue() {
        // Arrange
        String maxSpec = "B".repeat(20);

        // Act
        doctor.setSpecialization(maxSpec);

        // Assert
        assertEquals(maxSpec, doctor.getSpecialization());
    }

    // ─── setIncome / getIncome Tests ─────────────────────────────────────────

    @Test
    @DisplayName("setIncome stores and getIncome retrieves the value")
    void setIncome_validValue_getIncomeReturnsValue() {
        // Arrange
        Double expectedIncome = 90000.00;

        // Act
        doctor.setIncome(expectedIncome);

        // Assert
        assertEquals(expectedIncome, doctor.getIncome());
    }

    @Test
    @DisplayName("setIncome with null stores null")
    void setIncome_null_getIncomeReturnsNull() {
        // Arrange & Act
        doctor.setIncome(null);

        // Assert
        assertNull(doctor.getIncome());
    }

    @Test
    @DisplayName("setIncome with zero stores zero")
    void setIncome_zero_getIncomeReturnsZero() {
        // Arrange & Act
        doctor.setIncome(0.0);

        // Assert
        assertEquals(0.0, doctor.getIncome());
    }

    @Test
    @DisplayName("setIncome with negative value stores negative")
    void setIncome_negativeValue_getIncomeReturnsNegative() {
        // Arrange & Act
        doctor.setIncome(-500.50);

        // Assert
        assertEquals(-500.50, doctor.getIncome());
    }

    @Test
    @DisplayName("setIncome with max double value stores correctly")
    void setIncome_maxDoubleValue_getIncomeReturnsMaxDouble() {
        // Arrange & Act
        doctor.setIncome(Double.MAX_VALUE);

        // Assert
        assertEquals(Double.MAX_VALUE, doctor.getIncome());
    }

    // ─── Lombok @Data: equals / hashCode / toString Tests ───────────────────

    @Test
    @DisplayName("Two doctors with same fields are equal (Lombok equals)")
    void equals_sameFieds_returnsTrue() {
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
    @DisplayName("Two doctors with different ids are not equal")
    void equals_differentIds_returnsFalse() {
        // Arrange
        Doctor doc1 = new Doctor();
        doc1.setDocId(1);

        Doctor doc2 = new Doctor();
        doc2.setDocId(2);

        // Act & Assert
        assertNotEquals(doc1, doc2);
    }

    @Test
    @DisplayName("Two doctors with same fields have same hashCode (Lombok hashCode)")
    void hashCode_sameFields_returnsSameHashCode() {
        // Arrange
        Doctor doc1 = new Doctor();
        doc1.setDocId(1);
        doc1.setDocName("sairam");

        Doctor doc2 = new Doctor();
        doc2.setDocId(1);
        doc2.setDocName("sairam");

        // Act & Assert
        assertEquals(doc1.hashCode(), doc2.hashCode());
    }

    @Test
    @DisplayName("toString contains field values (Lombok toString)")
    void toString_withValues_containsFieldValues() {
        // Arrange
        doctor.setDocId(203);
        doctor.setDocName("sairam");
        doctor.setSpecialization("MD_Cardio");
        doctor.setIncome(90000.00);

        // Act
        String result = doctor.toString();

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("203"));
        assertTrue(result.contains("sairam"));
        assertTrue(result.contains("MD_Cardio"));
        assertTrue(result.contains("90000.0"));
    }

    @Test
    @DisplayName("Doctor is not equal to null")
    void equals_null_returnsFalse() {
        // Arrange
        doctor.setDocId(1);

        // Act & Assert
        assertNotEquals(null, doctor);
    }

    @Test
    @DisplayName("Doctor is equal to itself (reflexive)")
    void equals_sameInstance_returnsTrue() {
        // Arrange
        doctor.setDocId(1);

        // Act & Assert
        assertEquals(doctor, doctor);
    }

    // ─── Full population test ────────────────────────────────────────────────

    @Test
    @DisplayName("Fully populated Doctor has all fields set correctly")
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
}
