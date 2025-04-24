package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia;

public class HarpiaTelemetryMessage {
    private String serial;
    private String institutionId;
    private HarpiaSensors sensors;

    public HarpiaTelemetryMessage() {
    }

    public HarpiaTelemetryMessage(String serial, String institutionId, HarpiaSensors sensors) {
        this.serial = serial;
        this.institutionId = institutionId;
        this.sensors = sensors;
    }

    public String getSerial() {
        return serial;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public HarpiaSensors getSensors() {
        return sensors;
    }
}
