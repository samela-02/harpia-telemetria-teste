package br.tec.bemtevi.harpia_ms_telemetria.application.usecase.lte;

import br.tec.bemtevi.harpia_ms_telemetria.application.mediator.Mediator;
import br.tec.bemtevi.harpia_ms_telemetria.domain.enums.TipoEvento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.LTE;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.LTERepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalvarLTEUseCase implements Observer {
    private static final Logger log = LoggerFactory.getLogger(SalvarLTEUseCase.class);

    private final LTERepository lteRepository;

    public SalvarLTEUseCase(LTERepository lteRepository, Mediator sensorMediator) {
        this.lteRepository = lteRepository;
        sensorMediator.registrar(TipoEvento.LTE, this);
    }

    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void onEvent(Object object) {
        List<LTE> lteList = (List<LTE>) object;
        lteRepository.saveAll(lteList);
        log.info("Dados de lte persistidos com sucesso.");
        for (LTE lte : lteList)
            log.info("Dados do lte. nmLte: {}. vlSignalStrength: {}. nmCarrier: {}. nmInternetState: {}. nmSimCardState: {}. nmStatus: {}.",
                    lte.getNmLTE(),
                    lte.getVlSignalStrength(),
                    lte.getNmCarrier(),
                    lte.getNmInternetState(),
                    lte.getNmSimCardState(),
                    lte.getNmStatus());
    }
}
