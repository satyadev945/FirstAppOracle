package com.sit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Spring Boot integration test - verifies application context loads.
 */
@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Spring Data JPA Application Context Tests")
class SpringDataJpaApplicationTests {

    @Test
    @DisplayName("Application context loads without errors")
    void contextLoads() {
        assertTrue(true, "Application context should load successfully");
    }
}
