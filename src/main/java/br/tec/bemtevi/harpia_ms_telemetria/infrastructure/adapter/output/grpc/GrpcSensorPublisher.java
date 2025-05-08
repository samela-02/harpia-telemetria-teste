package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc;

import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.LoggerFacade;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPSSSEResponse;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPSTracker;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;

@Component
public class GrpcSensorPublisher {
    private final LoggerFacade loggerFacade;
    private final String grpcServerHost;
    private final int grpcServerPort;
    private final GpsGrpcServiceGrpc.GpsGrpcServiceStub asyncStub;

    public GrpcSensorPublisher(LoggerFacade loggerFacade,
                               @Value("${grpc.server.host}") String grpcServerHost,
                               @Value("${grpc.server.port}") int grpcServerPort) {
        this.loggerFacade = loggerFacade;
        this.grpcServerHost = grpcServerHost;
        this.grpcServerPort = grpcServerPort;
        asyncStub = criarStub();
    }

    private GpsGrpcServiceGrpc.GpsGrpcServiceStub criarStub() {
        loggerFacade.info(String.format("Criando stub que irá enviar requests gRPC ao servidor %s na porta %s.",
                grpcServerHost,
                grpcServerPort));
        ManagedChannelBuilder<?> managedChannelBuilder = ManagedChannelBuilder
                .forAddress(grpcServerHost, grpcServerPort)
                .usePlaintext();
        ManagedChannel channel = managedChannelBuilder.build();
        return GpsGrpcServiceGrpc.newStub(channel);
    }

    public void enviarEvento(GPSTracker gpsTracker) {
        loggerFacade.info("Enviando evento via gRPC.");
        StreamObserver<GpsGrpc.GpsTracker> streamObserver =
                asyncStub.propagarGPS(new GpsGrpcStreamObserver(loggerFacade));
        GpsGrpc.GpsTracker request = criarRequest(gpsTracker);
        streamObserver.onNext(request);
        loggerFacade.info("Evento enviado com sucesso.");
    }

    private GpsGrpc.GpsTracker criarRequest(GPSTracker gpsTracker) {
        List<GpsGrpc.Gps> requestSensor = converterGpsSseEmGpsGrpc(gpsTracker.getSensores());
        return getRequest(gpsTracker, requestSensor);
    }

    private List<GpsGrpc.Gps> converterGpsSseEmGpsGrpc(List<GPSSSEResponse> sensores) {
        return sensores
                .stream()
                .map(sensor -> GpsGrpc.Gps
                        .newBuilder()
                        .setNmGPS(sensor.getNmGPS() != null ? sensor.getNmGPS() : "")
                        .setVlLatitude(sensor.getVlLatitude() != null ? sensor.getVlLatitude() : 0)
                        .setVlLongitude(sensor.getVlLongitude() != null ? sensor.getVlLongitude() : 0)
                        .setVlTrueCourse(sensor.getVlTrueCourse() != null ? sensor.getVlTrueCourse() : 0)
                        .setDtEvento(sensor.getDtEvento() != null ? sensor.getDtEvento().toInstant(ZoneOffset.UTC).toEpochMilli() : 0)
                        .setDtCriacao(sensor.getDtCriacao() != null ? sensor.getDtCriacao().toInstant(ZoneOffset.UTC).toEpochMilli() : 0)
                        .build())
                .toList();
    }

    private GpsGrpc.GpsTracker getRequest(GPSTracker gpsTracker, List<GpsGrpc.Gps> requestSensor) {
        return GpsGrpc.GpsTracker
                .newBuilder()
                .setIdInstituicao(gpsTracker.getIdInstituicao())
                .setIdEquipamento(gpsTracker.getIdEquipamento())
                .addAllSensores(requestSensor)
                .build();
    }
}
