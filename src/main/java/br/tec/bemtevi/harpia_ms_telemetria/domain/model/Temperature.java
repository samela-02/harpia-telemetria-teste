package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Temperature {
    @Id
    private String cdTemperature;
    private String nmTemperature;
    private Double vlTemperature;

    @DBRef
    private Equipamento equipamento;

    public Temperature(String cdTemperature, String nmTemperature, Double vlTemperature) {
        this.cdTemperature = cdTemperature;
        this.nmTemperature = nmTemperature;
        this.vlTemperature = vlTemperature;
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

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }
}
