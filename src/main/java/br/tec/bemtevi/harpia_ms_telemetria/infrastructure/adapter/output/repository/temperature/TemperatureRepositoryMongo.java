package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.temperature;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.Temperature;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TemperatureRepositoryMongo extends MongoRepository<Temperature, String> {
}
