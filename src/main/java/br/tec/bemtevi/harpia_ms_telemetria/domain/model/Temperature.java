package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "arg_temperature")
public class Temperature {
    @Id
    private String cdTemperature;
    private String nmTemperature;
    private Double vlTemperature;
    private LocalDateTime dtCriacao;

    @DBRef
    private Equipamento equipamento;

    @DBRef
    private Instituicao instituicao;

    public Temperature(String cdTemperature,
                       String nmTemperature,
                       Double vlTemperature,
                       LocalDateTime dtCriacao,
                       Equipamento equipamento,
                       Instituicao instituicao) {
        this.cdTemperature = cdTemperature;
        this.nmTemperature = nmTemperature;
        this.vlTemperature = vlTemperature;
        this.dtCriacao = dtCriacao;
        this.equipamento = equipamento;
        this.instituicao = instituicao;
    }

    public String getCdTemperature() {
        return cdTemperature;
    }

    public String getNmTemperature() {
        return nmTemperature;
    }

    public Double getVlTemperature() {
        return vlTemperature;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public LocalDateTime getDtCriacao() {
        return dtCriacao;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }
}
