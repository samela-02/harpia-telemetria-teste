package br.tec.bemtevi.harpia_ms_telemetria.domain.repository;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Instituicao;

import java.util.Optional;

public interface InstituicaoRepository {
    Optional<Instituicao> findInstituicaoByIdInstituicao(String idInstituicao);
}
