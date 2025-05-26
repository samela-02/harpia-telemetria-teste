package br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "arg_temperature")
public class Temperature {
    @Id
    private String cdTemperature;
    private String nmTemperature;
    private Double vlTemperature;
    private LocalDateTime dtEvento;
    private String idEquipamento;
    private String idInstituicao;
    private LocalDateTime dtCriacao;

    public Temperature(String cdTemperature,
                       String nmTemperature,
                       Double vlTemperature,
                       LocalDateTime dtEvento,
                       String idEquipamento,
                       String idInstituicao) {
        this.cdTemperature = cdTemperature;
        this.nmTemperature = nmTemperature;
        this.vlTemperature = vlTemperature;
        this.dtEvento = dtEvento;
        this.idEquipamento = idEquipamento;
        this.idInstituicao = idInstituicao;
        this.dtCriacao = LocalDateTime.now();
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

    public LocalDateTime getDtEvento() {
        return dtEvento;
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
        return "Temperature Info {" +
                "cdTemperature='" + cdTemperature + '\'' +
                ", nmTemperature='" + nmTemperature + '\'' +
                ", vlTemperature=" + vlTemperature +
                ", dtEvento=" + (dtEvento != null ? dtEvento.toString() : "null") +
                ", idEquipamento='" + idEquipamento + '\'' +
                ", idInstituicao='" + idInstituicao + '\'' +
                ", dtCriacao=" + (dtCriacao != null ? dtCriacao.toString() : "null") +
                '}';
    }
}
