package br.tec.bemtevi.harpia_ms_telemetria.application.usecase;

import br.tec.bemtevi.harpia_ms_telemetria.application.factory.SensorObserverFactory;
import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.gps.PropagarGPSUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.gps.SalvarGPSUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.lte.SalvarLTEUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.telemetria.ProcessarTelemetriaUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.temperature.SalvarTemperatureUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.*;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.GPSRepository;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.LTERepository;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.TemperatureRepository;
import br.tec.bemtevi.harpia_ms_telemetria.domain.sse.SSE;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.gps.GPSRepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.lte.LTERepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.temperature.TemperatureRepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.sse.GPSSSERepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.testutils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcessarTelemetriaUseCaseTest {
    private ProcessarTelemetriaUseCase processarTelemetriaUseCase;
    private GPSRepository gpsRepository;
    private LTERepository lteRepository;
    private TemperatureRepository temperatureRepository;
    private SSE sse;

    @BeforeEach
    void setUp() {
        gpsRepository = new GPSRepositoryInMemory();
        sse = new GPSSSERepositoryInMemory();
        lteRepository = new LTERepositoryInMemory();
        temperatureRepository = new TemperatureRepositoryInMemory();
        SalvarGPSUseCase salvarGPSUseCase = new SalvarGPSUseCase(gpsRepository);
        PropagarGPSUseCase propagarGPSUseCase = new PropagarGPSUseCase(sse);
        SalvarLTEUseCase salvarLTEUseCase = new SalvarLTEUseCase(lteRepository);
        SalvarTemperatureUseCase salvarTemperatureUseCase = new SalvarTemperatureUseCase(temperatureRepository);
        SensorObserverFactory sensorObserverFactory = new SensorObserverFactory(salvarGPSUseCase,
                propagarGPSUseCase,
                salvarLTEUseCase,
                salvarTemperatureUseCase);
        processarTelemetriaUseCase = new ProcessarTelemetriaUseCase(sensorObserverFactory);
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoSensors_QuandoExecuteForChamado_TodosOsObserversDevemSerNotificados() {
        List<GPS> gpsList = (List<GPS>) TestUtils.getFieldFromClass("gpsList", gpsRepository);
        List<GPSTracker> gpsTrackerList = (List<GPSTracker>) TestUtils.getFieldFromClass("gpsTrackerList", sse);
        List<LTE> lteList = (List<LTE>) TestUtils.getFieldFromClass("lteList", lteRepository);
        List<Temperature> temperatureList = (List<Temperature>) TestUtils.getFieldFromClass("temperatureList", temperatureRepository);
        assertTrue(gpsList.isEmpty());
        assertTrue(gpsTrackerList.isEmpty());
        assertTrue(lteList.isEmpty());
        assertTrue(temperatureList.isEmpty());

        LocalDateTime dtCriacao = LocalDateTime.now();
        Equipamento equipamento = new Equipamento(null, 1L, 1L, "H-1234", "H-1234", "H-1234", 1, null);
        Instituicao instituicao = new Instituicao("BTV");
        LTE lte = new LTE(null, "nome", 0.0, "carrier", "nminternetstate", "nmsimcardstate", "nmstatus", dtCriacao, equipamento, instituicao);
        GPS gps = new GPS(null, "nome", 0.0, 0.0, 0.0, dtCriacao, equipamento, instituicao);
        Temperature temperature = new Temperature(null, "nome", 0.0, dtCriacao, equipamento, instituicao);
        Sensors sensors = new Sensors("BTV", "H1234", List.of(lte), List.of(gps), List.of(temperature));
        processarTelemetriaUseCase.execute(sensors);

        assertFalse(gpsList.isEmpty());
        assertFalse(gpsTrackerList.isEmpty());
        assertFalse(lteList.isEmpty());
        assertFalse(temperatureList.isEmpty());
        assertEquals(1, gpsList.size());
        assertEquals(1, lteList.size());
        assertEquals(1, temperatureList.size());
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoSensorsSemSensoresDeTemperatura_QuandoExecuteForChamado_EntaoOObserverDeTemperatureNaoDeveSerNotificado() {
        List<GPS> gpsList = (List<GPS>) TestUtils.getFieldFromClass("gpsList", gpsRepository);
        List<GPSTracker> gpsTrackerList = (List<GPSTracker>) TestUtils.getFieldFromClass("gpsTrackerList", sse);
        List<LTE> lteList = (List<LTE>) TestUtils.getFieldFromClass("lteList", lteRepository);
        List<Temperature> temperatureList = (List<Temperature>) TestUtils.getFieldFromClass("temperatureList", temperatureRepository);
        assertTrue(gpsList.isEmpty());
        assertTrue(gpsTrackerList.isEmpty());
        assertTrue(lteList.isEmpty());
        assertTrue(temperatureList.isEmpty());

        LocalDateTime dtCriacao = LocalDateTime.now();
        Equipamento equipamento = new Equipamento(null, 1L, 1L, "H-1234", "H-1234", "H-1234", 1, null);
        Instituicao instituicao = new Instituicao("BTV");
        LTE lte = new LTE(null, "nome", 0.0, "carrier", "nminternetstate", "nmsimcardstate", "nmstatus", dtCriacao, equipamento, instituicao);
        GPS gps = new GPS(null, "nome", 0.0, 0.0, 0.0, dtCriacao, equipamento, instituicao);
        Sensors sensors = new Sensors("BTV", "H1234", List.of(lte), List.of(gps), null);
        processarTelemetriaUseCase.execute(sensors);

        assertFalse(gpsList.isEmpty());
        assertFalse(gpsTrackerList.isEmpty());
        assertFalse(lteList.isEmpty());
        assertEquals(1, gpsList.size());
        assertEquals(1, lteList.size());
        assertTrue(temperatureList.isEmpty());
    }
}