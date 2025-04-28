package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "arg_equipamento")
public class Equipamento {
    @Id
    private ObjectId cdEquipamento;
    private Long cdInstituicao;
    private Long cdTipoEquipamento;
    private String idEquipamento;
    private String nmEquipamento;
    private String nrSerie;
    private Integer lgAtivo;
    private LocalDateTime dtDelecao;

    private Equipamento() {
    }

    public Equipamento(ObjectId cdEquipamento,
                       Long cdInstituicao,
                       Long cdTipoEquipamento,
                       String idEquipamento,
                       String nmEquipamento,
                       String nrSerie,
                       Integer lgAtivo,
                       LocalDateTime dtDelecao) {
        this.cdEquipamento = cdEquipamento;
        this.cdInstituicao = cdInstituicao;
        this.cdTipoEquipamento = cdTipoEquipamento;
        this.idEquipamento = idEquipamento;
        this.nmEquipamento = nmEquipamento;
        this.nrSerie = nrSerie;
        this.lgAtivo = lgAtivo;
        this.dtDelecao = dtDelecao;
    }

    public ObjectId getCdEquipamento() {
        return cdEquipamento;
    }

    public Long getCdInstituicao() {
        return cdInstituicao;
    }

    public Long getCdTipoEquipamento() {
        return cdTipoEquipamento;
    }

    public String getIdEquipamento() {
        return idEquipamento;
    }

    public String getNmEquipamento() {
        return nmEquipamento;
    }

    public String getNrSerie() {
        return nrSerie;
    }

    public Integer getLgAtivo() {
        return lgAtivo;
    }

    public LocalDateTime getDtDelecao() {
        return dtDelecao;
    }
}
