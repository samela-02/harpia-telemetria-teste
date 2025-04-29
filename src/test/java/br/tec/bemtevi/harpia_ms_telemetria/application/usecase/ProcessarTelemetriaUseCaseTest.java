package br.tec.bemtevi.harpia_ms_telemetria.application.usecase;

import br.tec.bemtevi.harpia_ms_telemetria.application.mediator.SensorMediator;
import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.telemetria.ProcessarTelemetriaUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPS;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Sensors;
import br.tec.bemtevi.harpia_ms_telemetria.testutils.TestUtils;
import br.tec.bemtevi.harpia_ms_telemetria.testutils.mediator.SensorObserverInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcessarTelemetriaUseCaseTest {
    private ProcessarTelemetriaUseCase processarTelemetriaUseCase;
    private SensorObserverInMemory sensorObserverInMemory;

    @BeforeEach
    void setUp() {
        SensorMediator sensorMediator = new SensorMediator();
        sensorObserverInMemory = new SensorObserverInMemory(sensorMediator);
        processarTelemetriaUseCase = new ProcessarTelemetriaUseCase(sensorMediator);
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoSensors_QuandoExecuteForChamado_TodosOsObserversDevemSerNotificados() {
        List<Object> eventos = (List<Object>) TestUtils.getFieldFromClass("eventos", sensorObserverInMemory);
        assertTrue(eventos.isEmpty());
        List<GPS> gpsList = new ArrayList<>();
        GPS gps = new GPS("id", "GPS", 0.0, 0.0, 0.0, LocalDateTime.now(), null, null);
        gpsList.add(gps);
        Sensors sensors = new Sensors("TIVIC_PDI", "H-1234", null, gpsList, null);

        processarTelemetriaUseCase.execute(sensors);

        assertFalse(eventos.isEmpty());
        assertEquals(1, eventos.size());
    }
}