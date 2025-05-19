package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc;

import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.LoggerFacade;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class SensorsGrpcResponseStreamObserver implements StreamObserver<VoidGrpc> {
    private final LoggerFacade loggerFacade;

    public SensorsGrpcResponseStreamObserver(LoggerFacade loggerFacade) {
        this.loggerFacade = loggerFacade;
    }

    @Override
    public void onNext(VoidGrpc value) {
        loggerFacade.info("Servidor entregou o sinal que finalizou o processamentoda mensagem.");
    }

    @Override
    public void onError(Throwable t) {
        Status status = Status.fromThrowable(t);
        loggerFacade.error(String.format("Erro ao enviar mensagem. Código de status: %s.", status.toString()));
    }

    @Override
    public void onCompleted() {
        loggerFacade.info("Stream processada com sucesso.");
    }
}
