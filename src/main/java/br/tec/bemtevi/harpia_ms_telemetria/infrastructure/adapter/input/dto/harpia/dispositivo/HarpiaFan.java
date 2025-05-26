package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia.dispositivo;

public class HarpiaFan {
    private Double pwmfan0;

    public HarpiaFan() {
    }

    public HarpiaFan(Double pwmfan0) {
        this.pwmfan0 = pwmfan0;
    }

    public Double getPwmfan0() {
        return pwmfan0;
    }
}
