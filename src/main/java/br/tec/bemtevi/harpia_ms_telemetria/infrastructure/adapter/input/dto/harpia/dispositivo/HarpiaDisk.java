package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia.dispositivo;

public class HarpiaDisk {
    private Double total;
    private Double used;
    private Double free;
    private Double percent;

    public HarpiaDisk() {
    }

    public HarpiaDisk(Double total, Double used, Double free, Double percent) {
        this.total = total;
        this.used = used;
        this.free = free;
        this.percent = percent;
    }

    public Double getTotal() {
        return total;
    }

    public Double getUsed() {
        return used;
    }

    public Double getFree() {
        return free;
    }

    public Double getPercent() {
        return percent;
    }
}
