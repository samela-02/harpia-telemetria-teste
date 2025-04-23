package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.controller;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPSTracker;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.sse.GPSSSE;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/api/v1/gps")
public class GPSController {
    private final GPSSSE gpssse;

    public GPSController(GPSSSE gpssse) {
        this.gpssse = gpssse;
    }

    @GetMapping
    public Flux<ServerSentEvent<GPSTracker>> findGpsData(@RequestParam String idInstituicao,
                                                         ServerHttpRequest serverHttpRequest) {
        String bearerToken = serverHttpRequest.getHeaders().getFirst("Authorization");
        return gpssse.findGPSData(idInstituicao, bearerToken);
    }
}
