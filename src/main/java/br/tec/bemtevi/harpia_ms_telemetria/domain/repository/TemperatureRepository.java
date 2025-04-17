package br.tec.bemtevi.harpia_ms_telemetria.domain.repository;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Temperature;

public interface TemperatureRepository {
    void save(Temperature temperature);
}
