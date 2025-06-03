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

    public TelemetriaObserver(LoggerFacade loggerFacade,
                              SerializationFacade serializationFacade,
                              HarpiaAntiCorruptionLayer harpiaAntiCorruptionLayer,
                              EquipamentoService equipamentoService,
                              ProcessarMensagemUseCase processarMensagemUseCase,
                              FallbackGateway fallbackGateway) {
        this.loggerFacade = loggerFacade;
        this.serializationFacade = serializationFacade;
        this.harpiaAntiCorruptionLayer = harpiaAntiCorruptionLayer;
        this.equipamentoService = equipamentoService;
        this.processarMensagemUseCase = processarMensagemUseCase;
        this.fallbackGateway = fallbackGateway;
    }

    @Override
    public void onEvent(Object object) {
        loggerFacade.info("Notificação de mensagem recebida.");
        Mensagem mensagem;
        try {
            HarpiaTelemetryMessage harpiaTelemetryMessage = converterBytesEmHarpiaTelemetryMessage((byte[]) object);
            atualizarUltimaComunicacaoEquipamento(harpiaTelemetryMessage.getSerial());
            mensagem = converterHarpiaTelemetryMessageEmMensagem(harpiaTelemetryMessage);
        } catch (Exception e) {
            loggerFacade.warn(String.format("Erro ao atualizar a data de última comunicação: %s.", e.getMessage()));
            // TODO chamar shutdown aqui
            throw new RuntimeException(e);
        }

        try {
            processarMensagem(mensagem);
        } catch (Exception e) {
            processarFallback(mensagem, e);
        }
    }

    private Mensagem converterHarpiaTelemetryMessageEmMensagem(HarpiaTelemetryMessage harpiaTelemetryMessage) {
        return harpiaAntiCorruptionLayer.fromHarpiaTelemetryMessage(harpiaTelemetryMessage);
    }

    private HarpiaTelemetryMessage converterBytesEmHarpiaTelemetryMessage(byte[] mensagem) {
        return serializationFacade.fromSnakeCaseBytes(mensagem, HarpiaTelemetryMessage.class);
    }

    private void atualizarUltimaComunicacaoEquipamento(String idEquipamento) {
        equipamentoService.atualizarDtUltimaAtualizacao(idEquipamento);
    }

    private void processarMensagem(Mensagem mensagem) {
        processarMensagemUseCase.execute(mensagem);
        loggerFacade.info("Mensagem processada com sucesso.");
    }

    private void processarFallback(Mensagem mensagem, Exception e) {
        loggerFacade.warn("Erro ao processar mensagem.");
        loggerFacade.info("Iniciando fallback");
        fallbackGateway.enviarFallback(new FallbackDto("Microsserviço de detecção",
                LocalDateTime.now(),
                e.getMessage(),
                e.getCause(),
                mensagem)
        );
        loggerFacade.info("Fallback enviado com sucesso.");
    }
}
