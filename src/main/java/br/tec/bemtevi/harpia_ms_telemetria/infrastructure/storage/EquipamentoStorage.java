package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.storage;

import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.equipamento.CriarEquipamentoUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Equipamento;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EquipamentoStorage {
    private final CriarEquipamentoUseCase criarEquipamentoUseCase;
    private final int corteEquipamento;
    private final Map<String, Equipamento> equipamentoFlyweightMap;
    private String ultimoIdEquipamentoAdicionado;

    public EquipamentoStorage(CriarEquipamentoUseCase criarEquipamentoUseCase,
                              @Value("${corte.equipamento}") int corteEquipamento) {
        this.criarEquipamentoUseCase = criarEquipamentoUseCase;
        this.corteEquipamento = corteEquipamento;
        equipamentoFlyweightMap = new ConcurrentHashMap<>();
    }

    public Equipamento getInstance(String idEquipamento) {
        Equipamento equipamento = equipamentoFlyweightMap.get(idEquipamento);
        if (equipamento == null) {
            equipamento = criarEquipamento(idEquipamento);
            adicionarEquipamentoNoMap(idEquipamento, equipamento);
            ultimoIdEquipamentoAdicionado = idEquipamento;
        }
        return equipamento;
    }

    private Equipamento criarEquipamento(String idEquipamento) {
        return criarEquipamentoUseCase.execute(idEquipamento);
    }

    private void adicionarEquipamentoNoMap(String idEquipamento, Equipamento equipamento) {
        if (equipamentoFlyweightMap.size() == corteEquipamento)
            equipamentoFlyweightMap.remove(ultimoIdEquipamentoAdicionado);
        equipamentoFlyweightMap.put(idEquipamento, equipamento);
    }
}
