package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.sse;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPSTracker;
import br.tec.bemtevi.harpia_ms_telemetria.domain.sse.SSE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component(value = "GPSSSE")
public class GPSSSE implements SSE {
    private static final Logger log = LoggerFactory.getLogger(GPSSSE.class);

    private final Map<String, SseEmitter> sseEmitterMap;

    public GPSSSE() {
        sseEmitterMap = new ConcurrentHashMap<>();
    }

    @Override
    public void emit(Object object) {
        try {
            GPSTracker gpsTracker = (GPSTracker) object;
            log.info("Emitindo evento de gps que pertence ao Harpia {}.", gpsTracker.getIdEquipamento());
            SseEmitter.SseEventBuilder event = SseEmitter
                    .event()
                    .data(gpsTracker)
                    .id(gpsTracker.getIdEquipamento())
                    .name("GPS");
            // TODO garantir que o usuário logado esteja recebendo eventos da sua instituição caso não seja admin
            SseEmitter sseEmitter = findEmitterByIdInstituicao(gpsTracker.getIdInstituicao());
            sseEmitter.send(event);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível emitir o evento.", e);
        }
    }

    public SseEmitter findGPSData(String idInstituicao) {
        return findEmitterByIdInstituicao(idInstituicao);
    }

    private SseEmitter findEmitterByIdInstituicao(String idInstituicao) {
        SseEmitter sseEmitter = sseEmitterMap.get(idInstituicao);
        if (sseEmitter == null) {
            sseEmitter = new SseEmitter(-1L);
            sseEmitterMap.put(idInstituicao, sseEmitter);
        }
        return sseEmitter;
    }
}
