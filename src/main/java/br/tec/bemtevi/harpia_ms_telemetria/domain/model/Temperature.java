package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "arg_temperature")
public class Temperature {
    @Id
    private String cdTemperature;
    private String nmTemperature;
    private Double vlTemperature;
    private LocalDateTime dtCriacao;
    private String idEquipamento;
    private String idInstituicao;

    public Temperature(String cdTemperature,
                       String nmTemperature,
                       Double vlTemperature,
                       LocalDateTime dtCriacao,
                       String idEquipamento,
                       String idInstituicao) {
        this.cdTemperature = cdTemperature;
        this.nmTemperature = nmTemperature;
        this.vlTemperature = vlTemperature;
        this.dtCriacao = dtCriacao;
        this.idEquipamento = idEquipamento;
        this.idInstituicao = idInstituicao;
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

    public LocalDateTime getDtCriacao() {
        return dtCriacao;
    }

    public String getIdEquipamento() {
        return idEquipamento;
    }

    public String getIdInstituicao() {
        return idInstituicao;
    }
}
