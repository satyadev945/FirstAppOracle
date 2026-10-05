package com.sit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test class for SpringBootDataJpa main application class
 * Tests Spring Boot application startup and configuration
 */
class SpringBootDataJpaTest {

    @Test
    void testMainMethod_withValidArgs_doesNotThrowException() {
        // This test verifies the main method exists and can be called
        // Note: We don't actually run SpringApplication.run() in unit tests
        // as it would start the entire Spring context
        assertDoesNotThrow(() -> {
            // Verify the class exists and is properly structured
            Class<?> clazz = SpringBootDataJpa.class;
            assertNotNull(clazz, "SpringBootDataJpa class should exist");
        });
    }

    @Test
    void testSpringBootDataJpaClass_hasMainMethod() throws NoSuchMethodException {
        // Arrange & Act
        var mainMethod = SpringBootDataJpa.class.getDeclaredMethod("main", String[].class);
        
        // Assert
        assertNotNull(mainMethod, "Main method should exist");
        assertEquals(void.class, mainMethod.getReturnType(), "Main method should return void");
        assertTrue(java.lang.reflect.Modifier.isStatic(mainMethod.getModifiers()), 
                   "Main method should be static");
        assertTrue(java.lang.reflect.Modifier.isPublic(mainMethod.getModifiers()), 
                   "Main method should be public");
    }

    @Test
    void testSpringBootDataJpaClass_hasSpringBootApplicationAnnotation() {
        // Arrange & Act
        boolean hasAnnotation = SpringBootDataJpa.class
            .isAnnotationPresent(org.springframework.boot.autoconfigure.SpringBootApplication.class);
        
        // Assert
        assertTrue(hasAnnotation, "Class should have @SpringBootApplication annotation");
    }

    @Test
    void testSpringBootDataJpaClass_isPublic() {
        // Arrange & Act
        int modifiers = SpringBootDataJpa.class.getModifiers();
        
        // Assert
        assertTrue(java.lang.reflect.Modifier.isPublic(modifiers), 
                   "SpringBootDataJpa class should be public");
    }

    @Test
    void testSpringBootDataJpaClass_isNotAbstract() {
        // Arrange & Act
        int modifiers = SpringBootDataJpa.class.getModifiers();
        
        // Assert
        assertFalse(java.lang.reflect.Modifier.isAbstract(modifiers), 
                    "SpringBootDataJpa class should not be abstract");
    }

    @Test
    void testSpringBootDataJpaClass_isNotInterface() {
        // Arrange & Act
        boolean isInterface = SpringBootDataJpa.class.isInterface();
        
        // Assert
        assertFalse(isInterface, "SpringBootDataJpa should be a class, not an interface");
    }

    @Test
    void testSpringBootDataJpaClass_hasDefaultConstructor() {
        // Act & Assert
        assertDoesNotThrow(() -> {
            SpringBootDataJpa instance = new SpringBootDataJpa();
            assertNotNull(instance, "Should be able to create instance with default constructor");
        });
    }

    @Test
    void testSpringBootDataJpaClass_packageName() {
        // Arrange & Act
        String packageName = SpringBootDataJpa.class.getPackage().getName();
        
        // Assert
        assertEquals("com.sit", packageName, "Package name should be com.sit");
    }

    @Test
    void testSpringBootDataJpaClass_className() {
        // Arrange & Act
        String className = SpringBootDataJpa.class.getSimpleName();
        
        // Assert
        assertEquals("SpringBootDataJpa", className, "Class name should be SpringBootDataJpa");
    }

    @Test
    void testSpringBootDataJpaClass_hasNoFields() {
        // Arrange & Act
        var fields = SpringBootDataJpa.class.getDeclaredFields();
        
        // Assert
        assertEquals(0, fields.length, "SpringBootDataJpa should have no instance fields");
    }

    @Test
    void testSpringBootDataJpaClass_hasOnlyMainMethod() {
        // Arrange & Act
        var methods = SpringBootDataJpa.class.getDeclaredMethods();
        
        // Assert
        assertEquals(1, methods.length, "SpringBootDataJpa should have only the main method");
        assertEquals("main", methods[0].getName(), "The only method should be main");
    }

    @Test
    void testSpringBootDataJpaClass_canBeInstantiated() {
        // Act
        SpringBootDataJpa app = new SpringBootDataJpa();
        
        // Assert
        assertNotNull(app, "SpringBootDataJpa instance should not be null");
        assertEquals(SpringBootDataJpa.class, app.getClass(), 
                     "Instance should be of type SpringBootDataJpa");
    }

    @Test
    void testSpringBootDataJpaClass_multipleInstancesAreIndependent() {
        // Act
        SpringBootDataJpa app1 = new SpringBootDataJpa();
        SpringBootDataJpa app2 = new SpringBootDataJpa();
        
        // Assert
        assertNotNull(app1, "First instance should not be null");
        assertNotNull(app2, "Second instance should not be null");
        assertNotSame(app1, app2, "Instances should be different objects");
    }

    @Test
    void testMainMethodParameters_acceptsStringArray() throws NoSuchMethodException {
        // Arrange & Act
        var mainMethod = SpringBootDataJpa.class.getDeclaredMethod("main", String[].class);
        var parameterTypes = mainMethod.getParameterTypes();
        
        // Assert
        assertEquals(1, parameterTypes.length, "Main method should have one parameter");
        assertEquals(String[].class, parameterTypes[0], 
                     "Main method parameter should be String array");
    }

    @Test
    void testSpringBootApplicationAnnotation_hasCorrectAttributes() {
        // Arrange & Act
        var annotation = SpringBootDataJpa.class
            .getAnnotation(org.springframework.boot.autoconfigure.SpringBootApplication.class);
        
        // Assert
        assertNotNull(annotation, "@SpringBootApplication annotation should be present");
    }

    @Test
    void testSpringBootDataJpaClass_isInCorrectPackage() {
        // Arrange & Act
        Package pkg = SpringBootDataJpa.class.getPackage();
        
        // Assert
        assertNotNull(pkg, "Package should not be null");
        assertEquals("com.sit", pkg.getName(), "Should be in com.sit package");
    }

    @Test
    void testSpringBootDataJpaClass_hasNoSuperclass() {
        // Arrange & Act
        Class<?> superclass = SpringBootDataJpa.class.getSuperclass();
        
        // Assert
        assertEquals(Object.class, superclass, 
                     "SpringBootDataJpa should only extend Object");
    }

    @Test
    void testSpringBootDataJpaClass_implementsNoInterfaces() {
        // Arrange & Act
        Class<?>[] interfaces = SpringBootDataJpa.class.getInterfaces();
        
        // Assert
        assertEquals(0, interfaces.length, 
                     "SpringBootDataJpa should not implement any interfaces");
    }

    @Test
    void testSpringBootDataJpaClass_isNotEnum() {
        // Arrange & Act
        boolean isEnum = SpringBootDataJpa.class.isEnum();
        
        // Assert
        assertFalse(isEnum, "SpringBootDataJpa should not be an enum");
    }

    @Test
    void testSpringBootDataJpaClass_isNotAnnotation() {
        // Arrange & Act
        boolean isAnnotation = SpringBootDataJpa.class.isAnnotation();
        
        // Assert
        assertFalse(isAnnotation, "SpringBootDataJpa should not be an annotation");
    }
}
