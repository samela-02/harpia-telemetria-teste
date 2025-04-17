package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.lte;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.LTE;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.LTERepository;
import org.springframework.stereotype.Component;

@Component
public class LTERepositoryImpl implements LTERepository {
    private final LTERepositoryMongo lteRepositoryMongo;

    public LTERepositoryImpl(LTERepositoryMongo lteRepositoryMongo) {
        this.lteRepositoryMongo = lteRepositoryMongo;
    }

    @Override
    public void save(LTE lte) {
        lteRepositoryMongo.save(lte);
    }
}
