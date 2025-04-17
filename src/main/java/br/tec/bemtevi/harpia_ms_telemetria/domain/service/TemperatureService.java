package br.tec.bemtevi.harpia_ms_telemetria.domain.service;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Temperature;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.TemperatureObserver;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.TemperatureRepository;
import org.springframework.stereotype.Service;

@Service
public class TemperatureService implements TemperatureObserver {
    private final TemperatureRepository temperatureRepository;

    public TemperatureService(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    @Override
    public void onEvent(Temperature temperature) {
        temperatureRepository.save(temperature);
    }
}
