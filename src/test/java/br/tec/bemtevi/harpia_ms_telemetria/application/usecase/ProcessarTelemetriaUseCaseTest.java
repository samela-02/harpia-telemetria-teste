package br.tec.bemtevi.harpia_ms_telemetria.application.usecase;

import br.tec.bemtevi.harpia_ms_telemetria.domain.dto.SensorsDto;
import br.tec.bemtevi.harpia_ms_telemetria.domain.dto.TelemetryDto;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Equipamento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPS;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.LTE;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Temperature;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.GPSObserver;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.LTEObserver;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.TemperatureObserver;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.EquipamentoRepository;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.GPSRepository;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.LTERepository;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.TemperatureRepository;
import br.tec.bemtevi.harpia_ms_telemetria.domain.service.GPSService;
import br.tec.bemtevi.harpia_ms_telemetria.domain.service.LTEService;
import br.tec.bemtevi.harpia_ms_telemetria.domain.service.TemperatureService;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.equipamento.EquipamentoRepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.gps.GPSRepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.lte.LTERepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.temperature.TemperatureRepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.testutils.ListManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProcessarTelemetriaUseCaseTest {
    private ProcessarTelemetriaUseCase processarTelemetriaUseCase;
    private GPSRepository gpsRepository;
    private LTERepository lteRepository;
    private TemperatureRepository temperatureRepository;
    private EquipamentoRepository equipamentoRepository;

    @BeforeEach
    void setUp() {
        gpsRepository = new GPSRepositoryInMemory();
        GPSObserver gpsObserver = new GPSService(gpsRepository);
        lteRepository = new LTERepositoryInMemory();
        LTEObserver lteObserver = new LTEService(lteRepository);
        temperatureRepository = new TemperatureRepositoryInMemory();
        TemperatureObserver temperatureObserver = new TemperatureService(temperatureRepository);

        equipamentoRepository = new EquipamentoRepositoryInMemory();
        processarTelemetriaUseCase = new ProcessarTelemetriaUseCase(equipamentoRepository,
                List.of(gpsObserver),
                List.of(lteObserver),
                List.of(temperatureObserver));
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoTelemetryDto_QuandoExecuteForChamado_EntaoUmEquipamentoDeveSerSalvoETodosOsObserversDevemSerNotificados() {
        List<Equipamento> equipamentos = (List<Equipamento>) ListManager.getListFromRepositoryInMemory("equipamentos", equipamentoRepository);
        List<GPS> gpsList = (List<GPS>) ListManager.getListFromRepositoryInMemory("gpsList", gpsRepository);
        List<LTE> lteList = (List<LTE>) ListManager.getListFromRepositoryInMemory("lteList", lteRepository);
        List<Temperature> temperatureList = (List<Temperature>) ListManager.getListFromRepositoryInMemory("temperatureList", temperatureRepository);
        assertTrue(equipamentos.isEmpty());
        assertTrue(gpsList.isEmpty());
        assertTrue(lteList.isEmpty());
        assertTrue(temperatureList.isEmpty());

        LTE lte = new LTE(null, "nome", 0.0, "carrier", "nminternetstate", "nmsimcardstate", "nmstatus");
        GPS gps = new GPS(null, "nome", 0.0, 0.0, 0.0);
        Temperature temperature = new Temperature(null, "nome", 0.0);
        SensorsDto sensorsDto = new SensorsDto(List.of(lte), List.of(gps), List.of(temperature));
        TelemetryDto telemetryDto = new TelemetryDto("H-1234", sensorsDto);
        processarTelemetriaUseCase.execute(telemetryDto);

        assertFalse(equipamentos.isEmpty());
        assertFalse(gpsList.isEmpty());
        assertFalse(lteList.isEmpty());
        assertFalse(temperatureList.isEmpty());
        assertEquals(1, equipamentos.size());
        assertEquals(1, gpsList.size());
        assertEquals(1, lteList.size());
        assertEquals(1, temperatureList.size());

        Optional<GPS> optionalGPS = gpsList.stream().findFirst();
        Optional<LTE> optionalLTE = lteList.stream().findFirst();
        Optional<Temperature> optionalTemperature = temperatureList.stream().findFirst();
        assertTrue(optionalGPS.isPresent());
        assertTrue(optionalLTE.isPresent());
        assertTrue(optionalTemperature.isPresent());
        assertNotNull(optionalGPS.get().getEquipamento());
        assertNotNull(optionalLTE.get().getEquipamento());
        assertNotNull(optionalTemperature.get().getEquipamento());
    }
}