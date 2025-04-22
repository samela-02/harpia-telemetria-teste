package br.tec.bemtevi.harpia_ms_telemetria.application.usecase;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Instituicao;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.InstituicaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CriarInstituicaoUseCase {
    private final InstituicaoRepository instituicaoRepository;

    public CriarInstituicaoUseCase(InstituicaoRepository instituicaoRepository) {
        this.instituicaoRepository = instituicaoRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Instituicao execute(String idInstituicao) {
        Instituicao instituicao = new Instituicao(idInstituicao);
        instituicao = instituicaoRepository.save(instituicao);
        return instituicao;
    }
}
