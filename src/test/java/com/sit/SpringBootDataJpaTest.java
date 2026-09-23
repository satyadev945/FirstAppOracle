package com.sit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration / context load tests for SpringBootDataJpa main application class.
 * Uses H2 in-memory database to avoid Oracle dependency during tests.
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@DisplayName("SpringBootDataJpa Application Tests")
class SpringBootDataJpaTest {

    @Test
    @DisplayName("Spring application context loads successfully")
    void contextLoads() {
        // If this test passes, the Spring context loaded without errors
        assertTrue(true, "Application context should load successfully");
    }

    @Test
    @DisplayName("SpringBootDataJpa class is instantiable")
    void testSpringBootDataJpa_isInstantiable() {
        // Arrange & Act
        SpringBootDataJpa app = new SpringBootDataJpa();

        // Assert
        assertNotNull(app);
    }
}
