package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia.dispositivo;

public class HarpiaMemory {
    private Double ram;
    private Double swap;
    private Double emc;

    public HarpiaMemory() {
    }

    public HarpiaMemory(Double ram, Double swap, Double emc) {
        this.ram = ram;
        this.swap = swap;
        this.emc = emc;
    }

    public Double getRam() {
        return ram;
    }

    public Double getSwap() {
        return swap;
    }

    public Double getEmc() {
        return emc;
    }
}
