package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia.dispositivo;

public class HarpiaGpu {
    private Double gpuUsage;

    public HarpiaGpu() {
    }

    public HarpiaGpu(Double gpuUsage) {
        this.gpuUsage = gpuUsage;
    }

    public Double getGpuUsage() {
        return gpuUsage;
    }
}
