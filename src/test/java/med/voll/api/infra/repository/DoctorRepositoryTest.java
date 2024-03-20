package med.voll.api.infra.repository;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.Specialty;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
// @ActiveProfiles("test")
public class DoctorRepositoryTest {

  @Autowired
  private DoctorRepository repository;
  
  @Test
  void shouldReturnNullIfNoDoctorIsAvailable() {
    LocalDateTime nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

    Doctor doctor = repository.findRandomMedicAvailableOnDate(Specialty.CARDIOLOGIA, nextMonday);

    assertNull(doctor);
  }
}
