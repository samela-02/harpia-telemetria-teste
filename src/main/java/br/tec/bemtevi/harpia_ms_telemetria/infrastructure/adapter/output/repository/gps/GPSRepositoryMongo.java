package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.gps;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPS;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface GPSRepositoryMongo extends MongoRepository<GPS, String> {
}
