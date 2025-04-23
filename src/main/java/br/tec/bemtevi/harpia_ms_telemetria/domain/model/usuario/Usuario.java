package br.tec.bemtevi.harpia_ms_telemetria.domain.model.usuario;

public class Usuario {
    private Long cdUsuario;
    private String nmUsuario;
    private String idInstituicao;
    private String role;

    public Usuario() {
    }

    public Usuario(Long cdUsuario, String nmUsuario, String idInstituicao, String role) {
        this.cdUsuario = cdUsuario;
        this.nmUsuario = nmUsuario;
        this.idInstituicao = idInstituicao;
        this.role = role;
    }

    public Long getCdUsuario() {
        return cdUsuario;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public String getIdInstituicao() {
        return idInstituicao;
    }

    public String getRole() {
        return role;
    }
}
