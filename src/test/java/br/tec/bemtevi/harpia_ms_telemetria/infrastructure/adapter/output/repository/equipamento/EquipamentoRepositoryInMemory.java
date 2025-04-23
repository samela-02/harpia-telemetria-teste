package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.equipamento;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.equipamento.Equipamento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.EquipamentoRepository;

import java.util.ArrayList;
import java.util.List;

public class EquipamentoRepositoryInMemory implements EquipamentoRepository {
    private final List<Equipamento> equipamentos;

    public EquipamentoRepositoryInMemory() {
        equipamentos = new ArrayList<>();
    }

    @Override
    public Equipamento save(Equipamento equipamento) {
        equipamentos.add(equipamento);
        return equipamento;
    }
}