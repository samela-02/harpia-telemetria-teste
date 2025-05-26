package br.tec.bemtevi.harpia_ms_telemetria.domain.repository;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.LTE;

import java.util.List;

public interface LTERepository {
    void saveAll(List<LTE> lteList);
}
