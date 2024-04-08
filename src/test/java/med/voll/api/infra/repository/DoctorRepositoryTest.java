package med.voll.api.infra.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import med.voll.api.domain.entities.AddressBO;
import med.voll.api.domain.enums.Specialty;
import med.voll.api.infra.jakarta.repository.models.Doctor;
import med.voll.api.infra.jpa.repository.DoctorRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
public class DoctorRepositoryTest {

  @Autowired
  private DoctorRepository repository;
  
  @Test
  void shouldReturnNullIfNoDoctorIsAvailable() {
    LocalDateTime nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

    Doctor doctor = repository.findRandomMedicAvailableOnDate(Specialty.CARDIOLOGIA, nextMonday);

    assertNull(doctor);
  }

  @Test
  void shouldReturnADoctorIfIsAvailable() {

    AddressBO mockAddress = new AddressBO("Gales Glen", "Santa Cruz", "86609-020", "1329", "house", "Cascavel", "Parana");
    Doctor newDoctor = new Doctor(null, "Juan Daniels", "razmig@gutre.so", "(306) 391-6443", "000000", Specialty.CARDIOLOGIA, mockAddress, true);

    repository.save(newDoctor);

    LocalDateTime nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

    Doctor doctor = repository.findRandomMedicAvailableOnDate(Specialty.CARDIOLOGIA, nextMonday);

    assertNotNull(doctor);
  }
}
