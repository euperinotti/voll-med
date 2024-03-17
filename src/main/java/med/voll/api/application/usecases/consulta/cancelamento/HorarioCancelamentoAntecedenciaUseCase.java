package med.voll.api.application.usecases.consulta.cancelamento;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.application.dto.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.infra.repository.ConsultaRepository;

@Component
public class HorarioCancelamentoAntecedenciaUseCase implements ICancelamentoConsultaUseCase {

  @Autowired
  private ConsultaRepository repository;

  @Override
  public void execute(DadosCancelamentoConsulta dados) {
    Consulta consulta = repository.getReferenceById(dados.idConsulta());
    LocalDateTime agora = LocalDateTime.now();
    Long diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

    if (diferencaEmHoras < 24) {
      throw new RuntimeException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
    }
  }

}
