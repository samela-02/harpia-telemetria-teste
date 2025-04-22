package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.instituicao;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Instituicao;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.InstituicaoRepository;

import java.util.ArrayList;
import java.util.List;

public class InstituicaoRepositoryInMemory implements InstituicaoRepository {
    private final List<Instituicao> instituicaoList;

    public InstituicaoRepositoryInMemory() {
        instituicaoList = new ArrayList<>();
    }

    @Override
    public Instituicao save(Instituicao instituicao) {
        instituicaoList.add(instituicao);
        return instituicao;
    }
}