package br.tec.bemtevi.harpia_ms_telemetria.application.usecase.equipamento;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Equipamento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.EquipamentoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CriarEquipamentoUseCase {
    private final EquipamentoRepository equipamentoRepository;

    public CriarEquipamentoUseCase(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Equipamento execute(String idEquipamento) {
        Equipamento equipamento = new Equipamento(idEquipamento);
        equipamento = equipamentoRepository.save(equipamento);
        return equipamento;
    }
}
