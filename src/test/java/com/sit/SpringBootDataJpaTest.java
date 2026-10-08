package com.sit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SpringBootDataJpa main application class.
 * Tests the main class instantiation and basic behavior.
 */
@DisplayName("SpringBootDataJpa Main Class Tests")
class SpringBootDataJpaTest {

    // ─── Main Class Instantiation Tests ──────────────────────────────────────

    @Test
    @DisplayName("SpringBootDataJpa class can be instantiated")
    void springBootDataJpa_shouldBeInstantiable() {
        // Arrange & Act
        SpringBootDataJpa app = new SpringBootDataJpa();

        // Assert
        assertNotNull(app);
    }

    @Test
    @DisplayName("SpringBootDataJpa class is annotated with @SpringBootApplication")
    void springBootDataJpa_shouldHaveSpringBootApplicationAnnotation() {
        // Arrange & Act
        boolean hasAnnotation = SpringBootDataJpa.class
                .isAnnotationPresent(org.springframework.boot.autoconfigure.SpringBootApplication.class);

        // Assert
        assertTrue(hasAnnotation);
    }

    @Test
    @DisplayName("SpringBootDataJpa class is in correct package")
    void springBootDataJpa_shouldBeInCorrectPackage() {
        // Arrange & Act
        String packageName = SpringBootDataJpa.class.getPackageName();

        // Assert
        assertEquals("com.sit", packageName);
    }

    @Test
    @DisplayName("SpringBootDataJpa class name is correct")
    void springBootDataJpa_shouldHaveCorrectClassName() {
        // Arrange & Act
        String className = SpringBootDataJpa.class.getSimpleName();

        // Assert
        assertEquals("SpringBootDataJpa", className);
    }

    @Test
    @DisplayName("SpringBootDataJpa has main method")
    void springBootDataJpa_shouldHaveMainMethod() {
        // Arrange & Act & Assert
        assertDoesNotThrow(() -> {
            SpringBootDataJpa.class.getMethod("main", String[].class);
        });
    }
}
