package com.sit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Integration tests for SpringBootDataJpa application context.
 * Uses H2 in-memory database configured in test/resources/application.properties.
 */
@SpringBootTest(
    classes = SpringBootDataJpa.class,
    webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@TestPropertySource(locations = "classpath:application.properties")
@DisplayName("SpringBootDataJpa Application Context Tests")
class SpringDataJpaApplicationTests {

    @Test
    @DisplayName("Spring application context loads successfully")
    void contextLoads() {
        // Verifies that the Spring application context starts without errors
    }

    @Test
    @DisplayName("Application main method runs without throwing exception")
    void main_withEmptyArgs_runsWithoutException() {
        // Act & Assert
        assertDoesNotThrow(() ->
            SpringBootDataJpa.main(new String[]{})
        );
    }
}
