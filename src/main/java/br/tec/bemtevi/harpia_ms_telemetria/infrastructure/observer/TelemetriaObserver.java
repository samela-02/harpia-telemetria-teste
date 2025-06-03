package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.observer;

import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.ProcessarMensagemUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.LoggerFacade;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.SerializationFacade;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Mensagem;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.domain.service.EquipamentoService;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia.HarpiaTelemetryMessage;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.anticorruptionlayer.HarpiaAntiCorruptionLayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(value = "TelemetriaObserver")
public class TelemetriaObserver implements Observer {
    private final LoggerFacade loggerFacade;
    private final SerializationFacade serializationFacade;
    private final HarpiaAntiCorruptionLayer harpiaAntiCorruptionLayer;
    private final EquipamentoService equipamentoService;
    private final ProcessarMensagemUseCase processarMensagemUseCase;

    public TelemetriaObserver(LoggerFacade loggerFacade,
                              SerializationFacade serializationFacade,
                              HarpiaAntiCorruptionLayer harpiaAntiCorruptionLayer,
                              EquipamentoService equipamentoService,
                              ProcessarMensagemUseCase processarMensagemUseCase) {
        this.loggerFacade = loggerFacade;
        this.serializationFacade = serializationFacade;
        this.harpiaAntiCorruptionLayer = harpiaAntiCorruptionLayer;
        this.equipamentoService = equipamentoService;
        this.processarMensagemUseCase = processarMensagemUseCase;
    }

    @Override
    public void onEvent(Object object) {
        loggerFacade.info("Notificação de mensagem recebida.");
        try {
            processarMensagem((byte[]) object);
        } catch (Exception e) {
            processarFallback((byte[]) object);
        }
    }

    private void processarMensagem(byte[] object) {
        HarpiaTelemetryMessage harpiaTelemetryMessage = serializationFacade
                .fromSnakeCaseBytes(object, HarpiaTelemetryMessage.class);
        Mensagem mensagem = harpiaAntiCorruptionLayer.fromHarpiaTelemetryMessage(harpiaTelemetryMessage);
        processarMensagemUseCase.execute(mensagem);
        equipamentoService.atualizarDtUltimaAtualizacao(harpiaTelemetryMessage.getSerial());
        loggerFacade.info("Mensagem processada com sucesso.");
    }

    private void processarFallback(byte[] object) {
        loggerFacade.warn("Erro ao processar mensagem.");
        loggerFacade.info("Iniciando fallback");
        // TODO chamar fallback aqui
    }
}
