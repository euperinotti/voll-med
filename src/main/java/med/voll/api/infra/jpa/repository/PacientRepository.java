package med.voll.api.infra.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.validation.constraints.NotNull;
import med.voll.api.infra.jakarta.repository.models.Pacient;

@Repository
public interface PacientRepository extends JpaRepository<Pacient, Long> {
    Page<Pacient> findAllByisActiveTrue(Pageable page);

    @Query("""
        SELECT p.isActive
        FROM Pacient p
        WHERE p.id = :pacientId
    """)
    boolean findActiveById(@NotNull Long pacientId);
    
}
