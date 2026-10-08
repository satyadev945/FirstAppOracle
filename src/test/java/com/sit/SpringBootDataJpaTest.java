package com.sit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for SpringBootDataJpa main application class.
 * Verifies Spring context loads correctly and main class behaviour.
 */
@SpringBootTest
@ActiveProfiles("test")
@DisplayName("SpringBootDataJpa Application Tests")
class SpringBootDataJpaTest {

    // ─── Context Load Test ────────────────────────────────────────────────────

    @Test
    @DisplayName("Spring application context loads successfully")
    void contextLoads() {
        // If this test passes, the Spring context loaded without errors
        assertTrue(true, "Spring context should load without errors");
    }

    // ─── Main Class Tests ─────────────────────────────────────────────────────

    @Test
    @DisplayName("SpringBootDataJpa class is not null")
    void testSpringBootDataJpaClass_isNotNull() {
        // Arrange & Act
        SpringBootDataJpa app = new SpringBootDataJpa();

        // Assert
        assertNotNull(app, "SpringBootDataJpa instance should not be null");
    }

    @Test
    @DisplayName("SpringBootDataJpa is annotated with @SpringBootApplication")
    void testSpringBootDataJpa_hasSpringBootApplicationAnnotation() {
        // Assert
        assertTrue(
                SpringBootDataJpa.class.isAnnotationPresent(
                        org.springframework.boot.autoconfigure.SpringBootApplication.class),
                "SpringBootDataJpa should be annotated with @SpringBootApplication"
        );
    }
}
