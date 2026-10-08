package com.sit.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Doctor entity class.
 * Tests all getters, setters, constructors, and Lombok-generated methods.
 */
@DisplayName("Doctor Entity Tests")
class DoctorTest {

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
    }

    // ─── Constructor Tests ────────────────────────────────────────────────────

    @Test
    @DisplayName("Default constructor creates non-null Doctor instance")
    void testDefaultConstructor_createsNonNullInstance() {
        // Arrange & Act
        Doctor doc = new Doctor();

        // Assert
        assertNotNull(doc, "Doctor instance should not be null after default construction");
    }

    @Test
    @DisplayName("Default constructor initializes fields to null")
    void testDefaultConstructor_fieldsAreNull() {
        // Arrange & Act
        Doctor doc = new Doctor();

        // Assert
        assertNull(doc.getDocId(),          "docId should be null by default");
        assertNull(doc.getDocName(),        "docName should be null by default");
        assertNull(doc.getSpecialization(), "specialization should be null by default");
        assertNull(doc.getIncome(),         "income should be null by default");
    }

    // ─── Setter / Getter Tests ────────────────────────────────────────────────

    @Test
    @DisplayName("setDocId and getDocId work correctly")
    void testSetAndGetDocId() {
        // Arrange
        Integer expectedId = 101;

        // Act
        doctor.setDocId(expectedId);

        // Assert
        assertEquals(expectedId, doctor.getDocId(), "getDocId should return the value set by setDocId");
    }

    @Test
    @DisplayName("setDocName and getDocName work correctly")
    void testSetAndGetDocName() {
        // Arrange
        String expectedName = "Dr. John Smith";

        // Act
        doctor.setDocName(expectedName);

        // Assert
        assertEquals(expectedName, doctor.getDocName(), "getDocName should return the value set by setDocName");
    }

    @Test
    @DisplayName("setSpecialization and getSpecialization work correctly")
    void testSetAndGetSpecialization() {
        // Arrange
        String expectedSpec = "MD_Cardio";

        // Act
        doctor.setSpecialization(expectedSpec);

        // Assert
        assertEquals(expectedSpec, doctor.getSpecialization(),
                "getSpecialization should return the value set by setSpecialization");
    }

    @Test
    @DisplayName("setIncome and getIncome work correctly")
    void testSetAndGetIncome() {
        // Arrange
        Double expectedIncome = 90000.00;

        // Act
        doctor.setIncome(expectedIncome);

        // Assert
        assertEquals(expectedIncome, doctor.getIncome(), 0.001,
                "getIncome should return the value set by setIncome");
    }

    @Test
    @DisplayName("setDocId with null value stores null")
    void testSetDocId_withNull() {
        // Arrange
        doctor.setDocId(999);

        // Act
        doctor.setDocId(null);

        // Assert
        assertNull(doctor.getDocId(), "docId should be null after setting null");
    }

    @Test
    @DisplayName("setDocName with null value stores null")
    void testSetDocName_withNull() {
        // Arrange
        doctor.setDocName("Initial Name");

        // Act
        doctor.setDocName(null);

        // Assert
        assertNull(doctor.getDocName(), "docName should be null after setting null");
    }

    @Test
    @DisplayName("setSpecialization with null value stores null")
    void testSetSpecialization_withNull() {
        // Arrange
        doctor.setSpecialization("Cardiology");

        // Act
        doctor.setSpecialization(null);

        // Assert
        assertNull(doctor.getSpecialization(), "specialization should be null after setting null");
    }

    @Test
    @DisplayName("setIncome with null value stores null")
    void testSetIncome_withNull() {
        // Arrange
        doctor.setIncome(50000.00);

        // Act
        doctor.setIncome(null);

        // Assert
        assertNull(doctor.getIncome(), "income should be null after setting null");
    }

    @Test
    @DisplayName("setIncome with zero value stores zero")
    void testSetIncome_withZero() {
        // Arrange & Act
        doctor.setIncome(0.0);

        // Assert
        assertEquals(0.0, doctor.getIncome(), 0.001, "income should be 0.0");
    }

    @Test
    @DisplayName("setIncome with negative value stores negative")
    void testSetIncome_withNegativeValue() {
        // Arrange & Act
        doctor.setIncome(-1000.00);

        // Assert
        assertEquals(-1000.00, doctor.getIncome(), 0.001, "income should store negative values");
    }

    @Test
    @DisplayName("setDocName with empty string stores empty string")
    void testSetDocName_withEmptyString() {
        // Arrange & Act
        doctor.setDocName("");

        // Assert
        assertEquals("", doctor.getDocName(), "docName should store empty string");
    }

    @Test
    @DisplayName("setSpecialization with empty string stores empty string")
    void testSetSpecialization_withEmptyString() {
        // Arrange & Act
        doctor.setSpecialization("");

        // Assert
        assertEquals("", doctor.getSpecialization(), "specialization should store empty string");
    }

    // ─── Lombok @Data Generated Methods ──────────────────────────────────────

    @Test
    @DisplayName("equals returns true for two Doctors with same field values")
    void testEquals_withSameFieldValues_returnsTrue() {
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
        assertEquals(doc1, doc2, "Two Doctors with same fields should be equal");
    }

    @Test
    @DisplayName("equals returns false for two Doctors with different docId")
    void testEquals_withDifferentDocId_returnsFalse() {
        // Arrange
        Doctor doc1 = new Doctor();
        doc1.setDocId(1);
        doc1.setDocName("Alice");

        Doctor doc2 = new Doctor();
        doc2.setDocId(2);
        doc2.setDocName("Alice");

        // Act & Assert
        assertNotEquals(doc1, doc2, "Doctors with different docId should not be equal");
    }

    @Test
    @DisplayName("equals returns false when compared to null")
    void testEquals_withNull_returnsFalse() {
        // Arrange
        Doctor doc = new Doctor();
        doc.setDocId(1);

        // Act & Assert
        assertNotEquals(null, doc, "Doctor should not be equal to null");
    }

    @Test
    @DisplayName("hashCode is consistent for same Doctor instance")
    void testHashCode_isConsistentForSameInstance() {
        // Arrange
        doctor.setDocId(10);
        doctor.setDocName("Bob");
        doctor.setSpecialization("Orthopedics");
        doctor.setIncome(75000.00);

        // Act
        int hash1 = doctor.hashCode();
        int hash2 = doctor.hashCode();

        // Assert
        assertEquals(hash1, hash2, "hashCode should be consistent across multiple calls");
    }

    @Test
    @DisplayName("hashCode is equal for two Doctors with same field values")
    void testHashCode_equalForEqualObjects() {
        // Arrange
        Doctor doc1 = new Doctor();
        doc1.setDocId(5);
        doc1.setDocName("Carol");
        doc1.setSpecialization("Pediatrics");
        doc1.setIncome(85000.00);

        Doctor doc2 = new Doctor();
        doc2.setDocId(5);
        doc2.setDocName("Carol");
        doc2.setSpecialization("Pediatrics");
        doc2.setIncome(85000.00);

        // Act & Assert
        assertEquals(doc1.hashCode(), doc2.hashCode(),
                "Equal Doctors should have the same hashCode");
    }

    @Test
    @DisplayName("toString returns non-null string representation")
    void testToString_returnsNonNull() {
        // Arrange
        doctor.setDocId(1);
        doctor.setDocName("Dr. Test");
        doctor.setSpecialization("General");
        doctor.setIncome(60000.00);

        // Act
        String result = doctor.toString();

        // Assert
        assertNotNull(result, "toString should not return null");
    }

    @Test
    @DisplayName("toString contains field values")
    void testToString_containsFieldValues() {
        // Arrange
        doctor.setDocId(42);
        doctor.setDocName("Dr. House");
        doctor.setSpecialization("Diagnostics");
        doctor.setIncome(200000.00);

        // Act
        String result = doctor.toString();

        // Assert
        assertTrue(result.contains("42"),          "toString should contain docId");
        assertTrue(result.contains("Dr. House"),   "toString should contain docName");
        assertTrue(result.contains("Diagnostics"), "toString should contain specialization");
    }

    // ─── Full Object Population Test ─────────────────────────────────────────

    @Test
    @DisplayName("Fully populated Doctor retains all field values")
    void testFullyPopulatedDoctor_retainsAllValues() {
        // Arrange
        Integer id   = 203;
        String  name = "sairam";
        String  spec = "MD_Cardio";
        Double  inc  = 90000.00;

        // Act
        doctor.setDocId(id);
        doctor.setDocName(name);
        doctor.setSpecialization(spec);
        doctor.setIncome(inc);

        // Assert
        assertAll("All Doctor fields should match",
                () -> assertEquals(id,   doctor.getDocId()),
                () -> assertEquals(name, doctor.getDocName()),
                () -> assertEquals(spec, doctor.getSpecialization()),
                () -> assertEquals(inc,  doctor.getIncome(), 0.001)
        );
    }

    @Test
    @DisplayName("Doctor fields can be updated after initial set")
    void testDoctorFields_canBeUpdated() {
        // Arrange
        doctor.setDocId(1);
        doctor.setDocName("Initial");
        doctor.setSpecialization("General");
        doctor.setIncome(50000.00);

        // Act - update all fields
        doctor.setDocId(2);
        doctor.setDocName("Updated");
        doctor.setSpecialization("Cardiology");
        doctor.setIncome(100000.00);

        // Assert
        assertAll("All Doctor fields should be updated",
                () -> assertEquals(2,           doctor.getDocId()),
                () -> assertEquals("Updated",   doctor.getDocName()),
                () -> assertEquals("Cardiology",doctor.getSpecialization()),
                () -> assertEquals(100000.00,   doctor.getIncome(), 0.001)
        );
    }
}
