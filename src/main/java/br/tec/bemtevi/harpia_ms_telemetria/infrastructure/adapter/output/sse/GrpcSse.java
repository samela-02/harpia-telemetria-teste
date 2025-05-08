package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.sse;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPSTracker;
import br.tec.bemtevi.harpia_ms_telemetria.domain.sse.SSE;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc.GrpcSensorPublisher;
import org.springframework.stereotype.Component;

@Component(value = "GrpcSse")
public class GrpcSse implements SSE {
    private final GrpcSensorPublisher grpcSensorPublisher;

    public GrpcSse(GrpcSensorPublisher grpcSensorPublisher) {
        this.grpcSensorPublisher = grpcSensorPublisher;
    }

    @Override
    public void emit(Object object) {
        GPSTracker gpsTracker = (GPSTracker) object;
        grpcSensorPublisher.enviarEvento(gpsTracker);
    }
}
