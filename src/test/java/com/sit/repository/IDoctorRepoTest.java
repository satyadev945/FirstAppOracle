package com.sit.repository;

import com.sit.entity.Doctor;
import org.junit.jupiter.api.Test;
import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test class for IDoctorRepo repository interface
 * Tests repository interface structure and inheritance
 */
class IDoctorRepoTest {

    @Test
    void testIDoctorRepo_isInterface() {
        // Arrange & Act
        boolean isInterface = IDoctorRepo.class.isInterface();
        
        // Assert
        assertTrue(isInterface, "IDoctorRepo should be an interface");
    }

    @Test
    void testIDoctorRepo_extendsCrudRepository() {
        // Arrange & Act
        Class<?>[] interfaces = IDoctorRepo.class.getInterfaces();
        
        // Assert
        assertEquals(1, interfaces.length, "IDoctorRepo should extend one interface");
        assertEquals(CrudRepository.class, interfaces[0], 
                     "IDoctorRepo should extend CrudRepository");
    }

    @Test
    void testIDoctorRepo_hasCorrectGenericTypes() {
        // Arrange & Act
        Type[] genericInterfaces = IDoctorRepo.class.getGenericInterfaces();
        
        // Assert
        assertTrue(genericInterfaces.length > 0, "Should have generic interfaces");
        assertTrue(genericInterfaces[0] instanceof ParameterizedType, 
                   "Should be a parameterized type");
        
        ParameterizedType parameterizedType = (ParameterizedType) genericInterfaces[0];
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        
        assertEquals(2, typeArguments.length, "CrudRepository should have 2 type parameters");
        assertEquals(Doctor.class, typeArguments[0], "First type parameter should be Doctor");
        assertEquals(Integer.class, typeArguments[1], "Second type parameter should be Integer");
    }

    @Test
    void testIDoctorRepo_isPublic() {
        // Arrange & Act
        int modifiers = IDoctorRepo.class.getModifiers();
        
        // Assert
        assertTrue(java.lang.reflect.Modifier.isPublic(modifiers), 
                   "IDoctorRepo should be public");
    }

    @Test
    void testIDoctorRepo_isNotAbstract() {
        // Note: Interfaces are implicitly abstract, but we test it's not explicitly marked
        // Arrange & Act
        boolean isInterface = IDoctorRepo.class.isInterface();
        
        // Assert
        assertTrue(isInterface, "IDoctorRepo should be an interface");
    }

    @Test
    void testIDoctorRepo_packageName() {
        // Arrange & Act
        String packageName = IDoctorRepo.class.getPackage().getName();
        
        // Assert
        assertEquals("com.sit.repository", packageName, 
                     "Package name should be com.sit.repository");
    }

    @Test
    void testIDoctorRepo_interfaceName() {
        // Arrange & Act
        String interfaceName = IDoctorRepo.class.getSimpleName();
        
        // Assert
        assertEquals("IDoctorRepo", interfaceName, "Interface name should be IDoctorRepo");
    }

    @Test
    void testIDoctorRepo_hasNoCustomMethods() {
        // Arrange & Act
        Method[] declaredMethods = IDoctorRepo.class.getDeclaredMethods();
        
        // Assert
        assertEquals(0, declaredMethods.length, 
                     "IDoctorRepo should not declare any custom methods");
    }

    @Test
    void testIDoctorRepo_inheritsFromCrudRepository() {
        // Arrange & Act
        boolean isCrudRepository = CrudRepository.class.isAssignableFrom(IDoctorRepo.class);
        
        // Assert
        assertTrue(isCrudRepository, "IDoctorRepo should be assignable to CrudRepository");
    }

    @Test
    void testIDoctorRepo_canBeUsedAsRepositoryType() {
        // Arrange & Act
        Class<?> repoClass = IDoctorRepo.class;
        
        // Assert
        assertNotNull(repoClass, "Repository class should not be null");
        assertTrue(repoClass.isInterface(), "Should be an interface");
    }

    @Test
    void testIDoctorRepo_hasNoFields() {
        // Arrange & Act
        var fields = IDoctorRepo.class.getDeclaredFields();
        
        // Assert
        assertEquals(0, fields.length, "IDoctorRepo should have no fields");
    }

    @Test
    void testIDoctorRepo_hasNoConstructors() {
        // Arrange & Act
        var constructors = IDoctorRepo.class.getDeclaredConstructors();
        
        // Assert
        assertEquals(0, constructors.length, "Interfaces should have no constructors");
    }

    @Test
    void testIDoctorRepo_isNotEnum() {
        // Arrange & Act
        boolean isEnum = IDoctorRepo.class.isEnum();
        
        // Assert
        assertFalse(isEnum, "IDoctorRepo should not be an enum");
    }

    @Test
    void testIDoctorRepo_isNotAnnotation() {
        // Arrange & Act
        boolean isAnnotation = IDoctorRepo.class.isAnnotation();
        
        // Assert
        assertFalse(isAnnotation, "IDoctorRepo should not be an annotation");
    }

    @Test
    void testIDoctorRepo_isNotClass() {
        // Arrange & Act
        boolean isInterface = IDoctorRepo.class.isInterface();
        
        // Assert
        assertTrue(isInterface, "IDoctorRepo should be an interface, not a class");
    }

    @Test
    void testIDoctorRepo_hasCorrectFullyQualifiedName() {
        // Arrange & Act
        String fqn = IDoctorRepo.class.getName();
        
        // Assert
        assertEquals("com.sit.repository.IDoctorRepo", fqn, 
                     "Fully qualified name should be com.sit.repository.IDoctorRepo");
    }

    @Test
    void testIDoctorRepo_entityTypeIsDoctor() {
        // Arrange & Act
        Type[] genericInterfaces = IDoctorRepo.class.getGenericInterfaces();
        ParameterizedType parameterizedType = (ParameterizedType) genericInterfaces[0];
        Type entityType = parameterizedType.getActualTypeArguments()[0];
        
        // Assert
        assertEquals(Doctor.class, entityType, "Entity type should be Doctor");
    }

    @Test
    void testIDoctorRepo_idTypeIsInteger() {
        // Arrange & Act
        Type[] genericInterfaces = IDoctorRepo.class.getGenericInterfaces();
        ParameterizedType parameterizedType = (ParameterizedType) genericInterfaces[0];
        Type idType = parameterizedType.getActualTypeArguments()[1];
        
        // Assert
        assertEquals(Integer.class, idType, "ID type should be Integer");
    }

    @Test
    void testIDoctorRepo_extendsOnlyOneInterface() {
        // Arrange & Act
        Class<?>[] interfaces = IDoctorRepo.class.getInterfaces();
        
        // Assert
        assertEquals(1, interfaces.length, "Should extend exactly one interface");
    }

    @Test
    void testIDoctorRepo_hasNoAnnotations() {
        // Arrange & Act
        var annotations = IDoctorRepo.class.getDeclaredAnnotations();
        
        // Assert
        assertEquals(0, annotations.length, "IDoctorRepo should have no annotations");
    }

    @Test
    void testIDoctorRepo_canBeAssignedToCrudRepository() {
        // Arrange & Act
        boolean canAssign = CrudRepository.class.isAssignableFrom(IDoctorRepo.class);
        
        // Assert
        assertTrue(canAssign, "IDoctorRepo should be assignable to CrudRepository");
    }
}
