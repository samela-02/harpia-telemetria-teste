package br.tec.bemtevi.harpia_ms_telemetria.application.usecase;

import br.tec.bemtevi.harpia_ms_telemetria.application.mediator.Mediator;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Mensagem;
import br.tec.bemtevi.harpia_ms_telemetria.domain.service.ReflectionService;

public class ProcessarMensagemUseCaseFake extends ProcessarMensagemUseCase {
    public ProcessarMensagemUseCaseFake(ReflectionService reflectionService, Mediator mediator) {
        super(reflectionService, mediator);
    }

    @Override
    public void execute(Mensagem mensagem) {
    }
}