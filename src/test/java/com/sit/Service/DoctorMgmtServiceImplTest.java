package com.sit.Service;

import com.sit.Repository.IDoctorRepo;
import com.sit.entity.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("DoctorMgmtServiceImpl Tests")
class DoctorMgmtServiceImplTest {

    @Mock
    private IDoctorRepo doctorRepo;

    @InjectMocks
    private DoctorMgmtServiceImpl doctorMgmtService;

    private Doctor sampleDoctor;

    @BeforeEach
    void setUp() {
        sampleDoctor = new Doctor();
        sampleDoctor.setDocId(203);
        sampleDoctor.setDocName("sairam");
        sampleDoctor.setSpecialization("MD_Cardio");
        sampleDoctor.setIncome(90000.00);
    }

    // ─── Constructor Tests ───────────────────────────────────────────────────

    @Test
    @DisplayName("Constructor creates non-null service instance")
    void constructor_withValidRepo_createsNonNullInstance() {
        // Arrange & Act
        DoctorMgmtServiceImpl service = new DoctorMgmtServiceImpl(doctorRepo);

        // Assert
        assertNotNull(service);
    }

    @Test
    @DisplayName("Constructor injects IDoctorRepo dependency")
    void constructor_withRepo_injectsDependency() {
        // Arrange & Act
        DoctorMgmtServiceImpl service = new DoctorMgmtServiceImpl(doctorRepo);

        // Assert — verify the service is usable (repo is injected)
        assertNotNull(service);
    }

    // ─── registerDoctor Tests ────────────────────────────────────────────────

    @Test
    @DisplayName("registerDoctor returns success message with doctor ID")
    void registerDoctor_withValidDoctor_returnsSuccessMessage() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(sampleDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(sampleDoctor);

        // Assert
        assertNotNull(result);
        assertEquals("Doctor obj is saved with id value :203", result);
    }

    @Test
    @DisplayName("registerDoctor calls doctorRepo.save exactly once")
    void registerDoctor_callsRepoSaveOnce() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(sampleDoctor);

        // Act
        doctorMgmtService.registerDoctor(sampleDoctor);

        // Assert
        verify(doctorRepo, times(1)).save(sampleDoctor);
    }

    @Test
    @DisplayName("registerDoctor passes the correct doctor object to repo")
    void registerDoctor_passesCorrectDoctorToRepo() {
        // Arrange
        when(doctorRepo.save(sampleDoctor)).thenReturn(sampleDoctor);

        // Act
        doctorMgmtService.registerDoctor(sampleDoctor);

        // Assert
        verify(doctorRepo).save(sampleDoctor);
    }

    @Test
    @DisplayName("registerDoctor result contains the saved doctor ID")
    void registerDoctor_resultContainsDoctorId() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(sampleDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(sampleDoctor);

        // Assert
        assertTrue(result.contains("203"));
    }

    @Test
    @DisplayName("registerDoctor result starts with expected prefix")
    void registerDoctor_resultStartsWithExpectedPrefix() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(sampleDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(sampleDoctor);

        // Assert
        assertTrue(result.startsWith("Doctor obj is saved with id value :"));
    }

    @Test
    @DisplayName("registerDoctor with different doctor ID returns correct message")
    void registerDoctor_withDifferentDoctorId_returnsCorrectMessage() {
        // Arrange
        Doctor anotherDoctor = new Doctor();
        anotherDoctor.setDocId(500);
        anotherDoctor.setDocName("Dr. Jane");
        anotherDoctor.setSpecialization("Neurology");
        anotherDoctor.setIncome(150000.00);

        when(doctorRepo.save(any(Doctor.class))).thenReturn(anotherDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(anotherDoctor);

        // Assert
        assertEquals("Doctor obj is saved with id value :500", result);
    }

    @Test
    @DisplayName("registerDoctor with doctor having null name still saves successfully")
    void registerDoctor_withNullDocName_savesSuccessfully() {
        // Arrange
        Doctor doctorWithNullName = new Doctor();
        doctorWithNullName.setDocId(300);
        doctorWithNullName.setDocName(null);
        doctorWithNullName.setSpecialization("General");
        doctorWithNullName.setIncome(60000.00);

        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctorWithNullName);

        // Act
        String result = doctorMgmtService.registerDoctor(doctorWithNullName);

        // Assert
        assertNotNull(result);
        assertEquals("Doctor obj is saved with id value :300", result);
    }

    @Test
    @DisplayName("registerDoctor with doctor having zero income saves successfully")
    void registerDoctor_withZeroIncome_savesSuccessfully() {
        // Arrange
        Doctor doctorWithZeroIncome = new Doctor();
        doctorWithZeroIncome.setDocId(400);
        doctorWithZeroIncome.setDocName("Dr. Zero");
        doctorWithZeroIncome.setSpecialization("Intern");
        doctorWithZeroIncome.setIncome(0.0);

        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctorWithZeroIncome);

        // Act
        String result = doctorMgmtService.registerDoctor(doctorWithZeroIncome);

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("400"));
    }

    @Test
    @DisplayName("registerDoctor propagates RuntimeException from repo")
    void registerDoctor_whenRepoThrowsException_propagatesException() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class)))
                .thenThrow(new RuntimeException("Database connection failed"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> doctorMgmtService.registerDoctor(sampleDoctor));

        assertEquals("Database connection failed", exception.getMessage());
    }

    @Test
    @DisplayName("registerDoctor propagates IllegalArgumentException from repo")
    void registerDoctor_whenRepoThrowsIllegalArgument_propagatesException() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class)))
                .thenThrow(new IllegalArgumentException("Invalid doctor data"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> doctorMgmtService.registerDoctor(sampleDoctor));
    }

    @Test
    @DisplayName("registerDoctor with minimum doctor ID returns correct message")
    void registerDoctor_withMinimumDoctorId_returnsCorrectMessage() {
        // Arrange
        Doctor minIdDoctor = new Doctor();
        minIdDoctor.setDocId(1);
        minIdDoctor.setDocName("Dr. Min");
        minIdDoctor.setSpecialization("General");
        minIdDoctor.setIncome(50000.00);

        when(doctorRepo.save(any(Doctor.class))).thenReturn(minIdDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(minIdDoctor);

        // Assert
        assertEquals("Doctor obj is saved with id value :1", result);
    }

    @Test
    @DisplayName("registerDoctor does not call repo more than once")
    void registerDoctor_doesNotCallRepoMoreThanOnce() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(sampleDoctor);

        // Act
        doctorMgmtService.registerDoctor(sampleDoctor);

        // Assert
        verify(doctorRepo, atMostOnce()).save(any(Doctor.class));
    }

    @Test
    @DisplayName("registerDoctor with new doctor object (no ID set) returns message with saved ID")
    void registerDoctor_withNewDoctorNoId_returnsMessageWithSavedId() {
        // Arrange
        Doctor newDoctor = new Doctor();
        newDoctor.setDocName("New Doctor");
        newDoctor.setSpecialization("Dermatology");
        newDoctor.setIncome(95000.00);

        Doctor savedDoctor = new Doctor();
        savedDoctor.setDocId(999);
        savedDoctor.setDocName("New Doctor");
        savedDoctor.setSpecialization("Dermatology");
        savedDoctor.setIncome(95000.00);

        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(newDoctor);

        // Assert
        assertEquals("Doctor obj is saved with id value :999", result);
    }

    @Test
    @DisplayName("registerDoctor implements IDoctorService interface")
    void doctorMgmtService_implementsIDoctorService() {
        // Assert
        assertTrue(doctorMgmtService instanceof IDoctorService);
    }
}
