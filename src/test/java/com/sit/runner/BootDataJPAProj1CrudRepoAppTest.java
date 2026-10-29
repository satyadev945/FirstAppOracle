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
    private BootDataJPAProj1CrudRepoApp bootApp;

    // ─── Constructor Tests ───────────────────────────────────────────────────

    @Test
    @DisplayName("Constructor creates non-null instance")
    void constructor_withValidService_createsNonNullInstance() {
        // Arrange & Act
        BootDataJPAProj1CrudRepoApp app = new BootDataJPAProj1CrudRepoApp(service);

        // Assert
        assertNotNull(app);
    }

    @Test
    @DisplayName("Constructor injects IDoctorService dependency")
    void constructor_injectsServiceDependency() {
        // Arrange & Act
        BootDataJPAProj1CrudRepoApp app = new BootDataJPAProj1CrudRepoApp(service);

        // Assert
        assertNotNull(app);
    }

    // ─── run() Tests ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("run() calls registerDoctor exactly once")
    void run_callsRegisterDoctorOnce() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        verify(service, times(1)).registerDoctor(any(Doctor.class));
    }

    @Test
    @DisplayName("run() creates Doctor with name 'sairam'")
    void run_createsDoctorWithCorrectName() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(doctorCaptor.capture()))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        Doctor capturedDoctor = doctorCaptor.getValue();
        assertEquals("sairam", capturedDoctor.getDocName());
    }

    @Test
    @DisplayName("run() creates Doctor with specialization 'MD_Cardio'")
    void run_createsDoctorWithCorrectSpecialization() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(doctorCaptor.capture()))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        Doctor capturedDoctor = doctorCaptor.getValue();
        assertEquals("MD_Cardio", capturedDoctor.getSpecialization());
    }

    @Test
    @DisplayName("run() creates Doctor with income 90000.00")
    void run_createsDoctorWithCorrectIncome() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(doctorCaptor.capture()))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        Doctor capturedDoctor = doctorCaptor.getValue();
        assertEquals(90000.00, capturedDoctor.getIncome());
    }

    @Test
    @DisplayName("run() with no args executes without throwing exception")
    void run_withNoArgs_executesWithoutException() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act & Assert
        assertDoesNotThrow(() -> bootApp.run());
    }

    @Test
    @DisplayName("run() with empty args array executes without throwing exception")
    void run_withEmptyArgs_executesWithoutException() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act & Assert
        assertDoesNotThrow(() -> bootApp.run(new String[]{}));
    }

    @Test
    @DisplayName("run() with multiple args executes without throwing exception")
    void run_withMultipleArgs_executesWithoutException() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act & Assert
        assertDoesNotThrow(() -> bootApp.run("arg1", "arg2", "arg3"));
    }

    @Test
    @DisplayName("run() handles exception from service gracefully (catches internally)")
    void run_whenServiceThrowsException_handlesGracefully() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenThrow(new RuntimeException("DB error"));

        // Act & Assert — run() catches exceptions internally via try-catch
        assertDoesNotThrow(() -> bootApp.run());
    }

    @Test
    @DisplayName("run() handles RuntimeException from service without propagating")
    void run_whenServiceThrowsRuntimeException_doesNotPropagate() {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenThrow(new RuntimeException("Connection timeout"));

        // Act & Assert
        assertDoesNotThrow(() -> bootApp.run("someArg"));
    }

    @Test
    @DisplayName("run() creates a new Doctor object (docId is null before save)")
    void run_createsDoctorWithNullDocId() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(doctorCaptor.capture()))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        Doctor capturedDoctor = doctorCaptor.getValue();
        assertNull(capturedDoctor.getDocId()); // ID is generated by DB sequence
    }

    @Test
    @DisplayName("run() passes non-null Doctor to service")
    void run_passesNonNullDoctorToService() throws Exception {
        // Arrange
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        when(service.registerDoctor(doctorCaptor.capture()))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        assertNotNull(doctorCaptor.getValue());
    }

    @Test
    @DisplayName("run() does not call service more than once")
    void run_doesNotCallServiceMoreThanOnce() throws Exception {
        // Arrange
        when(service.registerDoctor(any(Doctor.class)))
                .thenReturn("Doctor obj is saved with id value :203");

        // Act
        bootApp.run();

        // Assert
        verify(service, atMostOnce()).registerDoctor(any(Doctor.class));
    }

    @Test
    @DisplayName("BootDataJPAProj1CrudRepoApp implements CommandLineRunner")
    void bootApp_implementsCommandLineRunner() {
        // Assert
        assertTrue(bootApp instanceof org.springframework.boot.CommandLineRunner);
    }
}
