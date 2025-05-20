package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc;

import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.LoggerFacade;
import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class CcoGrpcChannel {
    private final LoggerFacade loggerFacade;
    private final String grpcCcoHost;
    private final int grpcCcoPort;
    private Set<StreamObserver<?>> observers;
    private ManagedChannel channel;

    public CcoGrpcChannel(LoggerFacade loggerFacade,
                          @Value("${grpc-cco.server.host}") String grpcCcoHost,
                          @Value("${grpc-cco.server.port}") int grpcCcoPort) {
        this.loggerFacade = loggerFacade;
        this.grpcCcoHost = grpcCcoHost;
        this.grpcCcoPort = grpcCcoPort;
        criarNovoCanal();
    }

    private void criarNovoCanal() {
        observers = new HashSet<>();
        loggerFacade.info(String.format("Criando canal para realizar comunicação gRPC no servidor %s na porta %s.",
                grpcCcoHost,
                grpcCcoPort));
        channel = ManagedChannelBuilder
                .forAddress(grpcCcoHost, grpcCcoPort)
                .usePlaintext()
                .build();
        adicionarHookDeShutdown();
    }

    private void adicionarHookDeShutdown() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                loggerFacade.info("Desligando canal gRPC com graceful shutdown de, no máximo, 10 segundos.");
                observers.forEach(StreamObserver::onCompleted);
                channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
                loggerFacade.info("Canal gRPC desligado com sucesso.");
            } catch (InterruptedException e) {
                loggerFacade.error(String.format("Erro ao finalizar o canal gRPC. " +
                        "Mensagem da exception: %s.", e.getMessage()));
                e.printStackTrace(System.err);
            }
        }));
    }

    public void registrarShutdown(StreamObserver<?> streamObserver) {
        observers.add(streamObserver);
    }

    public ManagedChannel getChannel() {
        return channel;
    }

    public boolean isAptoAEnviarMensagem() {
        return channel.getState(true).equals(ConnectivityState.READY);
    }
}
