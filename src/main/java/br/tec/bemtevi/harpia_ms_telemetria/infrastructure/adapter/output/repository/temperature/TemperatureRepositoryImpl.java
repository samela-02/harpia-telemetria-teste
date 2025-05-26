package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.temperature;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.Temperature;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.TemperatureRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TemperatureRepositoryImpl implements TemperatureRepository {
    private final TemperatureRepositoryMongo temperatureRepositoryMongo;

    public TemperatureRepositoryImpl(TemperatureRepositoryMongo temperatureRepositoryMongo) {
        this.temperatureRepositoryMongo = temperatureRepositoryMongo;
    }

    @Override
    public void saveAll(List<Temperature> temperatureList) {
        temperatureRepositoryMongo.saveAll(temperatureList);
    }
}
