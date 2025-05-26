package br.tec.bemtevi.harpia_ms_telemetria.domain.model.dispositivo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "arg_dispositivo")
public class Dispositivo {
    @Id
    private String id;
    private String idInstituicao;
    private String idEquipamento;
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

    public Dispositivo(String id,
                       String idInstituicao,
                       String idEquipamento,
                       Tempo tempo,
                       Cpu cpu,
                       Memoria memoria,
                       Gpu gpu,
                       Disco disco,
                       TemperaturaDispositivo temperatura,
                       Fan fan,
                       Power power) {
        this.id = id;
        this.idInstituicao = idInstituicao;
        this.idEquipamento = idEquipamento;
        this.tempo = tempo;
        this.cpu = cpu;
        this.memoria = memoria;
        this.gpu = gpu;
        this.disco = disco;
        this.temperatura = temperatura;
        this.fan = fan;
        this.power = power;
    }

    public String getId() {
        return id;
    }

    public String getIdInstituicao() {
        return idInstituicao;
    }

    public String getIdEquipamento() {
        return idEquipamento;
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
