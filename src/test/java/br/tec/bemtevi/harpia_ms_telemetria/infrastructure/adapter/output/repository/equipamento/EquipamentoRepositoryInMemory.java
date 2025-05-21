package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.equipamento;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Equipamento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.EquipamentoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EquipamentoRepositoryInMemory implements EquipamentoRepository {
    private final List<Equipamento> equipamentos;

    public EquipamentoRepositoryInMemory() {
        equipamentos = new ArrayList<>();
    }

    @Override
    public Optional<Equipamento> findEquipamentoByIdEquipamento(String idEquipamento) {
        return equipamentos
                .stream()
                .filter(equipamento -> equipamento.getIdEquipamento().equals(idEquipamento))
                .findFirst();
    }

    @Override
    public void save(Equipamento equipamento) {
        equipamentos.add(equipamento);
    }
}