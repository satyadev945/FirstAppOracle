package com.sit.runner;

import com.sit.Service.IDoctorService;
import com.sit.entity.Doctor;
import org.junit.jupiter.api.BeforeEach;
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
 * Verifies that the run() method correctly builds a Doctor and delegates to IDoctorService.
 */
@ExtendWith(MockitoExtension.class)
class BootDataJPAProj1CrudRepoAppTest {

    @Mock
    private IDoctorService service;

    @InjectMocks
    private BootDataJPAProj1CrudRepoApp runner;

    // ─── run() – happy path ──────────────────────────────────────────────────

    @Test
    void run_withNoArgs_callsRegisterDoctorOnce() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        runner.run();

        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    void run_withNoArgs_passesCorrectDoctorName() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> captor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(captor.capture()))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        runner.run();

        // Assert
        Doctor captured = captor.getValue();
        assertEquals("sairam", captured.getDocName());
    }

    @Test
    void run_withNoArgs_passesCorrectSpecialization() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> captor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(captor.capture()))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        runner.run();

        // Assert
        Doctor captured = captor.getValue();
        assertEquals("MD_Cardio", captured.getSpecialization());
    }

    @Test
    void run_withNoArgs_passesCorrectIncome() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> captor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(captor.capture()))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        runner.run();

        // Assert
        Doctor captured = captor.getValue();
        assertEquals(90000.00, captured.getIncome());
    }

    @Test
    void run_withStringArgs_callsRegisterDoctorOnce() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        runner.run("arg1", "arg2");

        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    // ─── run() – exception handling ──────────────────────────────────────────

    @Test
    void run_whenServiceThrowsException_doesNotPropagateException() throws Exception {
        // Arrange – service throws; runner catches internally
        when(service.registerDoctor(any(Doctor.class)))
                .thenThrow(new RuntimeException("DB error"));

        // Act & Assert – should NOT throw (exception is caught inside run())
        assertDoesNotThrow(() -> runner.run());
    }

    @Test
    void run_whenServiceThrowsRuntimeException_serviceWasStillCalled() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenThrow(new RuntimeException("DB error"));

        // Act
        runner.run();

        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    void run_whenServiceThrowsIllegalStateException_doesNotPropagateException() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenThrow(new IllegalStateException("Illegal state"));

        // Act & Assert
        assertDoesNotThrow(() -> runner.run());
    }

    // ─── Constructor / instantiation ─────────────────────────────────────────

    @Test
    void runner_isNotNull() {
        // Assert
        assertNotNull(runner);
    }

    @Test
    void runner_implementsCommandLineRunner() {
        // Assert
        assertTrue(runner instanceof org.springframework.boot.CommandLineRunner);
    }
}
