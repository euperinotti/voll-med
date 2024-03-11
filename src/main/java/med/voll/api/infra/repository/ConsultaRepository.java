package med.voll.api.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.domain.consulta.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
  
}
