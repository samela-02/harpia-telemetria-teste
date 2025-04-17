package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LTE {
    @Id
    private String cdLTE;
    private String name;
    private Double signalStrength;
    private String carrier;
    private String internetState;
    private String simCardState;
    private String status;

    @DBRef
    private Equipamento equipamento;

    public LTE(String cdLTE,
               String name,
               Double signalStrength,
               String carrier,
               String internetState,
               String simCardState,
               String status,
               Equipamento equipamento) {
        this.cdLTE = cdLTE;
        this.name = name;
        this.signalStrength = signalStrength;
        this.carrier = carrier;
        this.internetState = internetState;
        this.simCardState = simCardState;
        this.status = status;
        this.equipamento = equipamento;
    }

    public String getCdLTE() {
        return cdLTE;
    }

    public String getName() {
        return name;
    }

    public Double getSignalStrength() {
        return signalStrength;
    }

    public String getCarrier() {
        return carrier;
    }

    public String getInternetState() {
        return internetState;
    }

    public String getSimCardState() {
        return simCardState;
    }

    public String getStatus() {
        return status;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }
}
