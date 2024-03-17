package med.voll.api.application.usecases.consulta.cancelamento;

import med.voll.api.application.dto.DadosCancelamentoConsulta;

public interface ICancelamentoConsultaUseCase {
  void execute(DadosCancelamentoConsulta dados);
}
