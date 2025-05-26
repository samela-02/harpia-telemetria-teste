package br.tec.bemtevi.harpia_ms_telemetria.domain.model.dispositivo;

public class Dispositivo {
    private Tempo tempo;
    private Cpu cpu;
    private Memoria memoria;
    private Gpu gpu;
    private Disco disco;
    private TemperaturaDispositivo temperatura;
    private Fan fan;
    private Power power;

    public Dispositivo() {
    }

    public Dispositivo(Tempo tempo,
                       Cpu cpu,
                       Memoria memoria,
                       Gpu gpu,
                       Disco disco,
                       TemperaturaDispositivo temperatura,
                       Fan fan,
                       Power power) {
        this.tempo = tempo;
        this.cpu = cpu;
        this.memoria = memoria;
        this.gpu = gpu;
        this.disco = disco;
        this.temperatura = temperatura;
        this.fan = fan;
        this.power = power;
    }

    public Tempo getTempo() {
        return tempo;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public Memoria getMemoria() {
        return memoria;
    }

    public Gpu getGpu() {
        return gpu;
    }

    public Disco getDisco() {
        return disco;
    }

    public TemperaturaDispositivo getTemperatura() {
        return temperatura;
    }

    public Fan getFan() {
        return fan;
    }

    public Power getPower() {
        return power;
    }
}
