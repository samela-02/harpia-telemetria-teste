package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.bateria;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Bateria;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.BateriaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BateriaRepositoryImpl implements BateriaRepository {
    private final BateriaMongoRepository bateriaMongoRepository;

    public BateriaRepositoryImpl(BateriaMongoRepository bateriaMongoRepository) {
        this.bateriaMongoRepository = bateriaMongoRepository;
    }

    @Override
    public void saveAll(List<Bateria> bateriaList) {
        bateriaMongoRepository.saveAll(bateriaList);
    }
}
