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
    private BootDataJPAProj1CrudRepoApp bootDataJPAProj1CrudRepoApp;

    // ─── run() Tests ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("run() calls service.registerDoctor exactly once")
    void run_shouldCallRegisterDoctorExactlyOnce() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run();

        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    @DisplayName("run() passes doctor with correct name to service")
    void run_shouldPassDoctorWithCorrectName() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run();

        // Assert
        verify(service).registerDoctor(doctorCaptor.capture());
        assertEquals("sairam", doctorCaptor.getValue().getDocName());
    }

    @Test
    @DisplayName("run() passes doctor with correct specialization to service")
    void run_shouldPassDoctorWithCorrectSpecialization() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run();

        // Assert
        verify(service).registerDoctor(doctorCaptor.capture());
        assertEquals("MD_Cardio", doctorCaptor.getValue().getSpecialization());
    }

    @Test
    @DisplayName("run() passes doctor with correct income to service")
    void run_shouldPassDoctorWithCorrectIncome() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run();

        // Assert
        verify(service).registerDoctor(doctorCaptor.capture());
        assertEquals(90000.00, doctorCaptor.getValue().getIncome());
    }

    @Test
    @DisplayName("run() does not throw exception when service succeeds")
    void run_whenServiceSucceeds_shouldNotThrowException() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act & Assert
        assertDoesNotThrow(() -> bootDataJPAProj1CrudRepoApp.run());
    }

    @Test
    @DisplayName("run() does not propagate exception when service throws")
    void run_whenServiceThrowsException_shouldNotPropagateException() {
        // Arrange - service throws exception, but run() catches it internally
        when(service.registerDoctor(any(Doctor.class)))
                .thenThrow(new RuntimeException("DB Error"));

        // Act & Assert - run() has try-catch, so no exception should propagate
        assertDoesNotThrow(() -> bootDataJPAProj1CrudRepoApp.run());
    }

    @Test
    @DisplayName("run() with no args does not throw exception")
    void run_withNoArgs_shouldNotThrowException() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act & Assert
        assertDoesNotThrow(() -> bootDataJPAProj1CrudRepoApp.run());
    }

    @Test
    @DisplayName("run() with multiple args does not throw exception")
    void run_withMultipleArgs_shouldNotThrowException() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act & Assert
        assertDoesNotThrow(() -> bootDataJPAProj1CrudRepoApp.run("arg1", "arg2", "arg3"));
    }

    @Test
    @DisplayName("run() still calls service even with extra args")
    void run_withExtraArgs_shouldStillCallService() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run("extra", "args");

        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    @DisplayName("BootDataJPAProj1CrudRepoApp implements CommandLineRunner")
    void bootDataJPAProj1CrudRepoApp_shouldImplementCommandLineRunner() {
        // Assert
        assertTrue(bootDataJPAProj1CrudRepoApp instanceof org.springframework.boot.CommandLineRunner);
    }

    @Test
    @DisplayName("run() passes a non-null Doctor to service")
    void run_shouldPassNonNullDoctorToService() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run();

        // Assert
        verify(service).registerDoctor(doctorCaptor.capture());
        assertNotNull(doctorCaptor.getValue());
    }
}
