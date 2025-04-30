package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia;

import java.time.LocalDateTime;

public class HarpiaTemperature {
    private String id;
    private String name;
    private LocalDateTime timestamp;
    private Double temperature;

    public HarpiaTemperature() {
    }

    public HarpiaTemperature(String id, String name, LocalDateTime timestamp, Double temperature) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
        this.temperature = temperature;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Double getTemperature() {
        return temperature;
    }
}
