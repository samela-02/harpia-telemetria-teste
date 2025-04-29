package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia;

import java.time.LocalDateTime;

public class HarpiaTelemetryMessage {
    private String serial;
    private String institutionId;
    private LocalDateTime timestamp;
    private HarpiaSensors sensors;

    public HarpiaTelemetryMessage() {
    }

    public HarpiaTelemetryMessage(String serial, String institutionId, LocalDateTime timestamp, HarpiaSensors sensors) {
        this.serial = serial;
        this.institutionId = institutionId;
        this.timestamp = timestamp;
        this.sensors = sensors;
    }

    public String getSerial() {
        return serial;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public HarpiaSensors getSensors() {
        return sensors;
    }
}
