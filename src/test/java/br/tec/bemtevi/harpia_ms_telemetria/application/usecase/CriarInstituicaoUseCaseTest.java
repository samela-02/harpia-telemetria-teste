package br.tec.bemtevi.harpia_ms_telemetria.application.usecase;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Instituicao;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.InstituicaoRepository;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.instituicao.InstituicaoRepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.testutils.ListManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CriarInstituicaoUseCaseTest {
    private CriarInstituicaoUseCase criarInstituicaoUseCase;
    private InstituicaoRepository instituicaoRepository;

    @BeforeEach
    void setUp() {
        instituicaoRepository = new InstituicaoRepositoryInMemory();
        criarInstituicaoUseCase = new CriarInstituicaoUseCase(instituicaoRepository);
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoIdInstituicao_QuandoExecuteForChamado_EntaoUmaInstituicaoDeveSerCriada() {
        String idInstituicao = "BTV";

        Instituicao response = criarInstituicaoUseCase.execute(idInstituicao);

        assertEquals(idInstituicao, response.getIdInstituicao());
        List<Instituicao> instituicaoList = (List<Instituicao>) ListManager.getListFromRepositoryInMemory("instituicaoList", instituicaoRepository);
        assertEquals(1, instituicaoList.size());
        assertFalse(instituicaoList.isEmpty());
        Instituicao instituicao = instituicaoList.stream().findFirst().get();
        assertEquals(response.getIdInstituicao(), instituicao.getIdInstituicao());
    }
}