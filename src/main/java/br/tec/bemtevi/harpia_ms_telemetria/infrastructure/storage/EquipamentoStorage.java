package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.storage;

import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.equipamento.CriarEquipamentoUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Equipamento;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EquipamentoStorage {
    private final CriarEquipamentoUseCase criarEquipamentoUseCase;
    private final Map<String, Equipamento> equipamentoMap;

    public EquipamentoStorage(CriarEquipamentoUseCase criarEquipamentoUseCase) {
        this.criarEquipamentoUseCase = criarEquipamentoUseCase;
        equipamentoMap = new ConcurrentHashMap<>();
    }

    public Equipamento getInstance(String idEquipamento) {
        Equipamento equipamento = equipamentoMap.get(idEquipamento);
        if (equipamento == null)
            return criarEquipamento(idEquipamento);
        return equipamento;
    }

    private Equipamento criarEquipamento(String idEquipamento) {
        Equipamento equipamento = criarEquipamentoUseCase.execute(idEquipamento);
        equipamentoMap.put(idEquipamento, equipamento);
        return equipamento;
    }
}
