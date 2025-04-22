package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

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

    public void associarInstituicao(Instituicao instituicao) {
        for (GPS gps : this.gps)
            gps.setInstituicao(instituicao);
        for (LTE lte : this.lte)
            lte.setInstituicao(instituicao);
        for (Temperature temperature : this.temperature)
            temperature.setInstituicao(instituicao);
    }

    public void associarEquipamento(Equipamento equipamento) {
        for (GPS gps : this.gps)
            gps.setEquipamento(equipamento);
        for (LTE lte : this.lte)
            lte.setEquipamento(equipamento);
        for (Temperature temperature : this.temperature)
            temperature.setEquipamento(equipamento);
    }
}
