package br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.lte;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.equipamento.Equipamento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.instituicao.Instituicao;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "arg_lte")
public class LTE {
    @Id
    private String cdLTE;
    private String nmLTE;
    private Double vlSignalStrength;
    private String nmCarrier;
    private String nmInternetState;
    private String nmSimCardState;
    private String nmStatus;

    @DBRef
    private Equipamento equipamento;

    @DBRef
    private Instituicao instituicao;

    public LTE(String cdLTE,
               String nmLTE,
               Double vlSignalStrength,
               String nmCarrier,
               String nmInternetState,
               String nmSimCardState,
               String nmStatus,
               Equipamento equipamento,
               Instituicao instituicao) {
        this.cdLTE = cdLTE;
        this.nmLTE = nmLTE;
        this.vlSignalStrength = vlSignalStrength;
        this.nmCarrier = nmCarrier;
        this.nmInternetState = nmInternetState;
        this.nmSimCardState = nmSimCardState;
        this.nmStatus = nmStatus;
        this.equipamento = equipamento;
        this.instituicao = instituicao;
    }

    public String getCdLTE() {
        return cdLTE;
    }

    public String getNmLTE() {
        return nmLTE;
    }

    public Double getVlSignalStrength() {
        return vlSignalStrength;
    }

    public String getNmCarrier() {
        return nmCarrier;
    }

    public String getNmInternetState() {
        return nmInternetState;
    }

    public String getNmSimCardState() {
        return nmSimCardState;
    }

    public String getNmStatus() {
        return nmStatus;
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
