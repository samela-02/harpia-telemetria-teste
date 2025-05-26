package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.gateway;

import br.tec.bemtevi.harpia_ms_telemetria.application.mediator.Mediator;
import br.tec.bemtevi.harpia_ms_telemetria.domain.enums.TipoEvento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.gateway.ISendSensorsMsgToCCO;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.Sensors;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc.CcoSensorsGrpcClient;
import org.springframework.stereotype.Component;

@Component
public class GrpcCCOGateway implements Observer, ISendSensorsMsgToCCO {
    private final CcoSensorsGrpcClient ccoSensorsGrpcClient;

    public GrpcCCOGateway(Mediator mediator,
                          CcoSensorsGrpcClient ccoSensorsGrpcClient) {
        mediator.registrar(TipoEvento.SENSORS, this);
        this.ccoSensorsGrpcClient = ccoSensorsGrpcClient;
    }

    @Override
    public void onEvent(Object object) {
        send((Sensors) object);
    }

    @Override
    public void send(Sensors sensors) {
        ccoSensorsGrpcClient.enviarMensagem(sensors);
    }
}
