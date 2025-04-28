package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.instituicao;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Instituicao;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.InstituicaoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InstituicaoRepositoryInMemory implements InstituicaoRepository {
    private final List<Instituicao> instituicaoList;

    public InstituicaoRepositoryInMemory() {
        instituicaoList = new ArrayList<>();
    }

    @Override
    public Optional<Instituicao> findInstituicaoByIdInstituicao(String idInstituicao) {
        return instituicaoList
                .stream()
                .filter(instituicao -> instituicao.getIdInstituicao().equals(idInstituicao))
                .findFirst();
    }

    @Override
    public Instituicao save(Instituicao instituicao) {
        instituicaoList.add(instituicao);
        return instituicao;
    }
}