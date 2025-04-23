package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.equipamento;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.equipamento.Equipamento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.EquipamentoRepository;
import org.springframework.stereotype.Component;

@Component
public class EquipamentoRepositoryImpl implements EquipamentoRepository {
    private final EquipamentoRepositoryMongo equipamentoRepositoryMongo;

    public EquipamentoRepositoryImpl(EquipamentoRepositoryMongo equipamentoRepositoryMongo) {
        this.equipamentoRepositoryMongo = equipamentoRepositoryMongo;
    }

    @Override
    public Equipamento save(Equipamento equipamento) {
        equipamento = equipamentoRepositoryMongo.save(equipamento);
        return equipamento;
    }
}
