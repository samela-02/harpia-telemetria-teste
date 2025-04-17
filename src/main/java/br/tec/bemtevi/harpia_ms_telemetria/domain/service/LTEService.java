package br.tec.bemtevi.harpia_ms_telemetria.domain.service;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.LTE;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.LTEObserver;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.LTERepository;
import org.springframework.stereotype.Service;

@Service
public class LTEService implements LTEObserver {
    private final LTERepository lteRepository;

    public LTEService(LTERepository lteRepository) {
        this.lteRepository = lteRepository;
    }

    @Override
    public void onEvent(LTE lte) {
        lteRepository.save(lte);
    }
}
