package br.tec.bemtevi.harpia_ms_telemetria.domain.service;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPS;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.GPSObserver;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.GPSRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GPSService implements GPSObserver {
    private final GPSRepository gpsRepository;

    public GPSService(GPSRepository gpsRepository) {
        this.gpsRepository = gpsRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void onEvent(GPS gps) {
        gpsRepository.save(gps);
    }
}
