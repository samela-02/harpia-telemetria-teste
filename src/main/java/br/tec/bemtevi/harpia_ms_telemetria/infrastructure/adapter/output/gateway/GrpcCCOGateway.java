package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.gateway;

import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.LoggerFacade;
import br.tec.bemtevi.harpia_ms_telemetria.domain.gateway.ISendSensorsMsgToCCO;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Sensors;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc.SensorsGrpc;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc.SensorsGrpcResponseStreamObserver;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc.SensorsGrpcServiceGrpc;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.mapper.SensorsGrpcMapper;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GrpcCCOGateway implements ISendSensorsMsgToCCO {
    private final LoggerFacade loggerFacade;
    private final SensorsGrpcMapper sensorsGrpcMapper;
    private final String grpcServerHost;
    private final int grpcServerPort;
    private final SensorsGrpcServiceGrpc.SensorsGrpcServiceStub asyncStub;

    public GrpcCCOGateway(LoggerFacade loggerFacade,
                          SensorsGrpcMapper sensorsGrpcMapper,
                          @Value("${grpc.server.host}") String grpcServerHost,
                          @Value("${grpc.server.port}") int grpcServerPort) {
        this.loggerFacade = loggerFacade;
        this.sensorsGrpcMapper = sensorsGrpcMapper;
        this.grpcServerHost = grpcServerHost;
        this.grpcServerPort = grpcServerPort;
        asyncStub = criarStubAsync();
    }

    private SensorsGrpcServiceGrpc.SensorsGrpcServiceStub criarStubAsync() {
        loggerFacade.info(String.format("Criando stub que irá enviar requests gRPC ao servidor %s na porta %s.",
                grpcServerHost,
                grpcServerPort));
        ManagedChannelBuilder<?> managedChannelBuilder = ManagedChannelBuilder
                .forAddress(grpcServerHost, grpcServerPort)
                .usePlaintext();
        ManagedChannel channel = managedChannelBuilder.build();
        return SensorsGrpcServiceGrpc.newStub(channel);
    }

    @Override
    public void send(Sensors sensors) {
        loggerFacade.info("Enviando mensagem, que contém informações de telemetria, ao CCO via gRPC.");
        loggerFacade.debug(String.format("Dados dos sensores: %s", sensors.toString()));
        SensorsGrpc sensorsGrpcRequest = sensorsGrpcMapper.sensorsToSensorsGrpc(sensors);
        StreamObserver<SensorsGrpc> sensorsGrpcRequestStreamObserver = asyncStub
                .propagarSensores(new SensorsGrpcResponseStreamObserver(loggerFacade));
        sensorsGrpcRequestStreamObserver.onNext(sensorsGrpcRequest);
        loggerFacade.info("Mensagem enviada com sucesso.");
    }
}
