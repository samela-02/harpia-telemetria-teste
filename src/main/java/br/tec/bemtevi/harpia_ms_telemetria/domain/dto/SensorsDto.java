package br.tec.bemtevi.harpia_ms_telemetria.domain.dto;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPS;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.LTE;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Temperature;

import java.util.List;

public class SensorsDto {
    private List<LTE> lteList;
    private List<GPS> gpsList;
    private List<Temperature> temperatureList;

    public SensorsDto() {
    }

    public SensorsDto(List<LTE> lteList, List<GPS> gpsList, List<Temperature> temperatureList) {
        this.lteList = lteList;
        this.gpsList = gpsList;
        this.temperatureList = temperatureList;
    }

    public List<LTE> getLteList() {
        return lteList;
    }

    public List<GPS> getGpsList() {
        return gpsList;
    }

    public List<Temperature> getTemperatureList() {
        return temperatureList;
    }
}
