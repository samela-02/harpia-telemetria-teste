package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia.sensores;

import java.time.LocalDateTime;

public class HarpiaBattery {
    private String id;
    private String name;
    private LocalDateTime timestamp;
    private Double busVoltage;
    private Double loadVoltage;
    private Double current;
    private Double power;

    public HarpiaBattery() {
    }

    public HarpiaBattery(String id,
                         String name,
                         LocalDateTime timestamp,
                         Double busVoltage,
                         Double loadVoltage,
                         Double current,
                         Double power) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
        this.busVoltage = busVoltage;
        this.loadVoltage = loadVoltage;
        this.current = current;
        this.power = power;
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

    public Double getBusVoltage() {
        return busVoltage;
    }

    public Double getLoadVoltage() {
        return loadVoltage;
    }

    public Double getCurrent() {
        return current;
    }

    public Double getPower() {
        return power;
    }
}
