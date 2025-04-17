package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GPS {
    @Id
    private String cdGPS;
    private String name;
    private Double latitude;
    private Double longitude;
    private Double trueCourse;

    @DBRef
    private Equipamento equipamento;

    public GPS(String cdGPS, String name, Double latitude, Double longitude, Double trueCourse, Equipamento equipamento) {
        this.cdGPS = cdGPS;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.trueCourse = trueCourse;
        this.equipamento = equipamento;
    }

    public String getCdGPS() {
        return cdGPS;
    }

    public String getName() {
        return name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getTrueCourse() {
        return trueCourse;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }
}
