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

@ExtendWith(MockitoExtension.class)
@DisplayName("BootDataJPAProj1CrudRepoApp Tests")
class BootDataJPAProj1CrudRepoAppTest {

    @Mock
    private IDoctorService service;

    @InjectMocks
    private BootDataJPAProj1CrudRepoApp bootDataJPAProj1CrudRepoApp;

    // ─── run() Tests ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("run calls registerDoctor exactly once")
    void run_noArgs_callsRegisterDoctorOnce() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run();

        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    @DisplayName("run passes doctor with correct name to service")
    void run_noArgs_passesDoctorWithCorrectName() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run();

        // Assert
        verify(service).registerDoctor(doctorCaptor.capture());
        Doctor capturedDoctor = doctorCaptor.getValue();
        assertEquals("sairam", capturedDoctor.getDocName());
    }

    @Test
    @DisplayName("run passes doctor with correct specialization to service")
    void run_noArgs_passesDoctorWithCorrectSpecialization() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run();

        // Assert
        verify(service).registerDoctor(doctorCaptor.capture());
        Doctor capturedDoctor = doctorCaptor.getValue();
        assertEquals("MD_Cardio", capturedDoctor.getSpecialization());
    }

    @Test
    @DisplayName("run passes doctor with correct income to service")
    void run_noArgs_passesDoctorWithCorrectIncome() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run();

        // Assert
        verify(service).registerDoctor(doctorCaptor.capture());
        Doctor capturedDoctor = doctorCaptor.getValue();
        assertEquals(90000.00, capturedDoctor.getIncome());
    }

    @Test
    @DisplayName("run with varargs args calls registerDoctor once")
    void run_withArgs_callsRegisterDoctorOnce() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run("arg1", "arg2");

        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    @DisplayName("run does not throw exception when service succeeds")
    void run_serviceSucceeds_noExceptionThrown() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act & Assert
        assertDoesNotThrow(() -> bootDataJPAProj1CrudRepoApp.run());
    }

    @Test
    @DisplayName("run catches exception when service throws RuntimeException")
    void run_serviceThrowsRuntimeException_exceptionCaught() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenThrow(new RuntimeException("DB error"));

        // Act & Assert - exception should be caught internally, not propagated
        assertDoesNotThrow(() -> bootDataJPAProj1CrudRepoApp.run());
    }

    @Test
    @DisplayName("run catches exception when service throws Exception")
    void run_serviceThrowsException_exceptionCaught() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class))).thenThrow(new RuntimeException("Connection refused"));

        // Act & Assert
        assertDoesNotThrow(() -> bootDataJPAProj1CrudRepoApp.run());
    }

    @Test
    @DisplayName("run implements CommandLineRunner interface")
    void bootDataJPAProj1CrudRepoApp_implementsCommandLineRunner() {
        // Assert
        assertTrue(bootDataJPAProj1CrudRepoApp instanceof org.springframework.boot.CommandLineRunner);
    }

    @Test
    @DisplayName("run passes non-null doctor to service")
    void run_noArgs_passesNonNullDoctorToService() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(any(Doctor.class))).thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run();

        // Assert
        verify(service).registerDoctor(doctorCaptor.capture());
        assertNotNull(doctorCaptor.getValue());
    }
}
