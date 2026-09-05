package com.sit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration / context load tests for SpringBootDataJpa main application class.
 * Uses H2 in-memory database to avoid requiring a real PostgreSQL connection.
 */
@SpringBootTest
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.show-sql=false"
})
@DisplayName("SpringBootDataJpa Application Tests")
class SpringBootDataJpaTest {

    @Test
    @DisplayName("Spring application context loads successfully")
    void contextLoads() {
        // If the context loads without exception, the test passes
        assertTrue(true, "Application context should load without errors");
    }

    @Test
    @DisplayName("SpringBootDataJpa class is not null when instantiated")
    void springBootDataJpa_instantiation_isNotNull() {
        // Arrange & Act
        SpringBootDataJpa app = new SpringBootDataJpa();

        // Assert
        assertNotNull(app);
    }
}
