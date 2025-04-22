package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto;

public class HarpiaTemperature {
    private String id;
    private String name;
    private Double value;

    public HarpiaTemperature() {
    }

    public HarpiaTemperature(String id, String name, Double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }
}
