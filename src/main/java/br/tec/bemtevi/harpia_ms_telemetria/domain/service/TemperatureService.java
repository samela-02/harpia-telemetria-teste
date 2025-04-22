package br.tec.bemtevi.harpia_ms_telemetria.domain.service;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Temperature;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.TemperatureRepository;
import org.springframework.transaction.annotation.Transactional;

public class TemperatureService implements Observer {
    private final TemperatureRepository temperatureRepository;

    public TemperatureService(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void onEvent(Object object) {
        Temperature temperature = (Temperature) object;
        temperatureRepository.save(temperature);
    }
}
