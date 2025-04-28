package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.controller;

import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.gps.BuscarGPSStreamUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.domain.exception.PermissaoException;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPSTracker;
import org.springframework.http.ResponseEntity;
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
    private final BuscarGPSStreamUseCase buscarGPSStreamUseCase;

    public GPSController(BuscarGPSStreamUseCase buscarGPSStreamUseCase) {
        this.buscarGPSStreamUseCase = buscarGPSStreamUseCase;
    }

    @GetMapping
    public ResponseEntity<Flux<ServerSentEvent<GPSTracker>>> findGpsData(@RequestParam String idInstituicao,
                                                         ServerHttpRequest serverHttpRequest) {
        String bearerToken = serverHttpRequest.getHeaders().getFirst("Authorization");
        try {
            Flux<ServerSentEvent<GPSTracker>> body =
                    (Flux<ServerSentEvent<GPSTracker>>) buscarGPSStreamUseCase.execute(bearerToken, idInstituicao);
            return ResponseEntity.ok(body);
        } catch (PermissaoException e) {
            return ResponseEntity.status(403).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
