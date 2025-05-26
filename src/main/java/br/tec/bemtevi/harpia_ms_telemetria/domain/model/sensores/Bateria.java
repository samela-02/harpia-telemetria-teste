package br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "arg_bateria")
public class Bateria {
    @Id
    private String cdBateria;
    private String idBateria;
    private String nmBateria;
    private LocalDateTime dtEvento;
    private Double busVoltage;
    private Double loadVoltage;
    private Double currentMA;
    private Double currentMW;
    private String idEquipamento;
    private String idInstituicao;
    private LocalDateTime dtCriacao;

    public Bateria(String cdBateria,
                   String idBateria,
                   String nmBateria,
                   LocalDateTime dtEvento,
                   Double busVoltage,
                   Double loadVoltage,
                   Double currentMA,
                   Double currentMW,
                   String idEquipamento,
                   String idInstituicao) {
        this.cdBateria = cdBateria;
        this.idBateria = idBateria;
        this.nmBateria = nmBateria;
        this.dtEvento = dtEvento;
        this.busVoltage = busVoltage;
        this.loadVoltage = loadVoltage;
        this.currentMA = currentMA;
        this.currentMW = currentMW;
        this.idEquipamento = idEquipamento;
        this.idInstituicao = idInstituicao;
        this.dtCriacao = LocalDateTime.now();
    }

    public String getCdBateria() {
        return cdBateria;
    }

    public String getIdBateria() {
        return idBateria;
    }

    public String getNmBateria() {
        return nmBateria;
    }

    public LocalDateTime getDtEvento() {
        return dtEvento;
    }

    public Double getBusVoltage() {
        return busVoltage;
    }

    public Double getLoadVoltage() {
        return loadVoltage;
    }

    public Double getCurrentMA() {
        return currentMA;
    }

    public Double getCurrentMW() {
        return currentMW;
    }

    public String getIdEquipamento() {
        return idEquipamento;
    }

    public String getIdInstituicao() {
        return idInstituicao;
    }

    public LocalDateTime getDtCriacao() {
        return dtCriacao;
    }

    @Override
    public String toString() {
        return "Bateria Info {" +
                "cdBateria='" + cdBateria + '\'' +
                ", idBateria='" + idBateria + '\'' +
                ", nmBateria='" + nmBateria + '\'' +
                ", dtEvento=" + (dtEvento != null ? dtEvento.toString() : "null") +
                ", busVoltage=" + busVoltage +
                ", loadVoltage=" + loadVoltage +
                ", currentMA=" + currentMA +
                ", currentMW=" + currentMW +
                ", idEquipamento='" + idEquipamento + '\'' +
                ", idInstituicao='" + idInstituicao + '\'' +
                ", dtCriacao=" + (dtCriacao != null ? dtCriacao.toString() : "null") +
                '}';
    }
}
