package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

import java.util.List;

public class Sensors {
    private String idInstituicao;
    private String idEquipamento;
    private List<LTE> lte;
    private List<GPS> gps;
    private List<Temperature> temperature;
    private List<Bateria> bateria;

    public Sensors(String idInstituicao,
                   String idEquipamento,
                   List<LTE> lte,
                   List<GPS> gps,
                   List<Temperature> temperature,
                   List<Bateria> bateria) {
        this.idInstituicao = idInstituicao;
        this.idEquipamento = idEquipamento;
        this.lte = lte;
        this.gps = gps;
        this.temperature = temperature;
        this.bateria = bateria;
    }

    public String getIdInstituicao() {
        return idInstituicao;
    }

    public String getIdEquipamento() {
        return idEquipamento;
    }

    public List<LTE> getLte() {
        return lte;
    }

    public List<GPS> getGps() {
        return gps;
    }

    public List<Temperature> getTemperature() {
        return temperature;
    }

    public List<Bateria> getBateria() {
        return bateria;
    }
}
