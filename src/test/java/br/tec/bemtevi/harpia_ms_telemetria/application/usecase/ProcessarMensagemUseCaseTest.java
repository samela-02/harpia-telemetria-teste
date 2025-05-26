package br.tec.bemtevi.harpia_ms_telemetria.application.usecase;

import br.tec.bemtevi.harpia_ms_telemetria.application.mediator.Mediator;
import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.gps.SalvarGPSUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.LoggerFacade;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Mensagem;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.dispositivo.Dispositivo;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.GPS;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.Sensors;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.DispositivoRepository;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.GPSRepository;
import br.tec.bemtevi.harpia_ms_telemetria.domain.service.ReflectionService;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.dispositivo.DispositivoRepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.gps.GPSRepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.Slf4jLoggerFacade;
import br.tec.bemtevi.harpia_ms_telemetria.testutils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcessarMensagemUseCaseTest {
    private ProcessarMensagemUseCase processarMensagemUseCase;
    private GPSRepository gpsRepository;
    private DispositivoRepository dispositivoRepository;

    @BeforeEach
    void setUp() {
        ReflectionService reflectionService = new ReflectionService();
        Mediator mediator = new Mediator();

        gpsRepository = new GPSRepositoryInMemory();
        SalvarGPSUseCase salvarGPSUseCase = new SalvarGPSUseCase(gpsRepository, mediator);
        ProcessarSensorsUseCase processarSensorsUseCase = new ProcessarSensorsUseCase(reflectionService, mediator);

        LoggerFacade loggerFacade = new Slf4jLoggerFacade();
        dispositivoRepository = new DispositivoRepositoryInMemory();
        ProcessarDispositivoUseCase processarDispositivoUseCase = new ProcessarDispositivoUseCase(mediator, loggerFacade, dispositivoRepository);

        processarMensagemUseCase = new ProcessarMensagemUseCase(reflectionService, mediator);
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoMensagem_QuandoExecuteForChamado_EntaoUmEventoDeCadaAtributoDeveSerDisparado() {
        List<GPS> gpsList = (List<GPS>) TestUtils.getFieldFromClass("gpsList", gpsRepository);
        assertTrue(gpsList.isEmpty());
        List<Dispositivo> dispositivos = (List<Dispositivo>) TestUtils.getFieldFromClass("dispositivos", dispositivoRepository);
        assertTrue(dispositivos.isEmpty());
        Dispositivo dispositivo = new Dispositivo("ID", "IDINSTITUICAO", "IDEQUIPAMENTO", null, null, null, null, null, null, null, null);
        Sensors sensors = new Sensors("IDINSTITUICAO", "IDEQUIPAMENTO", null, List.of(new GPS(null, null, null, null, null, null, null, null)), null, null);
        Mensagem mensagem = new Mensagem(dispositivo, sensors);

        processarMensagemUseCase.execute(mensagem);

        assertFalse(gpsList.isEmpty());
        assertEquals(1, gpsList.size());
        assertFalse(dispositivos.isEmpty());
        assertEquals(1, dispositivos.size());
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoComAtributoNulo_QuandoExecuteForChamado_EntaoUmEventoDeCadaAtributoNaoNuloDeveSerDisparado() {
        List<GPS> gpsList = (List<GPS>) TestUtils.getFieldFromClass("gpsList", gpsRepository);
        assertTrue(gpsList.isEmpty());
        List<Dispositivo> dispositivos = (List<Dispositivo>) TestUtils.getFieldFromClass("dispositivos", dispositivoRepository);
        assertTrue(dispositivos.isEmpty());
        Dispositivo dispositivo = new Dispositivo("ID", "IDINSTITUICAO", "IDEQUIPAMENTO", null, null, null, null, null, null, null, null);
        Mensagem mensagem = new Mensagem(dispositivo, null);

        processarMensagemUseCase.execute(mensagem);

        assertTrue(gpsList.isEmpty());
        assertFalse(dispositivos.isEmpty());
        assertEquals(1, dispositivos.size());
    }
}