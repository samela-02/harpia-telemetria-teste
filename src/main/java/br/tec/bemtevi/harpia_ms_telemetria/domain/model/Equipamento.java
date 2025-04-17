package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Equipamento {
    @Id
    private String idEquipamento;

    private Equipamento() {
    }

    public Equipamento(String idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public String getIdEquipamento() {
        return idEquipamento;
    }
}
