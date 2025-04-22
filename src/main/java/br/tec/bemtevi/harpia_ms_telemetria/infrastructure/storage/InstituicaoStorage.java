package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.storage;

import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.CriarInstituicaoUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Instituicao;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InstituicaoStorage {
    private final CriarInstituicaoUseCase criarInstituicaoUseCase;
    private final Map<String, Instituicao> instituicaoMap;

    public InstituicaoStorage(CriarInstituicaoUseCase criarInstituicaoUseCase) {
        this.criarInstituicaoUseCase = criarInstituicaoUseCase;
        instituicaoMap = new ConcurrentHashMap<>();
    }

    public Instituicao getInstance(String idInstituicao) {
        Instituicao instituicao = instituicaoMap.get(idInstituicao);
        if (instituicao == null)
            return criarInstituicao(idInstituicao);
        return instituicao;
    }

    private Instituicao criarInstituicao(String idInstituicao) {
        Instituicao instituicao = criarInstituicaoUseCase.execute(idInstituicao);
        instituicaoMap.put(idInstituicao, instituicao);
        return instituicao;
    }
}
