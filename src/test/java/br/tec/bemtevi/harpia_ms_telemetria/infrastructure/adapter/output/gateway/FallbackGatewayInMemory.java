package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.gateway;

import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.LoggerFacade;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.SerializationFacade;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.http.HttpFacade;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.dto.FallbackDto;

public class FallbackGatewayInMemory extends FallbackGateway {
    private boolean fezFallback = false;

    public FallbackGatewayInMemory(LoggerFacade loggerFacade, HttpFacade httpFacade, String fallbackUrl, SerializationFacade serializationFacade) {
        super(loggerFacade, httpFacade, fallbackUrl, serializationFacade);
    }

    @Override
    public void enviarFallback(FallbackDto fallbackDto) {
        fezFallback = true;
    }
}