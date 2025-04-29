package br.tec.bemtevi.harpia_ms_telemetria.domain.repository;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Equipamento;

import java.util.Optional;

public interface EquipamentoRepository {
    Optional<Equipamento> findEquipamentoByIdEquipamento(String idEquipamento);
}
