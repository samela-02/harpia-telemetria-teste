package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.bateria;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Bateria;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.BateriaRepository;

import java.util.ArrayList;
import java.util.List;

public class BateriaRepositoryInMemory implements BateriaRepository {
    private final List<Bateria> bateriaList;

    public BateriaRepositoryInMemory() {
        bateriaList = new ArrayList<>();
    }

    @Override
    public void saveAll(List<Bateria> bateriaList) {
        this.bateriaList.addAll(bateriaList);
    }
}