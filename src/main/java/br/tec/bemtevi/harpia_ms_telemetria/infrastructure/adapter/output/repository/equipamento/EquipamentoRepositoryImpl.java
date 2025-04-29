package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.equipamento;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Equipamento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.EquipamentoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EquipamentoRepositoryImpl implements EquipamentoRepository {
    private final EquipamentoJpaRepository equipamentoJpaRepository;

    public EquipamentoRepositoryImpl(EquipamentoJpaRepository equipamentoJpaRepository) {
        this.equipamentoJpaRepository = equipamentoJpaRepository;
    }

    @Override
    public Optional<Equipamento> findEquipamentoByIdEquipamento(String idEquipamento) {
        return equipamentoJpaRepository.findByIdEquipamento(idEquipamento);
    }
}
