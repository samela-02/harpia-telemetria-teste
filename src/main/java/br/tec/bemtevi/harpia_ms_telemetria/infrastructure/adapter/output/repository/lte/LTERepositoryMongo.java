package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.lte;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.lte.LTE;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface LTERepositoryMongo extends CrudRepository<LTE, String> {
}
