package br.tec.bemtevi.harpia_ms_telemetria.domain.model.dispositivo;

public class Disco {
    private Double vlTotal;
    private Double vlUsed;
    private Double vlFree;
    private Double vlPercent;

    public Disco() {
    }

    public Disco(Double vlTotal, Double vlUsed, Double vlFree, Double vlPercent) {
        this.vlTotal = vlTotal;
        this.vlUsed = vlUsed;
        this.vlFree = vlFree;
        this.vlPercent = vlPercent;
    }

    public Double getVlTotal() {
        return vlTotal;
    }

    public Double getVlUsed() {
        return vlUsed;
    }

    public Double getVlFree() {
        return vlFree;
    }

    public Double getVlPercent() {
        return vlPercent;
    }
}
