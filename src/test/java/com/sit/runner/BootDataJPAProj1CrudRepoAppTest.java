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
@DisplayName("BootDataJPAProj1CrudRepoApp Tests")
class BootDataJPAProj1CrudRepoAppTest {

    @Mock
    private IDoctorService service;

    @InjectMocks
    private BootDataJPAProj1CrudRepoApp bootDataJPAProj1CrudRepoApp;

    // ─── run() Method Tests ───────────────────────────────────────────────────

    @Test
    @DisplayName("run() calls registerDoctor exactly once")
    void testRun_callsRegisterDoctorOnce() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run();

        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    @DisplayName("run() creates Doctor with correct name 'sairam'")
    void testRun_createsDoctorWithCorrectName() throws Exception {
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
    @DisplayName("run() creates Doctor with correct specialization 'MD_Cardio'")
    void testRun_createsDoctorWithCorrectSpecialization() throws Exception {
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
    @DisplayName("run() creates Doctor with correct income 90000.00")
    void testRun_createsDoctorWithCorrectIncome() throws Exception {
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
    @DisplayName("run() does not throw exception when service succeeds")
    void testRun_serviceSucceeds_noExceptionThrown() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act & Assert
        assertDoesNotThrow(() -> bootDataJPAProj1CrudRepoApp.run());
    }

    @Test
    @DisplayName("run() does not propagate exception when service throws RuntimeException")
    void testRun_serviceThrowsRuntimeException_exceptionCaughtInternally() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenThrow(new RuntimeException("DB connection failed"));

        // Act & Assert - exception is caught inside run() via try-catch
        assertDoesNotThrow(() -> bootDataJPAProj1CrudRepoApp.run());
    }

    @Test
    @DisplayName("run() with no args parameter does not throw exception")
    void testRun_withNoArgs_doesNotThrow() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act & Assert
        assertDoesNotThrow(() -> bootDataJPAProj1CrudRepoApp.run(new String[]{}));
    }

    @Test
    @DisplayName("run() with multiple args does not affect behavior")
    void testRun_withMultipleArgs_doesNotAffectBehavior() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run("arg1", "arg2", "arg3");

        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    @DisplayName("run() passes non-null Doctor to service")
    void testRun_passesNonNullDoctorToService() throws Exception {
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
    @DisplayName("run() does not call service more than once")
    void testRun_doesNotCallServiceMoreThanOnce() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootDataJPAProj1CrudRepoApp.run();

        // Assert
        verify(service, atMostOnce()).registerDoctor(any(Doctor.class));
    }

    @Test
    @DisplayName("run() handles NullPointerException from service gracefully")
    void testRun_serviceThrowsNullPointerException_handledGracefully() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenThrow(new NullPointerException("Null entity"));

        // Act & Assert - exception is caught inside run() via try-catch
        assertDoesNotThrow(() -> bootDataJPAProj1CrudRepoApp.run());
    }
}
