package br.tec.bemtevi.harpia_ms_telemetria.application.usecase.lte;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.lte.LTE;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.LTERepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalvarLTEUseCase implements Observer {
    private final LTERepository lteRepository;

    public SalvarLTEUseCase(LTERepository lteRepository) {
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
