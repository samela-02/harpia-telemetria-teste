package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.http;

import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.http.HttpFacade;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.http.HttpRequestContainer;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.http.HttpResponseContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class JavaClientHttpFacade implements HttpFacade {
    private static final Logger log = LoggerFactory.getLogger(JavaClientHttpFacade.class);
    private final JavaClientHttpRequestFactory javaClientHttpRequestFactory;

    public JavaClientHttpFacade(JavaClientHttpRequestFactory javaClientHttpRequestFactory) {
        this.javaClientHttpRequestFactory = javaClientHttpRequestFactory;
    }

    @Override
    public HttpResponseContainer send(HttpRequestContainer httpRequestContainer) {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest httpRequest = javaClientHttpRequestFactory.newInstance(httpRequestContainer);
            log.debug("Fazendo request {} em {}", httpRequestContainer.getMethod(), httpRequestContainer.getUrl());
            HttpResponse<String> httpResponse = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return new HttpResponseContainer(httpResponse);
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(String.format("Não foi possível realizar o request %1$s.",
                    httpRequestContainer.getMethod().getNome()), exception);
        }
    }
}
