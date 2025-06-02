package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc;

import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.LoggerFacade;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.interceptor.grpc.GrpcClientInterceptor;
import io.grpc.*;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class CcoGrpcChannel {
    private final LoggerFacade loggerFacade;
    private final GrpcClientInterceptor grpcClientInterceptor;
    private final String grpcCcoHost;
    private final int grpcCcoPort;
    private Set<StreamObserver<?>> observers;

    private ManagedChannel managedChannel;

    public CcoGrpcChannel(LoggerFacade loggerFacade,
                          GrpcClientInterceptor grpcClientInterceptor,
                          @Value("${grpc-cco.server.host}") String grpcCcoHost,
                          @Value("${grpc-cco.server.port}") int grpcCcoPort) {
        this.loggerFacade = loggerFacade;
        this.grpcClientInterceptor = grpcClientInterceptor;
        this.grpcCcoHost = grpcCcoHost;
        this.grpcCcoPort = grpcCcoPort;
        this.observers = new HashSet<>();
        criarNovoCanal();
    }

    private void criarNovoCanal() {
        loggerFacade.info(String.format("Criando canal para realizar comunicação gRPC no servidor %s na porta %s.",
                grpcCcoHost,
                grpcCcoPort));
        managedChannel = ManagedChannelBuilder
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
                managedChannel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
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

    public ConnectivityState getChannelState() {
        return managedChannel.getState(true);
    }

    public Channel getChannel() {
        return ClientInterceptors.intercept(managedChannel, grpcClientInterceptor);
    }

    public boolean isProcessavel() {
        return managedChannel.getState(true).equals(ConnectivityState.READY);
    }
}
