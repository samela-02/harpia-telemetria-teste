package br.tec.bemtevi.harpia_ms_telemetria.domain.service;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.LTE;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.LTERepository;
import org.springframework.transaction.annotation.Transactional;

public class LTEService implements Observer {
    private final LTERepository lteRepository;

    public LTEService(LTERepository lteRepository) {
        this.lteRepository = lteRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void onEvent(Object object) {
        LTE lte = (LTE) object;
        lteRepository.save(lte);
    }
}
