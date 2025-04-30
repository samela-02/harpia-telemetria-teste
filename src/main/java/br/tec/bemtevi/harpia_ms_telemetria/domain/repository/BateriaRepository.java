package br.tec.bemtevi.harpia_ms_telemetria.domain.repository;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Bateria;

import java.util.List;

public interface BateriaRepository {
    void saveAll(List<Bateria> bateriaList);
}
