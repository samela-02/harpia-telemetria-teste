package br.tec.bemtevi.harpia_ms_telemetria.application.usecase.temperature;

import br.tec.bemtevi.harpia_ms_telemetria.application.mediator.Mediator;
import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.ProcessarDispositivoUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.domain.enums.TipoEvento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.LoggerFacade;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.dispositivo.Dispositivo;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.DispositivoRepository;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.dispositivo.DispositivoRepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.Slf4jLoggerFacade;
import br.tec.bemtevi.harpia_ms_telemetria.testutils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcessarDispositivoUseCaseTest {
    private Mediator mediator;
    private LoggerFacade loggerFacade;
    private DispositivoRepository dispositivoRepository;
    private ProcessarDispositivoUseCase processarDispositivoUseCase;

    @BeforeEach
    void setUp() {
        mediator = new Mediator();
        loggerFacade = new Slf4jLoggerFacade();
        dispositivoRepository = new DispositivoRepositoryInMemory();
        processarDispositivoUseCase = new ProcessarDispositivoUseCase(mediator, loggerFacade, dispositivoRepository);
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoDispositivo_QuandoOnEventForChamado_EntaoODispositivoDeveSerPersistido() {
        List<Dispositivo> dispositivos = (List<Dispositivo>) TestUtils.getFieldFromClass("dispositivos", dispositivoRepository);
        assertTrue(dispositivos.isEmpty());
        Dispositivo dispositivo = new Dispositivo("ID", "IDINSTITUICAO", "IDEQUIPAMENTO", null, null, null, null, null, null, null, null);

        mediator.emitirEvento(TipoEvento.DISPOSITIVO, dispositivo);

        assertFalse(dispositivos.isEmpty());
        assertEquals(1, dispositivos.size());
    }
}