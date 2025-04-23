package br.tec.bemtevi.harpia_ms_telemetria.domain.model.equipamento;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "arg_equipamento")
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
