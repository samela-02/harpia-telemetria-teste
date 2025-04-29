package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "arg_instituicao")
public class Instituicao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arg_instituicao_cd_instituicao_seq")
    @SequenceGenerator(name = "arg_instituicao_cd_instituicao_seq", sequenceName = "arg_instituicao_cd_instituicao_seq", allocationSize = 1)
    @Column(name = "cd_instituicao")
    private Long cdInstituicao;

    @Column(name = "cd_cidade")
    private Long cdCidade;

    @Column(name = "id_instituicao")
    private String idInstituicao;

    @Column(name = "nm_instituicao")
    private String nmInstituicao;

    @Column(name = "txt_observacao")
    private String txtObservacao;

    @Column(name = "lg_ativo")
    private int lgAtivo;

    @Column(name = "dt_delecao")
    private LocalDateTime dtDelecao;

    public Instituicao() {
    }

    public Instituicao(Long cdInstituicao,
                       Long cdCidade,
                       String idInstituicao,
                       String nmInstituicao,
                       String txtObservacao,
                       int lgAtivo,
                       LocalDateTime dtDelecao) {
        this.cdInstituicao = cdInstituicao;
        this.cdCidade = cdCidade;
        this.idInstituicao = idInstituicao;
        this.nmInstituicao = nmInstituicao;
        this.txtObservacao = txtObservacao;
        this.lgAtivo = lgAtivo;
        this.dtDelecao = dtDelecao;
    }

    public Long getCdInstituicao() {
        return cdInstituicao;
    }

    public Long getCdCidade() {
        return cdCidade;
    }

    public String getIdInstituicao() {
        return idInstituicao;
    }

    public String getNmInstituicao() {
        return nmInstituicao;
    }

    public String getTxtObservacao() {
        return txtObservacao;
    }

    public int getLgAtivo() {
        return lgAtivo;
    }

    public LocalDateTime getDtDelecao() {
        return dtDelecao;
    }
}
