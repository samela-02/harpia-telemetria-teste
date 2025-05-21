package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "arg_equipamento")
public class Equipamento {
    @Id
    @Column(name = "cd_equipamento", insertable = false, updatable = false)
    private Long cdEquipamento;

    @Column(name = "cd_instituicao")
    private Long cdInstituicao;

    @Column(name = "cd_tipo_equipamento")
    private Long cdTipoEquipamento;

    @Column(name = "id_equipamento")
    private String idEquipamento;

    @Column(name = "nm_equipamento")
    private String nmEquipamento;

    @Column(name = "nr_serie")
    private String nrSerie;

    @Column(name = "lg_ativo")
    private Integer lgAtivo;

    @Column(name = "dt_delecao")
    private LocalDateTime dtDelecao;

    @Column(name = "dt_ultima_comunicacao")
    private LocalDateTime dtUltimaComunicacao;

    public Equipamento() {
    }

    public Equipamento(Long cdEquipamento,
                       Long cdInstituicao,
                       Long cdTipoEquipamento,
                       String idEquipamento,
                       String nmEquipamento,
                       String nrSerie,
                       Integer lgAtivo,
                       LocalDateTime dtDelecao,
                       LocalDateTime dtUltimaComunicacao) {
        this.cdEquipamento = cdEquipamento;
        this.cdInstituicao = cdInstituicao;
        this.cdTipoEquipamento = cdTipoEquipamento;
        this.idEquipamento = idEquipamento;
        this.nmEquipamento = nmEquipamento;
        this.nrSerie = nrSerie;
        this.lgAtivo = lgAtivo;
        this.dtDelecao = dtDelecao;
        this.dtUltimaComunicacao = dtUltimaComunicacao;
    }

    public Long getCdEquipamento() {
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

    public LocalDateTime getDtUltimaComunicacao() {
        return dtUltimaComunicacao;
    }
}
