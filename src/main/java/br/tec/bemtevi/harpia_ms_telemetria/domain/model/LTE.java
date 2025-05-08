package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

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
    private LocalDateTime dtEvento;
    private String idEquipamento;
    private String idInstituicao;
    private LocalDateTime dtCriacao;

    public LTE(String cdLTE,
               String nmLTE,
               Double vlSignalStrength,
               String nmCarrier,
               String nmInternetState,
               String nmSimCardState,
               String nmStatus,
               LocalDateTime dtEvento,
               String idEquipamento,
               String idInstituicao) {
        this.cdLTE = cdLTE;
        this.nmLTE = nmLTE;
        this.vlSignalStrength = vlSignalStrength;
        this.nmCarrier = nmCarrier;
        this.nmInternetState = nmInternetState;
        this.nmSimCardState = nmSimCardState;
        this.nmStatus = nmStatus;
        this.dtEvento = dtEvento;
        this.idEquipamento = idEquipamento;
        this.idInstituicao = idInstituicao;
        this.dtCriacao = LocalDateTime.now();
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
        return "LTE Info {" +
                "cdLTE='" + cdLTE + '\'' +
                ", nmLTE='" + nmLTE + '\'' +
                ", vlSignalStrength=" + vlSignalStrength +
                ", nmCarrier='" + nmCarrier + '\'' +
                ", nmInternetState='" + nmInternetState + '\'' +
                ", nmSimCardState='" + nmSimCardState + '\'' +
                ", nmStatus='" + nmStatus + '\'' +
                ", dtEvento=" + dtEvento +
                ", idEquipamento='" + idEquipamento + '\'' +
                ", idInstituicao='" + idInstituicao + '\'' +
                ", dtCriacao=" + dtCriacao +
                '}';
    }
}
