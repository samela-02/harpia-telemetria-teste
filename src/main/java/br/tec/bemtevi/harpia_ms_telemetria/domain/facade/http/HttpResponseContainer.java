package br.tec.bemtevi.harpia_ms_telemetria.domain.facade.http;

import java.net.http.HttpResponse;

public class HttpResponseContainer {
    private final String responseBody;
    private final int statusCode;

    public HttpResponseContainer(HttpResponse<String> httpResponse) {
        this.responseBody = httpResponse.body();
        this.statusCode = httpResponse.statusCode();
    }

    public String getResponseBody() {
        return responseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
