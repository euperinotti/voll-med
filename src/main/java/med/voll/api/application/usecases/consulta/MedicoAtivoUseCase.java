package med.voll.api.application.usecases.consulta;

import med.voll.api.application.dto.DadosAgendamentoConsulta;
import med.voll.api.infra.repository.MedicoRepository;

public class MedicoAtivoUseCase implements IConsultaUseCase { 
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
