package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.gateway;

import br.tec.bemtevi.harpia_ms_telemetria.application.mediator.Mediator;
import br.tec.bemtevi.harpia_ms_telemetria.domain.enums.TipoEvento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.LoggerFacade;
import br.tec.bemtevi.harpia_ms_telemetria.domain.gateway.ISendSensorsMsgToCCO;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Sensors;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc.GrpcChannel;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc.SensorsGrpc;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc.SensorsGrpcResponseStreamObserver;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc.SensorsGrpcServiceGrpc;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.mapper.SensorsGrpcMapper;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;

@Component
public class GrpcCCOGateway implements Observer, ISendSensorsMsgToCCO {
    private final LoggerFacade loggerFacade;
    private final SensorsGrpcMapper sensorsGrpcMapper;
    private final StreamObserver<SensorsGrpc> sensorsGrpcRequestStreamObserver;

    public GrpcCCOGateway(Mediator mediator,
                          LoggerFacade loggerFacade,
                          SensorsGrpcMapper sensorsGrpcMapper,
                          GrpcChannel grpcChannel) {
        mediator.registrar(TipoEvento.SENSORS, this);
        this.loggerFacade = loggerFacade;
        this.sensorsGrpcMapper = sensorsGrpcMapper;
        sensorsGrpcRequestStreamObserver = SensorsGrpcServiceGrpc
                .newStub(grpcChannel.getChannel())
                .propagarSensores(new SensorsGrpcResponseStreamObserver(loggerFacade));
        grpcChannel.registrarShutdown(sensorsGrpcRequestStreamObserver);
    }

    @Override
    public void onEvent(Object object) {
        send((Sensors) object);
    }

    @Override
    public void send(Sensors sensors) {
        loggerFacade.info("Enviando mensagem que contém informações de telemetria ao CCO via gRPC.");
        loggerFacade.debug(String.format("Dados dos sensores: %s", sensors.toString()));
        sensorsGrpcRequestStreamObserver.onNext(sensorsGrpcMapper.sensorsToSensorsGrpc(sensors));
        loggerFacade.info("Mensagem enviada com sucesso.");
    }
}
