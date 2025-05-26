package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia.dispositivo;

public class HarpiaCpu {
    private Double cpu1;
    private Double cpu2;
    private Double cpu3;
    private Double cpu4;
    private Double cpu5;
    private Double cpu6;

    public HarpiaCpu() {
    }

    public HarpiaCpu(Double cpu1, Double cpu2, Double cpu3, Double cpu4, Double cpu5, Double cpu6) {
        this.cpu1 = cpu1;
        this.cpu2 = cpu2;
        this.cpu3 = cpu3;
        this.cpu4 = cpu4;
        this.cpu5 = cpu5;
        this.cpu6 = cpu6;
    }

    public Double getCpu1() {
        return cpu1;
    }

    public void setCpu1(Double cpu1) {
        this.cpu1 = cpu1;
    }

    public Double getCpu2() {
        return cpu2;
    }

    public void setCpu2(Double cpu2) {
        this.cpu2 = cpu2;
    }

    public Double getCpu3() {
        return cpu3;
    }

    public void setCpu3(Double cpu3) {
        this.cpu3 = cpu3;
    }

    public Double getCpu4() {
        return cpu4;
    }

    public void setCpu4(Double cpu4) {
        this.cpu4 = cpu4;
    }

    public Double getCpu5() {
        return cpu5;
    }

    public void setCpu5(Double cpu5) {
        this.cpu5 = cpu5;
    }

    public Double getCpu6() {
        return cpu6;
    }

    public void setCpu6(Double cpu6) {
        this.cpu6 = cpu6;
    }
}
