package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.sse;

import br.tec.bemtevi.harpia_ms_telemetria.domain.sse.SSE;
import org.springframework.stereotype.Component;

@Component(value = "GPSSSE")
public class GPSSSE implements SSE {
    @Override
    public void emit(Object object) {
        throw new UnsupportedOperationException();
    }
}
