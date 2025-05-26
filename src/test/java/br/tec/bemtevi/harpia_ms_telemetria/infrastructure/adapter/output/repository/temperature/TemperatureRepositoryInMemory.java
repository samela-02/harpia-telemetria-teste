package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.temperature;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.Temperature;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.TemperatureRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TemperatureRepositoryInMemory implements TemperatureRepository {
    private final List<Temperature> temperatureList;

    public TemperatureRepositoryInMemory() {
        temperatureList = new ArrayList<>();
    }

    @Override
    public void saveAll(List<Temperature> temperatureList) {
        temperatureList
                .stream()
                .forEach(this::save);
    }

    private void save(Temperature temperature) {
        UUID uuid = UUID.randomUUID();
        Temperature novo = new Temperature(uuid.toString(),
                "nome",
                0.0,
                temperature.getDtEvento(),
                temperature.getIdEquipamento(),
                temperature.getIdInstituicao());
        temperatureList.add(novo);
    }
}