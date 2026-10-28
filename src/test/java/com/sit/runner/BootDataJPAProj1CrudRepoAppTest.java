package com.sit.runner;

import com.sit.Service.IDoctorService;
import com.sit.entity.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for BootDataJPAProj1CrudRepoApp (CommandLineRunner).
 * Tests the run() method and its interaction with IDoctorService.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("BootDataJPAProj1CrudRepoApp Tests")
class BootDataJPAProj1CrudRepoAppTest {

    @Mock
    private IDoctorService service;

    @InjectMocks
    private BootDataJPAProj1CrudRepoApp bootApp;

    // ─── run() Method Tests ──────────────────────────────────────────────────

    @Test
    @DisplayName("run() calls registerDoctor exactly once")
    void testRun_callsRegisterDoctorOnce() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    @DisplayName("run() creates Doctor with correct name 'sairam'")
    void testRun_createsDoctorWithCorrectName() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        verify(service).registerDoctor(doctorCaptor.capture());
        assertEquals("sairam", doctorCaptor.getValue().getDocName());
    }

    @Test
    @DisplayName("run() creates Doctor with correct specialization 'MD_Cardio'")
    void testRun_createsDoctorWithCorrectSpecialization() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        verify(service).registerDoctor(doctorCaptor.capture());
        assertEquals("MD_Cardio", doctorCaptor.getValue().getSpecialization());
    }

    @Test
    @DisplayName("run() creates Doctor with correct income 90000.00")
    void testRun_createsDoctorWithCorrectIncome() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        verify(service).registerDoctor(doctorCaptor.capture());
        assertEquals(90000.00, doctorCaptor.getValue().getIncome());
    }

    @Test
    @DisplayName("run() does not throw exception when service succeeds")
    void testRun_doesNotThrowException_whenServiceSucceeds() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act & Assert
        assertDoesNotThrow(() -> bootApp.run());
    }

    @Test
    @DisplayName("run() handles exception from service gracefully (catches internally)")
    void testRun_handlesServiceException_gracefully() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenThrow(new RuntimeException("Service error"));

        // Act & Assert - run() catches exceptions internally, so no exception should propagate
        assertDoesNotThrow(() -> bootApp.run());
    }

    @Test
    @DisplayName("run() with empty args array does not throw")
    void testRun_withEmptyArgs_doesNotThrow() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act & Assert
        assertDoesNotThrow(() -> bootApp.run(new String[]{}));
    }

    @Test
    @DisplayName("run() with non-empty args array does not throw")
    void testRun_withNonEmptyArgs_doesNotThrow() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act & Assert
        assertDoesNotThrow(() -> bootApp.run("arg1", "arg2"));
    }

    @Test
    @DisplayName("run() creates Doctor with null docId (not set in run)")
    void testRun_createsDoctorWithNullDocId() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        verify(service).registerDoctor(doctorCaptor.capture());
        assertNull(doctorCaptor.getValue().getDocId());
    }

    @Test
    @DisplayName("BootDataJPAProj1CrudRepoApp is not null after injection")
    void testBootApp_isNotNull() {
        // Assert
        assertNotNull(bootApp);
    }

    @Test
    @DisplayName("run() verifies no other interactions with service")
    void testRun_noOtherServiceInteractions() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("run() handles NullPointerException from service gracefully")
    void testRun_handlesNullPointerException_gracefully() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenThrow(new NullPointerException("Null pointer"));

        // Act & Assert - run() catches all exceptions internally
        assertDoesNotThrow(() -> bootApp.run());
    }

    @Test
    @DisplayName("run() handles IllegalArgumentException from service gracefully")
    void testRun_handlesIllegalArgumentException_gracefully() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenThrow(new IllegalArgumentException("Invalid argument"));

        // Act & Assert
        assertDoesNotThrow(() -> bootApp.run());
    }

    @Test
    @DisplayName("run() creates a Doctor object (not null) to pass to service")
    void testRun_createsDoctorObject_notNull() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        verify(service).registerDoctor(doctorCaptor.capture());
        assertNotNull(doctorCaptor.getValue());
    }

    @Test
    @DisplayName("BootDataJPAProj1CrudRepoApp implements CommandLineRunner")
    void testBootApp_implementsCommandLineRunner() {
        // Assert
        assertTrue(bootApp instanceof org.springframework.boot.CommandLineRunner);
    }

    @Test
    @DisplayName("run() with multiple args does not affect behavior")
    void testRun_withMultipleArgs_doesNotAffectBehavior() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run("arg1", "arg2", "arg3");

        // Assert
        verify(service).registerDoctor(doctorCaptor.capture());
        assertEquals("sairam", doctorCaptor.getValue().getDocName());
        assertEquals("MD_Cardio", doctorCaptor.getValue().getSpecialization());
        assertEquals(90000.00, doctorCaptor.getValue().getIncome());
    }
}
