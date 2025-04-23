package br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.gps;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.equipamento.Equipamento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.instituicao.Instituicao;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "arg_gps")
public class GPS {
    @Id
    private String cdGPS;
    private String nmGPS;
    private Double vlLatitude;
    private Double vlLongitude;
    private Double vlTrueCourse;

    @DBRef
    private Equipamento equipamento;

    @DBRef
    private Instituicao instituicao;

    public GPS(String cdGPS,
               String nmGPS,
               Double vlLatitude,
               Double vlLongitude,
               Double vlTrueCourse,
               Equipamento equipamento,
               Instituicao instituicao) {
        this.cdGPS = cdGPS;
        this.nmGPS = nmGPS;
        this.vlLatitude = vlLatitude;
        this.vlLongitude = vlLongitude;
        this.vlTrueCourse = vlTrueCourse;
        this.equipamento = equipamento;
        this.instituicao = instituicao;
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

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }
}
