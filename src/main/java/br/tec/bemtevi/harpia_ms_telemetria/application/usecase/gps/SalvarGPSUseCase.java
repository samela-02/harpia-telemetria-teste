package br.tec.bemtevi.harpia_ms_telemetria.application.usecase.gps;

import br.tec.bemtevi.harpia_ms_telemetria.application.mediator.Mediator;
import br.tec.bemtevi.harpia_ms_telemetria.domain.enums.TipoEvento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.GPS;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.GPSRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalvarGPSUseCase implements Observer {
    private static final Logger log = LoggerFactory.getLogger(SalvarGPSUseCase.class);

    private final GPSRepository gpsRepository;

    public SalvarGPSUseCase(GPSRepository gpsRepository, Mediator sensorMediator) {
        this.gpsRepository = gpsRepository;
        sensorMediator.registrar(TipoEvento.GPS, this);
    }

    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void onEvent(Object object) {
        List<GPS> gpsList = (List<GPS>) object;
        gpsRepository.saveAll(gpsList);
        log.info("Dados de gps persistidos com sucesso.");
        for (GPS gps : gpsList)
            log.info("Dados de GPS. nmGps: {}. vlLatitude: {}. vlLongitude: {}. vlTrueCourse: {}.",
                    gps.getNmGPS(),
                    gps.getVlLatitude(),
                    gps.getVlLongitude(),
                    gps.getVlTrueCourse());
    }
}
