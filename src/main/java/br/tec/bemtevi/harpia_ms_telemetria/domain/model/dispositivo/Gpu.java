package br.tec.bemtevi.harpia_ms_telemetria.domain.model.dispositivo;

public class Gpu {
    private Double vlGpuUsage;

    public Gpu() {
    }

    public Gpu(Double vlGpuUsage) {
        this.vlGpuUsage = vlGpuUsage;
    }

    public Double getVlGpuUsage() {
        return vlGpuUsage;
    }
}
