package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.observer;

import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.ProcessarMensagemUseCase;
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
    private static final Logger log = LoggerFactory.getLogger(TelemetriaObserver.class);
    private final SerializationFacade serializationFacade;
    private final HarpiaAntiCorruptionLayer harpiaAntiCorruptionLayer;
    private final EquipamentoService equipamentoService;
    private final ProcessarMensagemUseCase processarMensagemUseCase;

    public TelemetriaObserver(SerializationFacade serializationFacade,
                              HarpiaAntiCorruptionLayer harpiaAntiCorruptionLayer,
                              EquipamentoService equipamentoService,
                              ProcessarMensagemUseCase processarMensagemUseCase) {
        this.serializationFacade = serializationFacade;
        this.harpiaAntiCorruptionLayer = harpiaAntiCorruptionLayer;
        this.equipamentoService = equipamentoService;
        this.processarMensagemUseCase = processarMensagemUseCase;
    }

    @Override
    public void onEvent(Object object) {
        log.info("Notificação de mensagem recebida.");
        byte[] mensagemBytes = (byte[]) object;
        HarpiaTelemetryMessage harpiaTelemetryMessage = serializationFacade.fromSnakeCaseBytes(mensagemBytes, HarpiaTelemetryMessage.class);
        Mensagem mensagem = harpiaAntiCorruptionLayer.fromHarpiaTelemetryMessage(harpiaTelemetryMessage);
        processarMensagemUseCase.execute(mensagem);
        equipamentoService.atualizarDtUltimaAtualizacao(harpiaTelemetryMessage.getSerial());
        log.info("Mensagem processada com sucesso.");
    }
}
