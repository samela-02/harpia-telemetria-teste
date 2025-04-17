package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GPS {
    @Id
    private String cdGPS;
    private String nmGPS;
    private Double vlLatitude;
    private Double vlLongitude;
    private Double vlTrueCourse;

    @DBRef
    private Equipamento equipamento;

    public GPS(String cdGPS, String nmGPS, Double vlLatitude, Double vlLongitude, Double vlTrueCourse) {
        this.cdGPS = cdGPS;
        this.nmGPS = nmGPS;
        this.vlLatitude = vlLatitude;
        this.vlLongitude = vlLongitude;
        this.vlTrueCourse = vlTrueCourse;
    }

    public String getCdGPS() {
        return cdGPS;
    }

    public String getNmGPS() {
        return nmGPS;
    }

    public Double getVlLatitude() {
        return vlLatitude;
    }

    public Double getVlLongitude() {
        return vlLongitude;
    }

    public Double getVlTrueCourse() {
        return vlTrueCourse;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void associarEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }
}
