package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.observer.impl;

import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.ProcessarTelemetriaUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.HarpiaTelemetryMessage;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Sensors;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.anticorruptionlayer.HarpiaAntiCorruptionLayer;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.SerializationFacade;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.observer.TelemetriaObserver;
import org.springframework.stereotype.Component;

@Component
public class TelemetriaObserverImpl implements TelemetriaObserver {
    private final SerializationFacade serializationFacade;
    private final HarpiaAntiCorruptionLayer harpiaAntiCorruptionLayer;
    private final ProcessarTelemetriaUseCase processarTelemetriaUseCase;

    public TelemetriaObserverImpl(SerializationFacade serializationFacade,
                                  HarpiaAntiCorruptionLayer harpiaAntiCorruptionLayer,
                                  ProcessarTelemetriaUseCase processarTelemetriaUseCase) {
        this.serializationFacade = serializationFacade;
        this.harpiaAntiCorruptionLayer = harpiaAntiCorruptionLayer;
        this.processarTelemetriaUseCase = processarTelemetriaUseCase;
    }

    @Override
    public void onEvent(byte[] mensagem) {
        HarpiaTelemetryMessage harpiaTelemetryMessage = serializationFacade.fromSnakeCaseBytes(mensagem, HarpiaTelemetryMessage.class);
        Sensors sensors = harpiaAntiCorruptionLayer.fromHarpiaTelemetryMessage(harpiaTelemetryMessage);
        processarTelemetriaUseCase.execute(sensors);
    }
}
