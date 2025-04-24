package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade;

import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.impl.http.HttpRequestContainer;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.impl.http.HttpResponseContainer;

public interface HttpFacade {
    HttpResponseContainer send(HttpRequestContainer httpRequestContainer);
}
