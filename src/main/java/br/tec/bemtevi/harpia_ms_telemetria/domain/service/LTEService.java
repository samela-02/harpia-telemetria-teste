package br.tec.bemtevi.harpia_ms_telemetria.domain.service;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.LTE;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.LTERepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class LTEService implements Observer {
    private final LTERepository lteRepository;

    public LTEService(LTERepository lteRepository) {
        this.lteRepository = lteRepository;
    }

    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void onEvent(Object object) {
        List<LTE> lteList = (List<LTE>) object;
        lteRepository.saveAll(lteList);
    }
}
