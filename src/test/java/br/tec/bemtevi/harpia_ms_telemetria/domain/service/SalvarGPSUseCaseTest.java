package br.tec.bemtevi.harpia_ms_telemetria.domain.service;

import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.gps.SalvarGPSUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPS;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.GPSRepository;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.gps.GPSRepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.testutils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalvarGPSUseCaseTest {
    private SalvarGPSUseCase salvarGpsUseCase;
    private GPSRepository gpsRepository;

    @BeforeEach
    void setUp() {
        gpsRepository = new GPSRepositoryInMemory();
        salvarGpsUseCase = new SalvarGPSUseCase(gpsRepository);
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoGPS_QuandoOnEventForChamado_EntaoOGPSDeveSerSalvo() {
        List<GPS> gpsListAntesDoOnEvent = (List<GPS>) TestUtils.getFieldFromClass("gpsList", gpsRepository);
        assertTrue(gpsListAntesDoOnEvent.isEmpty());

        GPS gps = new GPS(null, "GPS1", 0.0, 0.0, 0.0, null, null);
        salvarGpsUseCase.onEvent(List.of(gps));

        assertFalse(gpsListAntesDoOnEvent.isEmpty());
        assertEquals(1, gpsListAntesDoOnEvent.size());
    }
}