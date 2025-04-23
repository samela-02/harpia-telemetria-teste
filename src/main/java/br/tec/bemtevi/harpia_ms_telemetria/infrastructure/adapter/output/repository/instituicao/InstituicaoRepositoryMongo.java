package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.instituicao;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.instituicao.Instituicao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface InstituicaoRepositoryMongo extends CrudRepository<Instituicao, String> {
}
