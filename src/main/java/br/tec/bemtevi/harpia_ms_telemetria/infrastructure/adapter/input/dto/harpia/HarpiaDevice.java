package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia;

import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia.dispositivo.*;

public class HarpiaDevice {
    private HarpiaTime time;
    private HarpiaCpu cpu;
    private HarpiaMemory memory;
    private HarpiaGpu gpu;
    private HarpiaDisk disk;
    private HarpiaTemperaturaDispositivo temperature;
    private HarpiaFan fan;
    private HarpiaPower power;

    public HarpiaDevice() {
    }

    public HarpiaDevice(HarpiaTime time,
                        HarpiaCpu cpu,
                        HarpiaMemory memory,
                        HarpiaGpu gpu,
                        HarpiaDisk disk,
                        HarpiaTemperaturaDispositivo temperature,
                        HarpiaFan fan,
                        HarpiaPower power) {
        this.time = time;
        this.cpu = cpu;
        this.memory = memory;
        this.gpu = gpu;
        this.disk = disk;
        this.temperature = temperature;
        this.fan = fan;
        this.power = power;
    }

    public HarpiaTime getTime() {
        return time;
    }

    public HarpiaCpu getCpu() {
        return cpu;
    }

    public HarpiaMemory getMemory() {
        return memory;
    }

    public HarpiaGpu getGpu() {
        return gpu;
    }

    public HarpiaDisk getDisk() {
        return disk;
    }

    public HarpiaTemperaturaDispositivo getTemperature() {
        return temperature;
    }

    public HarpiaFan getFan() {
        return fan;
    }

    public HarpiaPower getPower() {
        return power;
    }
}
