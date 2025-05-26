package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia.dispositivo;

public class HarpiaTemperaturaDispositivo {
    private Double gpu;
    private Double cpu;
    private Double cv0;
    private Double cv1;
    private Double cv2;
    private Double soc0;
    private Double soc1;
    private Double soc2;
    private Double tj;

    public HarpiaTemperaturaDispositivo() {
    }

    public HarpiaTemperaturaDispositivo(Double gpu,
                                        Double cpu,
                                        Double cv0,
                                        Double cv1,
                                        Double cv2,
                                        Double soc0,
                                        Double soc1,
                                        Double soc2,
                                        Double tj) {
        this.gpu = gpu;
        this.cpu = cpu;
        this.cv0 = cv0;
        this.cv1 = cv1;
        this.cv2 = cv2;
        this.soc0 = soc0;
        this.soc1 = soc1;
        this.soc2 = soc2;
        this.tj = tj;
    }

    public Double getGpu() {
        return gpu;
    }

    public Double getCpu() {
        return cpu;
    }

    public Double getCv0() {
        return cv0;
    }

    public Double getCv1() {
        return cv1;
    }

    public Double getCv2() {
        return cv2;
    }

    public Double getSoc0() {
        return soc0;
    }

    public Double getSoc1() {
        return soc1;
    }

    public Double getSoc2() {
        return soc2;
    }

    public Double getTj() {
        return tj;
    }
}
