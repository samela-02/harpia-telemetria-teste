package br.tec.bemtevi.harpia_ms_telemetria.domain.model.dispositivo;

public class Memoria {
    private Double vlRam;
    private Double vlSwap;
    private Double vlEmc;

    public Memoria() {
    }

    public Memoria(Double vlRam, Double vlSwap, Double vlEmc) {
        this.vlRam = vlRam;
        this.vlSwap = vlSwap;
        this.vlEmc = vlEmc;
    }

    public Double getVlRam() {
        return vlRam;
    }

    public Double getVlSwap() {
        return vlSwap;
    }

    public Double getVlEmc() {
        return vlEmc;
    }
}
