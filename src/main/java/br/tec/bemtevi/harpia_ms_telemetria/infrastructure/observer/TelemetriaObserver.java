package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.observer;

import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.ProcessarTelemetriaUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Sensors;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.HarpiaTelemetryMessage;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.anticorruptionlayer.HarpiaAntiCorruptionLayer;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.SerializationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(value = "TelemetriaObserver")
public class TelemetriaObserver implements Observer {
    private static final Logger log = LoggerFactory.getLogger(TelemetriaObserver.class);
    private final SerializationFacade serializationFacade;
    private final HarpiaAntiCorruptionLayer harpiaAntiCorruptionLayer;
    private final ProcessarTelemetriaUseCase processarTelemetriaUseCase;

    public TelemetriaObserver(SerializationFacade serializationFacade,
                              HarpiaAntiCorruptionLayer harpiaAntiCorruptionLayer,
                              ProcessarTelemetriaUseCase processarTelemetriaUseCase) {
        this.serializationFacade = serializationFacade;
        this.harpiaAntiCorruptionLayer = harpiaAntiCorruptionLayer;
        this.processarTelemetriaUseCase = processarTelemetriaUseCase;
    }

    @Override
    public void onEvent(Object object) {
        log.info("Notificação de mensagem recebida.");
        byte[] mensagem = (byte[]) object;
        HarpiaTelemetryMessage harpiaTelemetryMessage = serializationFacade.fromSnakeCaseBytes(mensagem, HarpiaTelemetryMessage.class);
        Sensors sensors = harpiaAntiCorruptionLayer.fromHarpiaTelemetryMessage(harpiaTelemetryMessage);
        processarTelemetriaUseCase.execute(sensors);
        log.info("Mensagem processada com sucesso.");
    }
}
