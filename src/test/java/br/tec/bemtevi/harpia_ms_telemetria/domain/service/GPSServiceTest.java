package br.tec.bemtevi.harpia_ms_telemetria.domain.service;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPS;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.GPSRepository;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.gps.GPSRepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.testutils.ListManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GPSServiceTest {
    private GPSService gpsService;
    private GPSRepository gpsRepository;

    @BeforeEach
    void setUp() {
        gpsRepository = new GPSRepositoryInMemory();
        gpsService = new GPSService(gpsRepository);
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoGPS_QuandoOnEventForChamado_EntaoOGPSDeveSerSalvo() {
        List<GPS> gpsListAntesDoOnEvent = (List<GPS>) ListManager.getListFromRepository("gpsList", gpsRepository);
        assertTrue(gpsListAntesDoOnEvent.isEmpty());

        GPS gps = new GPS(null, "GPS1", 0.0, 0.0, 0.0, null);
        gpsService.onEvent(gps);

        assertFalse(gpsListAntesDoOnEvent.isEmpty());
        assertEquals(1, gpsListAntesDoOnEvent.size());
    }
}