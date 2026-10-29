package com.sit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SpringBootDataJpa Main Class Tests")
class SpringBootDataJpaTest {

    // ─── Constructor Tests ───────────────────────────────────────────────────

    @Test
    @DisplayName("Default constructor creates non-null instance")
    void defaultConstructor_createsNonNullInstance() {
        // Arrange & Act
        SpringBootDataJpa app = new SpringBootDataJpa();

        // Assert
        assertNotNull(app);
    }

    @Test
    @DisplayName("SpringBootDataJpa class is instantiable")
    void springBootDataJpa_isInstantiable() {
        // Arrange & Act
        SpringBootDataJpa instance = new SpringBootDataJpa();

        // Assert
        assertNotNull(instance);
        assertInstanceOf(SpringBootDataJpa.class, instance);
    }
}
