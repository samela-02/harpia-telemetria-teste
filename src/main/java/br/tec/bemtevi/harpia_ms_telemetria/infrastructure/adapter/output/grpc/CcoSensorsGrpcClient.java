package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc;

import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.LoggerFacade;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.Sensors;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.mapper.SensorsGrpcMapper;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;

@Component
public class CcoSensorsGrpcClient {
    private final LoggerFacade loggerFacade;
    private final CcoGrpcChannel ccoGrpcChannel;
    private final SensorsGrpcMapper sensorsGrpcMapper;
    private StreamObserver<SensorsGrpc> sensorsGrpcStreamObserver;

    public CcoSensorsGrpcClient(LoggerFacade loggerFacade,
                                CcoGrpcChannel ccoGrpcChannel,
                                SensorsGrpcMapper sensorsGrpcMapper) {
        this.loggerFacade = loggerFacade;
        this.ccoGrpcChannel = ccoGrpcChannel;
        this.sensorsGrpcMapper = sensorsGrpcMapper;
        criarNovaConexaoGrpc();
    }

    public void enviarMensagem(Sensors sensors) {
        try {
            if (ccoGrpcChannel.isProcessavel()) {
                loggerFacade.info("Iniciando tentativa do envio da mensagem dos sensores via gRPC.");
                loggerFacade.debug(String.format("Dados dos sensores: %s.", sensors.toString()));
                sensorsGrpcStreamObserver.onNext(sensorsGrpcMapper.sensorsToSensorsGrpc(sensors));
            }
        } catch (Exception e) {
            loggerFacade.error(e.getMessage());
        }
    }

    public void criarNovaConexaoGrpc() {
        loggerFacade.info("Abrindo nova stream que vai processar o envio dos comandos.");
        boolean isStreamAberta = false;
        while (!isStreamAberta) {
            if (ccoGrpcChannel.isProcessavel()) {
                sensorsGrpcStreamObserver = SensorsGrpcServiceGrpc
                        .newStub(ccoGrpcChannel.getChannel())
                        .propagarSensores(new SensorsGrpcResponseStreamObserver(loggerFacade, this));
                ccoGrpcChannel.registrarShutdown(sensorsGrpcStreamObserver);
                isStreamAberta = true;
                loggerFacade.info("Stream aberta com sucesso.");
            } else {
                try {
                    loggerFacade.info("O canal não está apto a abrir streams.");
                    loggerFacade.info("Aguardando 15 segundos para tentar novamente.");
                    loggerFacade.debug(String.format("Estado da conexão: %s.", ccoGrpcChannel.getChannelState()));
                    Thread.sleep(15000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException("Erro ao executar o sleep.", e);
                }
            }
        }
    }
}
