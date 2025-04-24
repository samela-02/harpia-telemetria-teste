package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.impl.http;

import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.HttpFacade;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class JavaClientHttpFacade implements HttpFacade {
    private final JavaClientHttpRequestFactory javaClientHttpRequestFactory;

    public JavaClientHttpFacade(JavaClientHttpRequestFactory javaClientHttpRequestFactory) {
        this.javaClientHttpRequestFactory = javaClientHttpRequestFactory;
    }

    @Override
    public HttpResponseContainer send(HttpRequestContainer httpRequestContainer) {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest httpRequest = javaClientHttpRequestFactory.newInstance(httpRequestContainer);
            HttpResponse<String> httpResponse = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return new HttpResponseContainer(httpResponse);
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(String.format("Não foi possível realizar o request %1$s.",
                    httpRequestContainer.getMethod().getNome()), exception);
        }
    }
}
