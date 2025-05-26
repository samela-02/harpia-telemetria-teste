package br.tec.bemtevi.harpia_ms_telemetria.domain.model.dispositivo;

public class Cpu {
    private Double vlCpu1;
    private Double vlCpu2;
    private Double vlCpu3;
    private Double vlCpu4;
    private Double vlCpu5;
    private Double vlCpu6;

    public Cpu() {
    }

    public Cpu(Double vlCpu1, Double vlCpu2, Double vlCpu3, Double vlCpu4, Double vlCpu5, Double vlCpu6) {
        this.vlCpu1 = vlCpu1;
        this.vlCpu2 = vlCpu2;
        this.vlCpu3 = vlCpu3;
        this.vlCpu4 = vlCpu4;
        this.vlCpu5 = vlCpu5;
        this.vlCpu6 = vlCpu6;
    }

    public Double getVlCpu1() {
        return vlCpu1;
    }

    public Double getVlCpu2() {
        return vlCpu2;
    }

    public Double getVlCpu3() {
        return vlCpu3;
    }

    public Double getVlCpu4() {
        return vlCpu4;
    }

    public Double getVlCpu5() {
        return vlCpu5;
    }

    public Double getVlCpu6() {
        return vlCpu6;
    }
}
