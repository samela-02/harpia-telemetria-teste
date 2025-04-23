package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.impl.http;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Map;

@Component
public class JavaClientHttpRequestFactory {
    HttpRequest newInstance(HttpRequestContainer httpRequestContainer) {
        HttpRequest.Builder httpRequestBuilder = getBasicHttpRequestBuilder(httpRequestContainer.getUrl());
        adicionarHeadersNoBuilder(httpRequestBuilder, httpRequestContainer.getHeaders());
        adicionarMetodoNoBuilder(httpRequestBuilder, httpRequestContainer);
        return httpRequestBuilder.build();
    }

    private HttpRequest.Builder getBasicHttpRequestBuilder(String url) {
        try {
            return HttpRequest.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .uri(new URI(url))
                    .header("Accept", MediaType.APPLICATION_JSON_VALUE);
        } catch (URISyntaxException exception) {
            throw new RuntimeException("Erro ao instanciar uma URI.", exception);
        }
    }

    private void adicionarHeadersNoBuilder(HttpRequest.Builder builder, Map<String, String> headers) {
        if (headers == null) return;
        for (String mapKey : headers.keySet()) {
            builder.header(mapKey, headers.get(mapKey));
        }
    }

    private void adicionarMetodoNoBuilder(HttpRequest.Builder builder, HttpRequestContainer httpRequestContainer) {
        switch (httpRequestContainer.getMethod()) {
            case GET -> adicionarGET(builder);
            case POST -> adicionarPOST(builder, httpRequestContainer.getBody());
            case PUT -> adicionarPUT(builder, httpRequestContainer.getBody());
            case DELETE -> adicionarDELETE(builder);
        }
    }

    private void adicionarGET(HttpRequest.Builder builder) {
        builder.GET();
    }

    private void adicionarPOST(HttpRequest.Builder builder, String body) {
        if (body == null)
            body = "";
        builder.header("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        builder.POST(HttpRequest.BodyPublishers.ofString(body));
    }

    private void adicionarPUT(HttpRequest.Builder builder, String body) {
        if (body == null)
            body = "";
        builder.header("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        builder.PUT(HttpRequest.BodyPublishers.ofString(body));
    }

    private void adicionarDELETE(HttpRequest.Builder builder) {
        builder.DELETE();
    }
}
