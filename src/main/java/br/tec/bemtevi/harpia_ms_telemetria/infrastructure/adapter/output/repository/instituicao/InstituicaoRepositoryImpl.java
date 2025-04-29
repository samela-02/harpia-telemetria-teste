package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.instituicao;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Instituicao;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.InstituicaoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InstituicaoRepositoryImpl implements InstituicaoRepository {
    private final InstituicaoJpaRepository instituicaoJpaRepository;

    public InstituicaoRepositoryImpl(InstituicaoJpaRepository instituicaoJpaRepository) {
        this.instituicaoJpaRepository = instituicaoJpaRepository;
    }

    @Override
    public Optional<Instituicao> findInstituicaoByIdInstituicao(String idInstituicao) {
        return instituicaoJpaRepository.findByIdInstituicao(idInstituicao);
    }
}
