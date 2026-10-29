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
    void defaultConstructor_createsNonNullInstance() {
        // Arrange & Act
        Doctor doc = new Doctor();

        // Assert
        assertNotNull(doc);
    }

    @Test
    @DisplayName("Default constructor initializes fields to null")
    void defaultConstructor_fieldsAreNullByDefault() {
        // Arrange & Act
        Doctor doc = new Doctor();

        // Assert
        assertNull(doc.getDocId());
        assertNull(doc.getDocName());
        assertNull(doc.getSpecialization());
        assertNull(doc.getIncome());
    }

    // ─── Setter / Getter Tests ───────────────────────────────────────────────

    @Test
    @DisplayName("setDocId and getDocId work correctly")
    void setDocId_getDocId_returnsCorrectValue() {
        // Arrange
        Integer expectedId = 101;

        // Act
        doctor.setDocId(expectedId);

        // Assert
        assertEquals(expectedId, doctor.getDocId());
    }

    @Test
    @DisplayName("setDocName and getDocName work correctly")
    void setDocName_getDocName_returnsCorrectValue() {
        // Arrange
        String expectedName = "Dr. John Smith";

        // Act
        doctor.setDocName(expectedName);

        // Assert
        assertEquals(expectedName, doctor.getDocName());
    }

    @Test
    @DisplayName("setSpecialization and getSpecialization work correctly")
    void setSpecialization_getSpecialization_returnsCorrectValue() {
        // Arrange
        String expectedSpec = "MD_Cardio";

        // Act
        doctor.setSpecialization(expectedSpec);

        // Assert
        assertEquals(expectedSpec, doctor.getSpecialization());
    }

    @Test
    @DisplayName("setIncome and getIncome work correctly")
    void setIncome_getIncome_returnsCorrectValue() {
        // Arrange
        Double expectedIncome = 90000.00;

        // Act
        doctor.setIncome(expectedIncome);

        // Assert
        assertEquals(expectedIncome, doctor.getIncome());
    }

    @Test
    @DisplayName("setDocId with null value stores null")
    void setDocId_withNull_storesNull() {
        // Arrange
        doctor.setDocId(999);

        // Act
        doctor.setDocId(null);

        // Assert
        assertNull(doctor.getDocId());
    }

    @Test
    @DisplayName("setDocName with null value stores null")
    void setDocName_withNull_storesNull() {
        // Arrange
        doctor.setDocName("SomeName");

        // Act
        doctor.setDocName(null);

        // Assert
        assertNull(doctor.getDocName());
    }

    @Test
    @DisplayName("setSpecialization with null value stores null")
    void setSpecialization_withNull_storesNull() {
        // Arrange
        doctor.setSpecialization("Cardiology");

        // Act
        doctor.setSpecialization(null);

        // Assert
        assertNull(doctor.getSpecialization());
    }

    @Test
    @DisplayName("setIncome with null value stores null")
    void setIncome_withNull_storesNull() {
        // Arrange
        doctor.setIncome(50000.00);

        // Act
        doctor.setIncome(null);

        // Assert
        assertNull(doctor.getIncome());
    }

    @Test
    @DisplayName("setIncome with zero value stores zero")
    void setIncome_withZero_storesZero() {
        // Arrange & Act
        doctor.setIncome(0.0);

        // Assert
        assertEquals(0.0, doctor.getIncome());
    }

    @Test
    @DisplayName("setIncome with negative value stores negative")
    void setIncome_withNegativeValue_storesNegative() {
        // Arrange & Act
        doctor.setIncome(-1000.00);

        // Assert
        assertEquals(-1000.00, doctor.getIncome());
    }

    @Test
    @DisplayName("setDocName with empty string stores empty string")
    void setDocName_withEmptyString_storesEmptyString() {
        // Arrange & Act
        doctor.setDocName("");

        // Assert
        assertEquals("", doctor.getDocName());
    }

    @Test
    @DisplayName("setSpecialization with empty string stores empty string")
    void setSpecialization_withEmptyString_storesEmptyString() {
        // Arrange & Act
        doctor.setSpecialization("");

        // Assert
        assertEquals("", doctor.getSpecialization());
    }

    // ─── Lombok @Data generated equals / hashCode / toString ────────────────

    @Test
    @DisplayName("Two doctors with same field values are equal (Lombok @Data)")
    void equals_twoIdenticalDoctors_areEqual() {
        // Arrange
        Doctor doc1 = new Doctor();
        doc1.setDocId(1);
        doc1.setDocName("Alice");
        doc1.setSpecialization("Neurology");
        doc1.setIncome(120000.00);

        Doctor doc2 = new Doctor();
        doc2.setDocId(1);
        doc2.setDocName("Alice");
        doc2.setSpecialization("Neurology");
        doc2.setIncome(120000.00);

        // Act & Assert
        assertEquals(doc1, doc2);
    }

    @Test
    @DisplayName("Two doctors with different IDs are not equal")
    void equals_differentDocId_notEqual() {
        // Arrange
        Doctor doc1 = new Doctor();
        doc1.setDocId(1);
        doc1.setDocName("Alice");

        Doctor doc2 = new Doctor();
        doc2.setDocId(2);
        doc2.setDocName("Alice");

        // Act & Assert
        assertNotEquals(doc1, doc2);
    }

    @Test
    @DisplayName("hashCode is consistent for equal doctors")
    void hashCode_equalDoctors_sameHashCode() {
        // Arrange
        Doctor doc1 = new Doctor();
        doc1.setDocId(10);
        doc1.setDocName("Bob");
        doc1.setSpecialization("Ortho");
        doc1.setIncome(80000.00);

        Doctor doc2 = new Doctor();
        doc2.setDocId(10);
        doc2.setDocName("Bob");
        doc2.setSpecialization("Ortho");
        doc2.setIncome(80000.00);

        // Act & Assert
        assertEquals(doc1.hashCode(), doc2.hashCode());
    }

    @Test
    @DisplayName("toString contains docName field value")
    void toString_containsDocName() {
        // Arrange
        doctor.setDocId(5);
        doctor.setDocName("DrTest");
        doctor.setSpecialization("Pediatrics");
        doctor.setIncome(75000.00);

        // Act
        String result = doctor.toString();

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("DrTest"));
    }

    @Test
    @DisplayName("toString contains specialization field value")
    void toString_containsSpecialization() {
        // Arrange
        doctor.setDocId(5);
        doctor.setDocName("DrTest");
        doctor.setSpecialization("Pediatrics");
        doctor.setIncome(75000.00);

        // Act
        String result = doctor.toString();

        // Assert
        assertTrue(result.contains("Pediatrics"));
    }

    @Test
    @DisplayName("Doctor is not equal to null")
    void equals_withNull_returnsFalse() {
        // Arrange
        doctor.setDocId(1);
        doctor.setDocName("Test");

        // Act & Assert
        assertNotEquals(null, doctor);
    }

    @Test
    @DisplayName("Doctor is not equal to different type object")
    void equals_withDifferentType_returnsFalse() {
        // Arrange
        doctor.setDocId(1);

        // Act & Assert
        assertNotEquals("string", doctor);
    }

    @Test
    @DisplayName("Doctor equals itself (reflexive)")
    void equals_sameInstance_returnsTrue() {
        // Arrange
        doctor.setDocId(1);
        doctor.setDocName("Self");

        // Act & Assert
        assertEquals(doctor, doctor);
    }

    // ─── Boundary / Edge Cases ───────────────────────────────────────────────

    @Test
    @DisplayName("setDocId with maximum Integer value")
    void setDocId_withMaxIntegerValue_storesCorrectly() {
        // Arrange & Act
        doctor.setDocId(Integer.MAX_VALUE);

        // Assert
        assertEquals(Integer.MAX_VALUE, doctor.getDocId());
    }

    @Test
    @DisplayName("setDocId with minimum Integer value")
    void setDocId_withMinIntegerValue_storesCorrectly() {
        // Arrange & Act
        doctor.setDocId(Integer.MIN_VALUE);

        // Assert
        assertEquals(Integer.MIN_VALUE, doctor.getDocId());
    }

    @Test
    @DisplayName("setIncome with maximum Double value")
    void setIncome_withMaxDoubleValue_storesCorrectly() {
        // Arrange & Act
        doctor.setIncome(Double.MAX_VALUE);

        // Assert
        assertEquals(Double.MAX_VALUE, doctor.getIncome());
    }

    @Test
    @DisplayName("Full doctor object with all fields set")
    void fullDoctorObject_allFieldsSet_returnsCorrectValues() {
        // Arrange
        Integer id = 203;
        String name = "sairam";
        String spec = "MD_Cardio";
        Double income = 90000.00;

        // Act
        doctor.setDocId(id);
        doctor.setDocName(name);
        doctor.setSpecialization(spec);
        doctor.setIncome(income);

        // Assert
        assertAll("Doctor fields",
                () -> assertEquals(id, doctor.getDocId()),
                () -> assertEquals(name, doctor.getDocName()),
                () -> assertEquals(spec, doctor.getSpecialization()),
                () -> assertEquals(income, doctor.getIncome())
        );
    }
}
