package br.tec.bemtevi.harpia_ms_telemetria.domain.repository;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.gps.GPS;

import java.util.List;

public interface GPSRepository {
    void saveAll(List<GPS> gpsList);
}
