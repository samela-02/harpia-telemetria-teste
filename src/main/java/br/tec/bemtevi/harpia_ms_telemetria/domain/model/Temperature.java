package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Temperature {
    @Id
    private String cdTemperature;
    private String name;
    private Double value;

    @DBRef
    private Equipamento equipamento;

    public Temperature(String cdTemperature, String name, Double value, Equipamento equipamento) {
        this.cdTemperature = cdTemperature;
        this.name = name;
        this.value = value;
        this.equipamento = equipamento;
    }

    public String getCdTemperature() {
        return cdTemperature;
    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }
}
