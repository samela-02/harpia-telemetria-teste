package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "arg_gps")
public class GPS {
    @Id
    private String cdGPS;
    private String nmGPS;
    private Double vlLatitude;
    private Double vlLongitude;
    private Double vlTrueCourse;
    private LocalDateTime dtEvento;
    private String idEquipamento;
    private String idInstituicao;

    public GPS(String cdGPS,
               String nmGPS,
               Double vlLatitude,
               Double vlLongitude,
               Double vlTrueCourse,
               LocalDateTime dtEvento,
               String idEquipamento,
               String idInstituicao) {
        this.cdGPS = cdGPS;
        this.nmGPS = nmGPS;
        this.vlLatitude = vlLatitude;
        this.vlLongitude = vlLongitude;
        this.vlTrueCourse = vlTrueCourse;
        this.dtEvento = dtEvento;
        this.idEquipamento = idEquipamento;
        this.idInstituicao = idInstituicao;
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

    public LocalDateTime getDtEvento() {
        return dtEvento;
    }

    public String getIdEquipamento() {
        return idEquipamento;
    }

    public String getIdInstituicao() {
        return idInstituicao;
    }
}
