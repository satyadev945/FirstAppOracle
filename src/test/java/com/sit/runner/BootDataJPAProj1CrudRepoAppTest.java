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
 * Tests the run() method and its interaction with IDoctorService.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("BootDataJPAProj1CrudRepoApp Runner Tests")
class BootDataJPAProj1CrudRepoAppTest {

    @Mock
    private IDoctorService service;

    @InjectMocks
    private BootDataJPAProj1CrudRepoApp bootDataJPAProj1CrudRepoApp;

    // ─── run() Method Tests ───────────────────────────────────────────────────

    @Test
    @DisplayName("run method executes without throwing exception")
    void run_noArgs_executesWithoutException() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
            .thenReturn("Doctor obj is saved with id value :203");

        // Act & Assert
        assertDoesNotThrow(() -> bootDataJPAProj1CrudRepoApp.run());
    }

    @Test
    @DisplayName("run method calls registerDoctor exactly once")
    void run_noArgs_callsRegisterDoctorOnce() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
            .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run();

        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    @DisplayName("run method creates Doctor with correct name 'sairam'")
    void run_noArgs_createsDoctorWithCorrectName() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(doctorCaptor.capture()))
            .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run();

        // Assert
        Doctor capturedDoctor = doctorCaptor.getValue();
        assertEquals("sairam", capturedDoctor.getDocName());
    }

    @Test
    @DisplayName("run method creates Doctor with correct specialization 'MD_Cardio'")
    void run_noArgs_createsDoctorWithCorrectSpecialization() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(doctorCaptor.capture()))
            .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run();

        // Assert
        Doctor capturedDoctor = doctorCaptor.getValue();
        assertEquals("MD_Cardio", capturedDoctor.getSpecialization());
    }

    @Test
    @DisplayName("run method creates Doctor with correct income 90000.00")
    void run_noArgs_createsDoctorWithCorrectIncome() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(doctorCaptor.capture()))
            .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run();

        // Assert
        Doctor capturedDoctor = doctorCaptor.getValue();
        assertEquals(90000.00, capturedDoctor.getIncome());
    }

    @Test
    @DisplayName("run method creates Doctor with null docId (not set manually)")
    void run_noArgs_createsDoctorWithNullDocId() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(doctorCaptor.capture()))
            .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run();

        // Assert
        Doctor capturedDoctor = doctorCaptor.getValue();
        assertNull(capturedDoctor.getDocId());
    }

    @Test
    @DisplayName("run method handles service exception gracefully without rethrowing")
    void run_serviceThrowsException_handlesGracefully() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
            .thenThrow(new RuntimeException("Service failure"));

        // Act & Assert - exception is caught internally, should not propagate
        assertDoesNotThrow(() -> bootDataJPAProj1CrudRepoApp.run());
    }

    @Test
    @DisplayName("run method with empty args array executes without exception")
    void run_emptyArgsArray_executesWithoutException() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
            .thenReturn("Doctor obj is saved with id value :203");

        // Act & Assert
        assertDoesNotThrow(() -> bootDataJPAProj1CrudRepoApp.run(new String[]{}));
    }

    @Test
    @DisplayName("run method with non-empty args array executes without exception")
    void run_nonEmptyArgsArray_executesWithoutException() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
            .thenReturn("Doctor obj is saved with id value :203");

        // Act & Assert
        assertDoesNotThrow(() -> bootDataJPAProj1CrudRepoApp.run("arg1", "arg2"));
    }

    @Test
    @DisplayName("run method passes non-null Doctor to service")
    void run_noArgs_passesNonNullDoctorToService() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(doctorCaptor.capture()))
            .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run();

        // Assert
        assertNotNull(doctorCaptor.getValue());
    }

    @Test
    @DisplayName("run method does not call any other service methods")
    void run_noArgs_onlyCallsRegisterDoctor() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
            .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run();

        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("BootDataJPAProj1CrudRepoApp implements CommandLineRunner")
    void bootDataJPAProj1CrudRepoApp_implementsCommandLineRunner() {
        // Assert
        assertTrue(bootDataJPAProj1CrudRepoApp instanceof org.springframework.boot.CommandLineRunner);
    }
}
