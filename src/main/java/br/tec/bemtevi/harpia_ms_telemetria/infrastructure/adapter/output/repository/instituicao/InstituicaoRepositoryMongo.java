package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.instituicao;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Instituicao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface InstituicaoRepositoryMongo extends CrudRepository<Instituicao, String> {
    Optional<Instituicao> findByIdInstituicao(String idInstituicao);
}
