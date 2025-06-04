package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.gateway;

import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.LoggerFacade;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.SerializationFacade;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.http.HttpFacade;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.http.HttpMethod;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.http.HttpRequestContainer;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.http.HttpResponseContainer;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.dto.FallbackDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FallbackGateway {
    private final LoggerFacade loggerFacade;
    private final HttpFacade httpFacade;
    private final String fallbackUrl;
    private final SerializationFacade serializationFacade;

    public FallbackGateway(LoggerFacade loggerFacade,
                           HttpFacade httpFacade,
                           @Value("${fallback.url}") String fallbackUrl,
                           SerializationFacade serializationFacade) {
        this.loggerFacade = loggerFacade;
        this.httpFacade = httpFacade;
        this.fallbackUrl = fallbackUrl;
        this.serializationFacade = serializationFacade;
    }

    public void enviarFallback(FallbackDto fallbackDto) {
        loggerFacade.info("Iniciando processo de fallback.");
        HttpRequestContainer httpRequestContainer = new HttpRequestContainer
                .Builder(HttpMethod.POST, fallbackUrl)
                .comBody(serializationFacade.asSnakeCaseString(fallbackDto))
                .build();
        HttpResponseContainer httpResponseContainer = httpFacade.send(httpRequestContainer);
        validarResposta(httpResponseContainer);
    }

    private void validarResposta(HttpResponseContainer httpResponseContainer) {
        if (httpResponseContainer.getStatusCode() != 200) {
            loggerFacade.warn(String.format("Erro ao fazer fallback: %s.", httpResponseContainer.getResponseBody()));
            throw new RuntimeException("Erro ao realizar fallback.");
        }
    }
}
