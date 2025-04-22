package br.tec.bemtevi.harpia_ms_telemetria.domain.service;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPS;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.GPSRepository;
import org.springframework.transaction.annotation.Transactional;

public class GPSService implements Observer {
    private final GPSRepository gpsRepository;

    public GPSService(GPSRepository gpsRepository) {
        this.gpsRepository = gpsRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void onEvent(Object object) {
        GPS gps = (GPS) object;
        gpsRepository.save(gps);
    }
}
