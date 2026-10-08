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
 * Unit tests for BootDataJPAProj1CrudRepoApp CommandLineRunner.
 * Verifies that the run() method correctly creates a Doctor and calls the service.
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
    @DisplayName("run calls service.registerDoctor exactly once")
    void testRun_callsRegisterDoctorOnce() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    @DisplayName("run creates Doctor with correct name 'sairam'")
    void testRun_createsDoctorWithCorrectName() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        verify(service).registerDoctor(doctorCaptor.capture());
        assertEquals("sairam", doctorCaptor.getValue().getDocName(),
                "Doctor name should be 'sairam'");
    }

    @Test
    @DisplayName("run creates Doctor with correct specialization 'MD_Cardio'")
    void testRun_createsDoctorWithCorrectSpecialization() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        verify(service).registerDoctor(doctorCaptor.capture());
        assertEquals("MD_Cardio", doctorCaptor.getValue().getSpecialization(),
                "Doctor specialization should be 'MD_Cardio'");
    }

    @Test
    @DisplayName("run creates Doctor with correct income 90000.00")
    void testRun_createsDoctorWithCorrectIncome() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        verify(service).registerDoctor(doctorCaptor.capture());
        assertEquals(90000.00, doctorCaptor.getValue().getIncome(), 0.001,
                "Doctor income should be 90000.00");
    }

    @Test
    @DisplayName("run does not throw exception when service succeeds")
    void testRun_doesNotThrowWhenServiceSucceeds() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act & Assert
        assertDoesNotThrow(() -> bootApp.run(),
                "run() should not throw when service succeeds");
    }

    @Test
    @DisplayName("run handles service exception gracefully without propagating")
    void testRun_handlesServiceExceptionGracefully() {
        // Arrange - service throws RuntimeException
        when(service.registerDoctor(any(Doctor.class)))
                .thenThrow(new RuntimeException("DB error"));

        // Act & Assert - exception should be caught internally (try-catch in run())
        assertDoesNotThrow(() -> bootApp.run(),
                "run() should catch and handle exceptions internally");
    }

    @Test
    @DisplayName("run does not call service more than once")
    void testRun_doesNotCallServiceMoreThanOnce() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        verify(service, atMostOnce()).registerDoctor(any(Doctor.class));
    }

    @Test
    @DisplayName("run with empty args array executes without error")
    void testRun_withEmptyArgs_executesWithoutError() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act & Assert
        assertDoesNotThrow(() -> bootApp.run(new String[]{}),
                "run() with empty args should not throw");
    }

    @Test
    @DisplayName("run with multiple args executes without error")
    void testRun_withMultipleArgs_executesWithoutError() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act & Assert
        assertDoesNotThrow(() -> bootApp.run("arg1", "arg2", "arg3"),
                "run() with multiple args should not throw");
    }

    @Test
    @DisplayName("run verifies no other interactions with service")
    void testRun_noOtherServiceInteractions() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
        verifyNoMoreInteractions(service);
    }

    // ─── CommandLineRunner contract ───────────────────────────────────────────

    @Test
    @DisplayName("BootDataJPAProj1CrudRepoApp implements CommandLineRunner")
    void testBootApp_implementsCommandLineRunner() {
        // Assert
        assertTrue(bootApp instanceof org.springframework.boot.CommandLineRunner,
                "BootDataJPAProj1CrudRepoApp should implement CommandLineRunner");
    }
}
