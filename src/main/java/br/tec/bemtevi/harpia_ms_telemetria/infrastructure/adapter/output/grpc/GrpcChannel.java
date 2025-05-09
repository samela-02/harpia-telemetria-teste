package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc;

import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.LoggerFacade;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class GrpcChannel {
    private final LoggerFacade loggerFacade;
    private final ManagedChannel channel;

    public GrpcChannel(LoggerFacade loggerFacade,
                       @Value("${grpc.server.host}") String grpcServerHost,
                       @Value("${grpc.server.port}") int grpcServerPort) {
        this.loggerFacade = loggerFacade;
        loggerFacade.info(String.format("Criando canal para realizar comunicação gRPC no servidor %s na porta %s.",
                grpcServerHost,
                grpcServerPort));
        channel = ManagedChannelBuilder
                .forAddress(grpcServerHost, grpcServerPort)
                .usePlaintext()
                .build();
        adicionarHookDeShutdown();
    }

    private void adicionarHookDeShutdown() {
        Runtime.getRuntime().addShutdownHook(getHook(channel));
    }

    private Thread getHook(ManagedChannel channel) {
        return new Thread(() -> {
            loggerFacade.info("Desligando canal gRPC com graceful shutdown de, no máximo, 30 segundos.");
            try {
                channel.shutdown().awaitTermination(30, TimeUnit.SECONDS);
                loggerFacade.info("Canal gRPC desligado com sucesso.");
            } catch (InterruptedException e) {
                loggerFacade.error(String.format("Erro ao finalizar o canal gRPC. " +
                        "Mensagem da exception: %s.", e.getMessage()));
                e.printStackTrace(System.err);
            }
        });
    }

    public ManagedChannel getChannel() {
        return channel;
    }
}
