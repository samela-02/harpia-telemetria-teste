package br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.temperature;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.equipamento.Equipamento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.instituicao.Instituicao;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "arg_temperature")
public class Temperature {
    @Id
    private String cdTemperature;
    private String nmTemperature;
    private Double vlTemperature;

    @DBRef
    private Equipamento equipamento;

    @DBRef
    private Instituicao instituicao;

    public Temperature(String cdTemperature,
                       String nmTemperature,
                       Double vlTemperature,
                       Equipamento equipamento,
                       Instituicao instituicao) {
        this.cdTemperature = cdTemperature;
        this.nmTemperature = nmTemperature;
        this.vlTemperature = vlTemperature;
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

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }
}
