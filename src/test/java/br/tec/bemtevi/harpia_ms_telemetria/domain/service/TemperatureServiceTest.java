package br.tec.bemtevi.harpia_ms_telemetria.domain.service;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Temperature;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.TemperatureRepository;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.temperature.TemperatureRepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.testutils.ListManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureServiceTest {
    private TemperatureService temperatureService;
    private TemperatureRepository temperatureRepository;

    @BeforeEach
    void setUp() {
        temperatureRepository = new TemperatureRepositoryInMemory();
        temperatureService = new TemperatureService(temperatureRepository);
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoTemperature_QuandoOnEventForChamado_EntaoATemperatureDeveSerSalva() {
        List<Temperature> temperatureListAntesDoOnEvent = (List<Temperature>) ListManager
                .getListFromRepositoryInMemory("temperatureList", temperatureRepository);
        assertTrue(temperatureListAntesDoOnEvent.isEmpty());

        Temperature temperature = new Temperature(null, "nome", 0.0, null, null);
        temperatureService.onEvent(List.of(temperature));

        assertFalse(temperatureListAntesDoOnEvent.isEmpty());
        assertEquals(1, temperatureListAntesDoOnEvent.size());
    }
}