package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.sse;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPSTracker;
import br.tec.bemtevi.harpia_ms_telemetria.domain.sse.SSE;
import br.tec.bemtevi.harpia_ms_telemetria.domain.storage.GPSStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component(value = "GPSSSE")
public class GPSSSE implements SSE, GPSStorage {
    private static final Logger log = LoggerFactory.getLogger(GPSSSE.class);

    private final Map<String, Sinks.Many<ServerSentEvent<GPSTracker>>> sinkMap;

    public GPSSSE() {
        sinkMap = new ConcurrentHashMap<>();
    }

    @Override
    public void emit(Object object) {
        GPSTracker gpsTracker = (GPSTracker) object;
        log.info("Emitindo evento de gps que pertence ao Harpia {}.", gpsTracker.getIdEquipamento());
        ServerSentEvent<GPSTracker> event = ServerSentEvent
                .builder(gpsTracker)
                .event("GPS")
                .build();
        Sinks.Many<ServerSentEvent<GPSTracker>> gpsTrackerSink = findSinkByIdInstituicao(gpsTracker.getIdInstituicao());
        gpsTrackerSink.tryEmitNext(event);
    }

    @Override
    public Object findGpsData(String idInstituicao) {
        Sinks.Many<ServerSentEvent<GPSTracker>> sink = findSinkByIdInstituicao(idInstituicao);
        return sink
                .asFlux()
                .doOnSubscribe(subscription -> log.info("Novo cliente conectado: {}.", subscription))
                .doOnCancel(() -> log.info("Cliente desconectado."))
                .doOnError(error -> log.error("Erro ao retornar evento.", error))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(200)));
    }

    private Sinks.Many<ServerSentEvent<GPSTracker>> findSinkByIdInstituicao(String idInstituicao) {
        Sinks.Many<ServerSentEvent<GPSTracker>> sink = sinkMap.get(idInstituicao);
        if (sink == null) {
            sink = Sinks.many().multicast().onBackpressureBuffer();
            sinkMap.put(idInstituicao, sink);
        }
        return sink;
    }
}
