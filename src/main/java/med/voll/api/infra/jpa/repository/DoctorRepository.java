package med.voll.api.infra.jpa.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.enums.Specialty;
import med.voll.api.infra.jakarta.repository.models.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
  Page<Doctor> findAllByisActiveTrue(Pageable page);

  @Query("""
          SELECT d FROM Doctor d
              WHERE
              d.isActive = true
              AND
              d.specialty = :specialty
              AND
              d.id NOT IN(
                  SELECT a.doctor.id from Appointment a
                  where
                  a.date = :date
          and
                  a.cancelReason is null
              )
              order by rand()
              limit 1
      """)
  Doctor findRandomMedicAvailableOnDate(Specialty specialty, @NotNull @Future LocalDateTime date);

  @Query("""
      SELECT d.isActive
      FROM Doctor d
      WHERE d.id = :doctorId
      """)
  boolean findActiveById(Long doctorId);
}
