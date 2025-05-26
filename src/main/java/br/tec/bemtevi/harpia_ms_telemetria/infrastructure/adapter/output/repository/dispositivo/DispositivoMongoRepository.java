package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.dispositivo;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.dispositivo.Dispositivo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface DispositivoMongoRepository extends MongoRepository<Dispositivo, String> {
}
