package med.voll.api.application.usecases.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.application.dto.DadosAgendamentoConsulta;
import med.voll.api.infra.repository.MedicoRepository;

@Component
public class MedicoAtivoUseCase implements IConsultaUseCase { 

  @Autowired
  private MedicoRepository repository;

  @Override
  public void execute(DadosAgendamentoConsulta dados) {
    if (dados.idMedico() == null) {
      return;
    }

    boolean isActive = repository.findActiveById(dados.idMedico());

    if(!isActive) {
      throw new RuntimeException("Médico inativo");
    }
  }
}
