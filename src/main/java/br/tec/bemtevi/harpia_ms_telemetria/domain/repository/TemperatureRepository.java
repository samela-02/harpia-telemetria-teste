package br.tec.bemtevi.harpia_ms_telemetria.domain.repository;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.Temperature;

import java.util.List;

public interface TemperatureRepository {
    void saveAll(List<Temperature> temperatureList);
}
