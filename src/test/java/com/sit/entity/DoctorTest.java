package com.sit.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Doctor entity class.
 * Tests all getters, setters, constructors, and Lombok-generated methods.
 */
class DoctorTest {

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
    }

    // ─── Constructor Tests ───────────────────────────────────────────────────

    @Test
    void testDefaultConstructor_createsNonNullInstance() {
        // Arrange & Act
        Doctor doc = new Doctor();

        // Assert
        assertNotNull(doc);
    }

    @Test
    void testDefaultConstructor_fieldsAreNullByDefault() {
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
    void testSetAndGetDocId_withValidId() {
        // Arrange
        Integer expectedId = 101;

        // Act
        doctor.setDocId(expectedId);

        // Assert
        assertEquals(expectedId, doctor.getDocId());
    }

    @Test
    void testSetAndGetDocId_withNullId() {
        // Arrange & Act
        doctor.setDocId(null);

        // Assert
        assertNull(doctor.getDocId());
    }

    @Test
    void testSetAndGetDocName_withValidName() {
        // Arrange
        String expectedName = "Dr. Smith";

        // Act
        doctor.setDocName(expectedName);

        // Assert
        assertEquals(expectedName, doctor.getDocName());
    }

    @Test
    void testSetAndGetDocName_withNullName() {
        // Arrange & Act
        doctor.setDocName(null);

        // Assert
        assertNull(doctor.getDocName());
    }

    @Test
    void testSetAndGetDocName_withEmptyString() {
        // Arrange & Act
        doctor.setDocName("");

        // Assert
        assertEquals("", doctor.getDocName());
    }

    @Test
    void testSetAndGetDocName_withMaxLengthName() {
        // Arrange – column length is 25
        String maxName = "ABCDEFGHIJKLMNOPQRSTUVWXY";

        // Act
        doctor.setDocName(maxName);

        // Assert
        assertEquals(maxName, doctor.getDocName());
        assertEquals(25, doctor.getDocName().length());
    }

    @Test
    void testSetAndGetSpecialization_withValidValue() {
        // Arrange
        String expectedSpec = "MD_Cardio";

        // Act
        doctor.setSpecialization(expectedSpec);

        // Assert
        assertEquals(expectedSpec, doctor.getSpecialization());
    }

    @Test
    void testSetAndGetSpecialization_withNullValue() {
        // Arrange & Act
        doctor.setSpecialization(null);

        // Assert
        assertNull(doctor.getSpecialization());
    }

    @Test
    void testSetAndGetSpecialization_withEmptyString() {
        // Arrange & Act
        doctor.setSpecialization("");

        // Assert
        assertEquals("", doctor.getSpecialization());
    }

    @Test
    void testSetAndGetIncome_withPositiveValue() {
        // Arrange
        Double expectedIncome = 90000.00;

        // Act
        doctor.setIncome(expectedIncome);

        // Assert
        assertEquals(expectedIncome, doctor.getIncome());
    }

    @Test
    void testSetAndGetIncome_withZeroValue() {
        // Arrange & Act
        doctor.setIncome(0.0);

        // Assert
        assertEquals(0.0, doctor.getIncome());
    }

    @Test
    void testSetAndGetIncome_withNullValue() {
        // Arrange & Act
        doctor.setIncome(null);

        // Assert
        assertNull(doctor.getIncome());
    }

    @Test
    void testSetAndGetIncome_withNegativeValue() {
        // Arrange & Act
        doctor.setIncome(-5000.00);

        // Assert
        assertEquals(-5000.00, doctor.getIncome());
    }

    @Test
    void testSetAndGetIncome_withLargeValue() {
        // Arrange & Act
        doctor.setIncome(Double.MAX_VALUE);

        // Assert
        assertEquals(Double.MAX_VALUE, doctor.getIncome());
    }

    // ─── Lombok @Data – equals / hashCode / toString ────────────────────────

    @Test
    void testEquals_twoIdenticalDoctors_areEqual() {
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
    void testEquals_twoDifferentDoctors_areNotEqual() {
        // Arrange
        Doctor doc1 = new Doctor();
        doc1.setDocId(1);
        doc1.setDocName("sairam");

        Doctor doc2 = new Doctor();
        doc2.setDocId(2);
        doc2.setDocName("john");

        // Act & Assert
        assertNotEquals(doc1, doc2);
    }

    @Test
    void testEquals_sameInstance_isEqual() {
        // Arrange
        doctor.setDocId(1);

        // Act & Assert
        assertEquals(doctor, doctor);
    }

    @Test
    void testEquals_withNull_isNotEqual() {
        // Arrange
        doctor.setDocId(1);

        // Act & Assert
        assertNotEquals(null, doctor);
    }

    @Test
    void testHashCode_equalDoctors_haveSameHashCode() {
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
    void testToString_containsDoctorFields() {
        // Arrange
        doctor.setDocId(1);
        doctor.setDocName("sairam");
        doctor.setSpecialization("MD_Cardio");
        doctor.setIncome(90000.00);

        // Act
        String result = doctor.toString();

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("sairam"));
        assertTrue(result.contains("MD_Cardio"));
    }

    @Test
    void testToString_withNullFields_doesNotThrow() {
        // Arrange – all fields null by default

        // Act & Assert
        assertDoesNotThrow(() -> doctor.toString());
    }

    // ─── Full Population Test ────────────────────────────────────────────────

    @Test
    void testFullyPopulatedDoctor_allFieldsCorrect() {
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
        assertAll(
            () -> assertEquals(id, doctor.getDocId()),
            () -> assertEquals(name, doctor.getDocName()),
            () -> assertEquals(spec, doctor.getSpecialization()),
            () -> assertEquals(income, doctor.getIncome())
        );
    }
}
