package br.tec.bemtevi.harpia_ms_telemetria.domain.model.dispositivo;

public class Fan {
    private Double vlPwmfan0;

    public Fan() {
    }

    public Fan(Double vlPwmfan0) {
        this.vlPwmfan0 = vlPwmfan0;
    }

    public Double getVlPwmfan0() {
        return vlPwmfan0;
    }
}
