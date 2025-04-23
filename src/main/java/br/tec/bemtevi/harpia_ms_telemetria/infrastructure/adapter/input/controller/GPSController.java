package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.controller;

import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.sse.GPSSSE;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping(value = "/api/v1/gps")
public class GPSController {
    private final GPSSSE gpssse;

    public GPSController(GPSSSE gpssse) {
        this.gpssse = gpssse;
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'COORDENADOR_OPERACAO', 'OPERADOR_CENTRAL')")
    @GetMapping
    public SseEmitter findGpsData(@RequestParam String idInstituicao) {
        return gpssse.findGPSData(idInstituicao);
    }
}
