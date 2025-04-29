package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.storage;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Instituicao;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.InstituicaoRepository;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InstituicaoStorage {
    private final InstituicaoRepository instituicaoRepository;
    private final Map<String, Instituicao> instituicaoMap;

    public InstituicaoStorage(InstituicaoRepository instituicaoRepository, Map<String, Instituicao> instituicaoMap) {
        this.instituicaoRepository = instituicaoRepository;
        this.instituicaoMap = instituicaoMap;
    }

    public Instituicao getInstance(String idInstituicao) {
        Instituicao instituicao = instituicaoMap.get(idInstituicao);
        if (instituicao == null) {
            instituicao = findInstituicaoByIdInstituicao(idInstituicao);
            instituicaoMap.put(idInstituicao, instituicao);
        }
        return instituicao;
    }

    private Instituicao findInstituicaoByIdInstituicao(String idInstituicao) {
        return instituicaoRepository
                .findInstituicaoByIdInstituicao(idInstituicao)
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("Instituição não encontrada: %s.", idInstituicao)));
    }
}
