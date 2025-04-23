package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "arg_instituicao")
public class Instituicao {
    @Id
    private String idInstituicao;

    public Instituicao(String idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public String getIdInstituicao() {
        return idInstituicao;
    }
}
