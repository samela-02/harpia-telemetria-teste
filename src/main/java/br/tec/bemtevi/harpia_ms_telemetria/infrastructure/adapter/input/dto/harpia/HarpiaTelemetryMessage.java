package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia;

public class HarpiaTelemetryMessage {
    private String serial;
    private String institutionId;
    private HarpiaDevice device;
    private HarpiaSensors sensors;

    public HarpiaTelemetryMessage() {
    }

    public HarpiaTelemetryMessage(String serial, String institutionId, HarpiaDevice device, HarpiaSensors sensors) {
        this.serial = serial;
        this.institutionId = institutionId;
        this.device = device;
        this.sensors = sensors;
    }

    public String getSerial() {
        return serial;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public HarpiaDevice getDevice() {
        return device;
    }

    public HarpiaSensors getSensors() {
        return sensors;
    }
}
