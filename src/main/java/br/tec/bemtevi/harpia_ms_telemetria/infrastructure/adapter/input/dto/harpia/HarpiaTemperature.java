package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia;

public class HarpiaTemperature {
    private String id;
    private String name;
    private Double temperature;

    public HarpiaTemperature() {
    }

    public HarpiaTemperature(String id, String name, Double temperature) {
        this.id = id;
        this.name = name;
        this.temperature = temperature;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getTemperature() {
        return temperature;
    }
}
