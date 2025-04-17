package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.temperature;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Temperature;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.TemperatureRepository;
import org.springframework.stereotype.Component;

@Component
public class TemperatureRepositoryImpl implements TemperatureRepository {
    private final TemperatureRepositoryMongo temperatureRepositoryMongo;

    public TemperatureRepositoryImpl(TemperatureRepositoryMongo temperatureRepositoryMongo) {
        this.temperatureRepositoryMongo = temperatureRepositoryMongo;
    }

    @Override
    public void save(Temperature temperature) {
        temperatureRepositoryMongo.save(temperature);
    }
}
