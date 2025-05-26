package br.tec.bemtevi.harpia_ms_telemetria.application.usecase;

import br.tec.bemtevi.harpia_ms_telemetria.application.mediator.Mediator;
import br.tec.bemtevi.harpia_ms_telemetria.domain.enums.TipoEvento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Mensagem;
import br.tec.bemtevi.harpia_ms_telemetria.domain.service.ReflectionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessarMensagemUseCase {
    private final ReflectionService reflectionService;
    private final Mediator mediator;

    public ProcessarMensagemUseCase(ReflectionService reflectionService, Mediator mediator) {
        this.reflectionService = reflectionService;
        this.mediator = mediator;
    }

    public void execute(Mensagem mensagem) {
        List<String> atributosString = reflectionService.converterAtributosEmString(mensagem);
        atributosString
                .parallelStream()
                .forEach(atributoString -> {
                    Object atributoReal = reflectionService.getAtributoByNome(atributoString, mensagem);
                    if (atributoReal != null)
                        mediator.emitirEvento(TipoEvento.fromString(atributoString.toUpperCase()), atributoReal);
                });
    }
}
