package br.tec.bemtevi.harpia_ms_telemetria.domain.repository;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.instituicao.Instituicao;

public interface InstituicaoRepository {
    Instituicao save(Instituicao instituicao);
}
