package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.observer.impl;

import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.ProcessarTelemetriaUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.domain.dto.TelemetryDto;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.SerializationFacade;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.observer.TelemetriaObserver;
import org.springframework.stereotype.Component;

@Component
public class TelemetriaObserverImpl implements TelemetriaObserver {
    private final SerializationFacade serializationFacade;
    private final ProcessarTelemetriaUseCase processarTelemetriaUseCase;

    public TelemetriaObserverImpl(SerializationFacade serializationFacade,
                                  ProcessarTelemetriaUseCase processarTelemetriaUseCase) {
        this.serializationFacade = serializationFacade;
        this.processarTelemetriaUseCase = processarTelemetriaUseCase;
    }

    @Override
    public void onEvent(byte[] mensagem) {
        TelemetryDto telemetryDto = serializationFacade.fromSnakeCaseBytes(mensagem, TelemetryDto.class);
        processarTelemetriaUseCase.execute(telemetryDto);
    }
}
