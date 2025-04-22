package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.instituicao;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Instituicao;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.InstituicaoRepository;
import org.springframework.stereotype.Component;

@Component
public class InstituicaoRepositoryImpl implements InstituicaoRepository {
    private final InstituicaoRepositoryMongo instituicaoRepositoryMongo;

    public InstituicaoRepositoryImpl(InstituicaoRepositoryMongo instituicaoRepositoryMongo) {
        this.instituicaoRepositoryMongo = instituicaoRepositoryMongo;
    }

    @Override
    public Instituicao save(Instituicao instituicao) {
        instituicao = instituicaoRepositoryMongo.save(instituicao);
        return instituicao;
    }
}
