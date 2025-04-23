package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.temperature;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.temperature.Temperature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TemperatureRepositoryMongo extends CrudRepository<Temperature, String> {
}
