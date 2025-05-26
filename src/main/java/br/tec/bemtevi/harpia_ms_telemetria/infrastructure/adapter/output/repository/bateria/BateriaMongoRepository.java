package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.bateria;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.Bateria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface BateriaMongoRepository extends MongoRepository<Bateria, String> {
}
