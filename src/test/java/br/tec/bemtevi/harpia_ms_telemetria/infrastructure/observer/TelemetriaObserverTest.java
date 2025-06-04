package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.observer;

import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.ProcessarMensagemUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.ProcessarMensagemUseCaseDefeituoso;
import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.ProcessarMensagemUseCaseFake;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.LoggerFacade;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.SerializationFacade;
import br.tec.bemtevi.harpia_ms_telemetria.domain.service.EquipamentoService;
import br.tec.bemtevi.harpia_ms_telemetria.domain.service.EquipamentoServiceFake;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia.HarpiaTelemetryMessage;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.gateway.FallbackGateway;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.gateway.FallbackGatewayDefeituoso;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.gateway.FallbackGatewayInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.anticorruptionlayer.HarpiaAntiCorruptionLayer;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.anticorruptionlayer.HarpiaAntiCorruptionLayerDefeituoso;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.anticorruptionlayer.HarpiaAntiCorruptionLayerFake;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.JacksonSerializationFacade;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.Slf4jLoggerFacade;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.gerenciador.aplicacao.GerenciadorDaAplicacao;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.gerenciador.aplicacao.GerenciadorDaAplicacaoFake;
import br.tec.bemtevi.harpia_ms_telemetria.testutils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TelemetriaObserverTest {
    private TelemetriaObserver telemetriaObserver;
    private GerenciadorDaAplicacao gerenciadorDaAplicacao;
    private FallbackGateway fallbackGateway;
    private ProcessarMensagemUseCase processarMensagemUseCase;
    private EquipamentoService equipamentoService;
    private HarpiaAntiCorruptionLayer harpiaAntiCorruptionLayer;
    private SerializationFacade serializationFacade;
    private LoggerFacade loggerFacade;

    @BeforeEach
    void setUp() {
        loggerFacade = new Slf4jLoggerFacade();
        serializationFacade = new JacksonSerializationFacade();
        harpiaAntiCorruptionLayer = new HarpiaAntiCorruptionLayerFake(null, null, null, null);
        equipamentoService = new EquipamentoServiceFake(null);
        processarMensagemUseCase = new ProcessarMensagemUseCaseFake(null, null);
        fallbackGateway = new FallbackGatewayInMemory(null, null, null, null);
        gerenciadorDaAplicacao = new GerenciadorDaAplicacaoFake(loggerFacade, null);
        telemetriaObserver = new TelemetriaObserver(loggerFacade, serializationFacade, harpiaAntiCorruptionLayer, equipamentoService, processarMensagemUseCase, fallbackGateway, gerenciadorDaAplicacao);
    }

    @Test
    void deveProcessarAMensagemComSucesso() {
        HarpiaTelemetryMessage harpiaTelemetryMessage = new HarpiaTelemetryMessage("id", "idInstituicao", null, null);
        byte[] snakeCaseBytes = serializationFacade.asSnakeCaseBytes(harpiaTelemetryMessage);

        telemetriaObserver.onEvent(snakeCaseBytes);
    }

    @Test
    void deveLancarCincoExcecoesEDesligarAAplicacaoAposAUltimaCasoUmaExcecaoSejaLancadaAntesDoProcessamentoDaMensagem() {
        harpiaAntiCorruptionLayer = new HarpiaAntiCorruptionLayerDefeituoso(null, null, null, null);
        telemetriaObserver = new TelemetriaObserver(loggerFacade, serializationFacade, harpiaAntiCorruptionLayer, equipamentoService, processarMensagemUseCase, fallbackGateway, gerenciadorDaAplicacao);
        boolean desligou = (boolean) TestUtils.getFieldFromClass("desligou", gerenciadorDaAplicacao);
        assertFalse(desligou);
        HarpiaTelemetryMessage harpiaTelemetryMessage = new HarpiaTelemetryMessage("id", "idInstituicao", null, null);
        byte[] snakeCaseBytes = serializationFacade.asSnakeCaseBytes(harpiaTelemetryMessage);

        for (int i = 0; i < 5; i++)
            assertThrows(RuntimeException.class, () -> telemetriaObserver.onEvent(snakeCaseBytes));

        desligou = (boolean) TestUtils.getFieldFromClass("desligou", gerenciadorDaAplicacao);
        assertTrue(desligou);
    }

    @Test
    void deveFazerUmFallbackQuandoUmaExcecaoForLancadaDuranteOProcessamentoDaMensagem() {
        processarMensagemUseCase = new ProcessarMensagemUseCaseDefeituoso(null, null);
        telemetriaObserver = new TelemetriaObserver(loggerFacade, serializationFacade, harpiaAntiCorruptionLayer, equipamentoService, processarMensagemUseCase, fallbackGateway, gerenciadorDaAplicacao);
        boolean fezFallback = (boolean) TestUtils.getFieldFromClass("fezFallback", fallbackGateway);
        assertFalse(fezFallback);
        HarpiaTelemetryMessage harpiaTelemetryMessage = new HarpiaTelemetryMessage("id", "idInstituicao", null, null);
        byte[] snakeCaseBytes = serializationFacade.asSnakeCaseBytes(harpiaTelemetryMessage);

        telemetriaObserver.onEvent(snakeCaseBytes);

        fezFallback = (boolean) TestUtils.getFieldFromClass("fezFallback", fallbackGateway);
        assertTrue(fezFallback);
    }

    @Test
    void deveLancarCincoExcecoesEDesligarAAplicacaoQuandoOFallbackNaoPuderSerFeito() {
        fallbackGateway = new FallbackGatewayDefeituoso(null, null, null, null);
        processarMensagemUseCase = new ProcessarMensagemUseCaseDefeituoso(null, null);
        telemetriaObserver = new TelemetriaObserver(loggerFacade, serializationFacade, harpiaAntiCorruptionLayer, equipamentoService, processarMensagemUseCase, fallbackGateway, gerenciadorDaAplicacao);
        boolean desligou = (boolean) TestUtils.getFieldFromClass("desligou", gerenciadorDaAplicacao);
        assertFalse(desligou);
        HarpiaTelemetryMessage harpiaTelemetryMessage = new HarpiaTelemetryMessage("id", "idInstituicao", null, null);
        byte[] snakeCaseBytes = serializationFacade.asSnakeCaseBytes(harpiaTelemetryMessage);

        for (int i = 0; i < 5; i++)
            assertThrows(RuntimeException.class, () -> telemetriaObserver.onEvent(snakeCaseBytes));

        desligou = (boolean) TestUtils.getFieldFromClass("desligou", gerenciadorDaAplicacao);
        assertTrue(desligou);
    }
}