package br.tec.bemtevi.harpia_ms_telemetria.domain.model.dispositivo;

public class TemperaturaDispositivo {
    private Double vlGpu;
    private Double vlCpu;
    private Double vlCv0;
    private Double vlCv1;
    private Double vlCv2;
    private Double vlSoc0;
    private Double vlSoc1;
    private Double vlSoc2;
    private Double vlTj;

    public TemperaturaDispositivo() {
    }

    public TemperaturaDispositivo(Double vlGpu,
                                  Double vlCpu,
                                  Double vlCv0,
                                  Double vlCv1,
                                  Double vlCv2,
                                  Double vlSoc0,
                                  Double vlSoc1,
                                  Double vlSoc2,
                                  Double vlTj) {
        this.vlGpu = vlGpu;
        this.vlCpu = vlCpu;
        this.vlCv0 = vlCv0;
        this.vlCv1 = vlCv1;
        this.vlCv2 = vlCv2;
        this.vlSoc0 = vlSoc0;
        this.vlSoc1 = vlSoc1;
        this.vlSoc2 = vlSoc2;
        this.vlTj = vlTj;
    }

    public Double getVlGpu() {
        return vlGpu;
    }

    public Double getVlCpu() {
        return vlCpu;
    }

    public Double getVlCv0() {
        return vlCv0;
    }

    public Double getVlCv1() {
        return vlCv1;
    }

    public Double getVlCv2() {
        return vlCv2;
    }

    public Double getVlSoc0() {
        return vlSoc0;
    }

    public Double getVlSoc1() {
        return vlSoc1;
    }

    public Double getVlSoc2() {
        return vlSoc2;
    }

    public Double getVlTj() {
        return vlTj;
    }
}
