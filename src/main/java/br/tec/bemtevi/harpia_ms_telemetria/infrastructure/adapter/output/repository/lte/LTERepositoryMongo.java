package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.lte;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.LTE;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface LTERepositoryMongo extends MongoRepository<LTE, String> {
}
