package br.tec.bemtevi.harpia_ms_telemetria.application.usecase;

import br.tec.bemtevi.harpia_ms_telemetria.application.mediator.Mediator;
import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.temperature.SalvarTemperatureUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.Temperature;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.TemperatureRepository;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.temperature.TemperatureRepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.testutils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalvarTemperatureUseCaseTest {
    private SalvarTemperatureUseCase salvarTemperatureUseCase;
    private TemperatureRepository temperatureRepository;

    @BeforeEach
    void setUp() {
        temperatureRepository = new TemperatureRepositoryInMemory();
        Mediator sensorMediator = new Mediator();
        salvarTemperatureUseCase = new SalvarTemperatureUseCase(temperatureRepository, sensorMediator);
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoTemperature_QuandoOnEventForChamado_EntaoATemperatureDeveSerSalva() {
        List<Temperature> temperatureListAntesDoOnEvent = (List<Temperature>) TestUtils
                .getFieldFromClass("temperatureList", temperatureRepository);
        assertTrue(temperatureListAntesDoOnEvent.isEmpty());

        Temperature temperature = new Temperature(null, "nome", 0.0, LocalDateTime.now(), null, null);
        salvarTemperatureUseCase.onEvent(List.of(temperature));

        assertFalse(temperatureListAntesDoOnEvent.isEmpty());
        assertEquals(1, temperatureListAntesDoOnEvent.size());
    }
}