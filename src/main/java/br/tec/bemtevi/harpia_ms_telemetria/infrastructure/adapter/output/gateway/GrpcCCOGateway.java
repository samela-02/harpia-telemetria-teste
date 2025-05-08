package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.gateway;

import br.tec.bemtevi.harpia_ms_telemetria.domain.gateway.ISendSensorsMsgToCCO;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Sensors;
import org.springframework.stereotype.Component;

@Component
public class GrpcCCOGateway implements ISendSensorsMsgToCCO {
    @Override
    public void send(Sensors sensors) {
    }
}
