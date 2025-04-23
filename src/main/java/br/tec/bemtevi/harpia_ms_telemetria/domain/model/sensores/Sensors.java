package br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.gps.GPS;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.lte.LTE;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.temperature.Temperature;

import java.util.List;

public class Sensors {
    private String idInstituicao;
    private String idEquipamento;
    private List<LTE> lte;
    private List<GPS> gps;
    private List<Temperature> temperature;

    public Sensors(String idInstituicao, String idEquipamento, List<LTE> lte, List<GPS> gps, List<Temperature> temperature) {
        this.idInstituicao = idInstituicao;
        this.idEquipamento = idEquipamento;
        this.lte = lte;
        this.gps = gps;
        this.temperature = temperature;
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
}
