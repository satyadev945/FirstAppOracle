package com.sit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Spring Boot context load test.
 * Uses H2 in-memory database so no real Oracle/MySQL is required.
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class SpringDataJpaApplicationTests {

    @Test
    void contextLoads() {
        // Verifies that the Spring application context starts without errors
        assertTrue(true, "Application context loaded successfully");
    }
}
