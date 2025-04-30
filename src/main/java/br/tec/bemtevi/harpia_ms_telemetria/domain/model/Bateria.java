package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "arg_bateria")
public class Bateria {
    @Id
    private String cdBateria;
    private String idBateria;
    private String nmBateria;
    private LocalDateTime dtCriacao;
    private Double busVoltage;
    private Double loadVoltage;
    private Double currentMA;
    private Double currentMW;
    private String idEquipamento;
    private String idInstituicao;

    public Bateria(String cdBateria,
                   String idBateria,
                   String nmBateria,
                   LocalDateTime dtCriacao,
                   Double busVoltage,
                   Double loadVoltage,
                   Double currentMA,
                   Double currentMW,
                   String idEquipamento,
                   String idInstituicao) {
        this.cdBateria = cdBateria;
        this.idBateria = idBateria;
        this.nmBateria = nmBateria;
        this.dtCriacao = dtCriacao;
        this.busVoltage = busVoltage;
        this.loadVoltage = loadVoltage;
        this.currentMA = currentMA;
        this.currentMW = currentMW;
        this.idEquipamento = idEquipamento;
        this.idInstituicao = idInstituicao;
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

    public LocalDateTime getDtCriacao() {
        return dtCriacao;
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
}
