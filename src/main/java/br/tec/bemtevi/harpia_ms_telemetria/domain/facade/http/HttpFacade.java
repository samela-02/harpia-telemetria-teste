package br.tec.bemtevi.harpia_ms_telemetria.domain.facade.http;

public interface HttpFacade {
    HttpResponseContainer send(HttpRequestContainer httpRequestContainer);
}
