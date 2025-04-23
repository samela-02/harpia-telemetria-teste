package br.tec.bemtevi.harpia_ms_telemetria.application.usecase;

import br.tec.bemtevi.harpia_ms_telemetria.application.factory.SensorObserverFactory;
import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.gps.SalvarGPSUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.lte.SalvarLTEUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.telemetria.ProcessarTelemetriaUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.temperature.SalvarTemperatureUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.gps.GPS;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.lte.LTE;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.Sensors;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.temperature.Temperature;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.GPSRepository;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.LTERepository;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.TemperatureRepository;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.gps.GPSRepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.lte.LTERepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.temperature.TemperatureRepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.testutils.ListManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcessarTelemetriaUseCaseTest {
    private ProcessarTelemetriaUseCase processarTelemetriaUseCase;
    private GPSRepository gpsRepository;
    private LTERepository lteRepository;
    private TemperatureRepository temperatureRepository;

    @BeforeEach
    void setUp() {
        gpsRepository = new GPSRepositoryInMemory();
        lteRepository = new LTERepositoryInMemory();
        temperatureRepository = new TemperatureRepositoryInMemory();
        SalvarGPSUseCase salvarGPSUseCase = new SalvarGPSUseCase(gpsRepository);
        SalvarLTEUseCase salvarLTEUseCase = new SalvarLTEUseCase(lteRepository);
        SalvarTemperatureUseCase salvarTemperatureUseCase = new SalvarTemperatureUseCase(temperatureRepository);
        SensorObserverFactory sensorObserverFactory = new SensorObserverFactory(salvarGPSUseCase,
                salvarLTEUseCase,
                salvarTemperatureUseCase);
        processarTelemetriaUseCase = new ProcessarTelemetriaUseCase(sensorObserverFactory);
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoTelemetryDto_QuandoExecuteForChamado_EntaoUmEquipamentoDeveSerSalvoETodosOsObserversDevemSerNotificados() {
        List<GPS> gpsList = (List<GPS>) ListManager.getListFromRepositoryInMemory("gpsList", gpsRepository);
        List<LTE> lteList = (List<LTE>) ListManager.getListFromRepositoryInMemory("lteList", lteRepository);
        List<Temperature> temperatureList = (List<Temperature>) ListManager.getListFromRepositoryInMemory("temperatureList", temperatureRepository);
        assertTrue(gpsList.isEmpty());
        assertTrue(lteList.isEmpty());
        assertTrue(temperatureList.isEmpty());

        LTE lte = new LTE(null, "nome", 0.0, "carrier", "nminternetstate", "nmsimcardstate", "nmstatus", null, null);
        GPS gps = new GPS(null, "nome", 0.0, 0.0, 0.0, null, null);
        Temperature temperature = new Temperature(null, "nome", 0.0, null, null);
        Sensors sensors = new Sensors("BTV", "H1234", List.of(lte), List.of(gps), List.of(temperature));
        processarTelemetriaUseCase.execute(sensors);

        assertFalse(gpsList.isEmpty());
        assertFalse(lteList.isEmpty());
        assertFalse(temperatureList.isEmpty());
        assertEquals(1, gpsList.size());
        assertEquals(1, lteList.size());
        assertEquals(1, temperatureList.size());
    }
}