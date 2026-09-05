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
 * Mocks IDoctorService to test the run() method logic.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("BootDataJPAProj1CrudRepoApp Tests")
class BootDataJPAProj1CrudRepoAppTest {

    @Mock
    private IDoctorService service;

    @InjectMocks
    private BootDataJPAProj1CrudRepoApp bootApp;

    // ─── run() Tests ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("run() calls registerDoctor exactly once")
    void run_noArgs_callsRegisterDoctorOnce() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    @DisplayName("run() passes doctor with correct name to service")
    void run_noArgs_passesDoctorWithCorrectName() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        verify(service).registerDoctor(doctorCaptor.capture());
        Doctor capturedDoctor = doctorCaptor.getValue();
        assertEquals("sairam", capturedDoctor.getDocName());
    }

    @Test
    @DisplayName("run() passes doctor with correct specialization to service")
    void run_noArgs_passesDoctorWithCorrectSpecialization() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        verify(service).registerDoctor(doctorCaptor.capture());
        Doctor capturedDoctor = doctorCaptor.getValue();
        assertEquals("MD_Cardio", capturedDoctor.getSpecialization());
    }

    @Test
    @DisplayName("run() passes doctor with correct income to service")
    void run_noArgs_passesDoctorWithCorrectIncome() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        verify(service).registerDoctor(doctorCaptor.capture());
        Doctor capturedDoctor = doctorCaptor.getValue();
        assertEquals(90000.00, capturedDoctor.getIncome());
    }

    @Test
    @DisplayName("run() with empty args array executes without exception")
    void run_emptyArgs_executesWithoutException() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act & Assert
        assertDoesNotThrow(() -> bootApp.run(new String[]{}));
    }

    @Test
    @DisplayName("run() with multiple args still calls registerDoctor once")
    void run_multipleArgs_callsRegisterDoctorOnce() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run("arg1", "arg2", "arg3");

        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    @DisplayName("run() when service throws exception does not propagate (caught internally)")
    void run_serviceThrowsException_exceptionCaughtInternally() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenThrow(new RuntimeException("Service error"));

        // Act & Assert - exception is caught inside run() try-catch block
        assertDoesNotThrow(() -> bootApp.run());
    }

    @Test
    @DisplayName("run() when service throws RuntimeException still completes")
    void run_serviceThrowsRuntimeException_runCompletes() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenThrow(new RuntimeException("DB error"));

        // Act & Assert
        assertDoesNotThrow(() -> bootApp.run("someArg"));
    }

    @Test
    @DisplayName("BootDataJPAProj1CrudRepoApp implements CommandLineRunner")
    void bootApp_implementsCommandLineRunner() {
        // Assert
        assertTrue(bootApp instanceof org.springframework.boot.CommandLineRunner);
    }

    @Test
    @DisplayName("BootDataJPAProj1CrudRepoApp instance is not null after injection")
    void bootApp_afterInjection_isNotNull() {
        // Assert
        assertNotNull(bootApp);
    }

    @Test
    @DisplayName("run() passes non-null Doctor object to service")
    void run_noArgs_passesNonNullDoctorToService() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        verify(service).registerDoctor(doctorCaptor.capture());
        assertNotNull(doctorCaptor.getValue());
    }
}
