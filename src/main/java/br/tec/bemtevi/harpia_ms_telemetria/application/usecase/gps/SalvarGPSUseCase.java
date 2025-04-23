package br.tec.bemtevi.harpia_ms_telemetria.application.usecase.gps;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.gps.GPS;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.GPSRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalvarGPSUseCase implements Observer {
    private final GPSRepository gpsRepository;

    public SalvarGPSUseCase(GPSRepository gpsRepository) {
        this.gpsRepository = gpsRepository;
    }

    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void onEvent(Object object) {
        List<GPS> gpsList = (List<GPS>) object;
        gpsRepository.saveAll(gpsList);
    }
}
