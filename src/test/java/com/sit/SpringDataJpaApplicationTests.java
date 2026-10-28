package com.sit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Application-level tests for SpringBootDataJpa.
 * Tests the main application class without requiring a full Spring context
 * (which would need a live database connection).
 */
@DisplayName("SpringBootDataJpa Application Tests")
class SpringDataJpaApplicationTests {

    @Test
    @DisplayName("SpringBootDataJpa class can be instantiated")
    void testSpringBootDataJpa_canBeInstantiated() {
        // Arrange & Act
        SpringBootDataJpa app = new SpringBootDataJpa();

        // Assert
        assertNotNull(app);
    }

    @Test
    @DisplayName("Application class is not null")
    void testApplicationClass_isNotNull() {
        // Assert
        assertNotNull(SpringBootDataJpa.class);
    }

    @Test
    @DisplayName("Application class name is correct")
    void testApplicationClass_hasCorrectName() {
        // Assert
        assertNotNull(SpringBootDataJpa.class.getName());
        assertEquals("com.sit.SpringBootDataJpa",
                SpringBootDataJpa.class.getName());
    }

    @Test
    @DisplayName("Application class simple name is correct")
    void testApplicationClass_hasCorrectSimpleName() {
        // Assert
        assertEquals("SpringBootDataJpa", SpringBootDataJpa.class.getSimpleName());
    }

    @Test
    @DisplayName("Application class package is correct")
    void testApplicationClass_hasCorrectPackage() {
        // Assert
        assertEquals("com.sit", SpringBootDataJpa.class.getPackageName());
    }

    @Test
    @DisplayName("SpringBootDataJpa has SpringBootApplication annotation")
    void testApplicationClass_hasSpringBootApplicationAnnotation() {
        // Assert
        assertTrue(SpringBootDataJpa.class.isAnnotationPresent(
                org.springframework.boot.autoconfigure.SpringBootApplication.class));
    }

    @Test
    @DisplayName("SpringBootDataJpa instance is of correct type")
    void testApplicationInstance_isCorrectType() {
        // Arrange & Act
        SpringBootDataJpa app = new SpringBootDataJpa();

        // Assert
        assertTrue(app instanceof SpringBootDataJpa);
    }

    @Test
    @DisplayName("Two SpringBootDataJpa instances are not null")
    void testTwoInstances_areNotNull() {
        // Arrange & Act
        SpringBootDataJpa app1 = new SpringBootDataJpa();
        SpringBootDataJpa app2 = new SpringBootDataJpa();

        // Assert
        assertNotNull(app1);
        assertNotNull(app2);
    }
}
