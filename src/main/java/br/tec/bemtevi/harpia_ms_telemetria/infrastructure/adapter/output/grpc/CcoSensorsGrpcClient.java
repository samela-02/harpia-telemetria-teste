package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc;

import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.LoggerFacade;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Sensors;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.mapper.SensorsGrpcMapper;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CcoSensorsGrpcClient {
    private final LoggerFacade loggerFacade;
    private final String grpcCcoHost;
    private final int grpcCcoPort;
    private final SensorsGrpcMapper sensorsGrpcMapper;
    private StreamObserver<SensorsGrpc> sensorsGrpcStreamObserver;

    public CcoSensorsGrpcClient(LoggerFacade loggerFacade,
                                @Value("${grpc-cco.server.host}") String grpcCcoHost,
                                @Value("${grpc-cco.server.port}") int grpcCcoPort,
                                SensorsGrpcMapper sensorsGrpcMapper) {
        this.loggerFacade = loggerFacade;
        this.grpcCcoHost = grpcCcoHost;
        this.grpcCcoPort = grpcCcoPort;
        this.sensorsGrpcMapper = sensorsGrpcMapper;
        criarNovaConexaoGrpc();
    }

    public void enviarMensagem(Sensors sensors) {
        try {
            loggerFacade.info("Iniciando tentativa do envio da mensagem dos sensores via gRPC.");
            loggerFacade.debug(String.format("Dados dos sensores: %s.", sensors.toString()));
            sensorsGrpcStreamObserver.onNext(sensorsGrpcMapper.sensorsToSensorsGrpc(sensors));
        } catch (Exception e) {
            loggerFacade.error(e.getMessage());
        }
    }

    public void criarNovaConexaoGrpc() {
        loggerFacade.info(String.format("Criando canal para realizar comunicação gRPC no servidor %s na porta %s.",
                grpcCcoHost,
                grpcCcoPort));
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(grpcCcoHost, grpcCcoPort)
                .usePlaintext()
                .build();
        sensorsGrpcStreamObserver = SensorsGrpcServiceGrpc
                .newStub(channel)
                .propagarSensores(new SensorsGrpcResponseStreamObserver(loggerFacade));
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                loggerFacade.info("Desligando canal gRPC com graceful shutdown de, no máximo, 10 segundos.");
                sensorsGrpcStreamObserver.onCompleted();
                channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
                loggerFacade.info("Canal gRPC desligado com sucesso.");
            } catch (InterruptedException e) {
                loggerFacade.error(String.format("Erro ao finalizar o canal gRPC. " +
                        "Mensagem da exception: %s.", e.getMessage()));
                e.printStackTrace(System.err);
            }
        }));
    }
}
