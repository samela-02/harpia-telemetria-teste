package br.tec.bemtevi.harpia_ms_telemetria.domain.gateway;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.Sensors;

public interface ISendSensorsMsgToCCO {
    void send(Sensors sensors);
}
