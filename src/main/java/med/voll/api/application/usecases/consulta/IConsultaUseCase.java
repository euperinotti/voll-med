package med.voll.api.application.usecases.consulta;

import med.voll.api.application.dto.DadosAgendamentoConsulta;

public interface IConsultaUseCase {
  public void execute(DadosAgendamentoConsulta dados);
}
