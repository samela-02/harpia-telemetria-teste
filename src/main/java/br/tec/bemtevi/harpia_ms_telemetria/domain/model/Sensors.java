package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

import java.util.List;

public class Sensors {
    private String idInstituicao;
    private List<LTE> lte;
    private List<GPS> gps;
    private List<Temperature> temperature;

    public Sensors(String idInstituicao, List<LTE> lte, List<GPS> gps, List<Temperature> temperature) {
        this.idInstituicao = idInstituicao;
        this.lte = lte;
        this.gps = gps;
        this.temperature = temperature;
    }

    public String getIdInstituicao() {
        return idInstituicao;
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
}
