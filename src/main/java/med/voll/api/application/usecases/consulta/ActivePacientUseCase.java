package med.voll.api.application.usecases.consulta;

import med.voll.api.application.dto.DadosAgendamentoConsulta;
import med.voll.api.infra.repository.PacienteRepository;

public class ActivePacientUseCase implements IConsultaUseCase {

  private PacienteRepository repository;

  @Override
  public void execute(DadosAgendamentoConsulta dados) {
    boolean pacientIsActive = repository.findAtivoById(dados.idPaciente());

    if(!pacientIsActive) {
      throw new RuntimeException("Paciente não está ativo");
    }
  }
  
}
