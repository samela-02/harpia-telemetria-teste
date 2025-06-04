package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.observer;

import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.ProcessarMensagemUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.LoggerFacade;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.SerializationFacade;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Mensagem;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.domain.service.EquipamentoService;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia.HarpiaTelemetryMessage;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.dto.FallbackDto;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.gateway.FallbackGateway;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.anticorruptionlayer.HarpiaAntiCorruptionLayer;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.gerenciador.aplicacao.GerenciadorDaAplicacao;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component(value = "TelemetriaObserver")
public class TelemetriaObserver implements Observer {
    private final LoggerFacade loggerFacade;
    private final SerializationFacade serializationFacade;
    private final HarpiaAntiCorruptionLayer harpiaAntiCorruptionLayer;
    private final EquipamentoService equipamentoService;
    private final ProcessarMensagemUseCase processarMensagemUseCase;
    private final FallbackGateway fallbackGateway;
    private final GerenciadorDaAplicacao gerenciadorDaAplicacao;

    public TelemetriaObserver(LoggerFacade loggerFacade,
                              SerializationFacade serializationFacade,
                              HarpiaAntiCorruptionLayer harpiaAntiCorruptionLayer,
                              EquipamentoService equipamentoService,
                              ProcessarMensagemUseCase processarMensagemUseCase,
                              FallbackGateway fallbackGateway,
                              GerenciadorDaAplicacao gerenciadorDaAplicacao) {
        this.loggerFacade = loggerFacade;
        this.serializationFacade = serializationFacade;
        this.harpiaAntiCorruptionLayer = harpiaAntiCorruptionLayer;
        this.equipamentoService = equipamentoService;
        this.processarMensagemUseCase = processarMensagemUseCase;
        this.fallbackGateway = fallbackGateway;
        this.gerenciadorDaAplicacao = gerenciadorDaAplicacao;
    }

    @Override
    public void onEvent(Object object) {
        loggerFacade.info("Notificação de mensagem recebida.");
        HarpiaTelemetryMessage harpiaTelemetryMessage;
        try {
            harpiaTelemetryMessage = serializationFacade.fromSnakeCaseBytes((byte[]) object, HarpiaTelemetryMessage.class);
            equipamentoService.atualizarDtUltimaAtualizacao(harpiaTelemetryMessage.getSerial());
        } catch (Exception e) {
            gerenciadorDaAplicacao.tentarDesligarAAplicacao();
            throw new RuntimeException(e);
        }

        Mensagem mensagem;
        try {
            mensagem = harpiaAntiCorruptionLayer.fromHarpiaTelemetryMessage(harpiaTelemetryMessage);
        } catch (Exception e) {
            processarFallback(harpiaTelemetryMessage, e);
            return;
        }

        try {
            processarMensagem(mensagem);
        } catch (Exception e) {
            processarFallback(mensagem, e);
        }
    }

    private void processarMensagem(Mensagem mensagem) {
        processarMensagemUseCase.execute(mensagem);
        loggerFacade.info("Mensagem processada com sucesso.");
    }

    private void processarFallback(Object object, Exception e) {
        try {
            loggerFacade.warn("Erro ao processar mensagem.");
            enviarRequestCallback(object, e);
        } catch (Exception ex) {
            gerenciadorDaAplicacao.tentarDesligarAAplicacao();
            throw new RuntimeException(ex);
        }
    }

    private void enviarRequestCallback(Object object, Exception e) {
        loggerFacade.info("Iniciando fallback");
        fallbackGateway.enviarFallback(new FallbackDto("Microsserviço de telemetria",
                LocalDateTime.now(),
                e.getMessage(),
                e.getCause(),
                object)
        );
        loggerFacade.info("Fallback enviado com sucesso.");
    }
}
