package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto;

public class HarpiaTelemetryMessage {
    private String serial;
    private HarpiaSensors sensors;

    public HarpiaTelemetryMessage() {
    }

    public HarpiaTelemetryMessage(String serial, HarpiaSensors sensors) {
        this.serial = serial;
        this.sensors = sensors;
    }

    public String getSerial() {
        return serial;
    }

    public HarpiaSensors getSensors() {
        return sensors;
    }
}
