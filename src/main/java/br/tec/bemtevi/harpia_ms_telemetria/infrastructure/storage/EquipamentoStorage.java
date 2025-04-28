package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.storage;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Equipamento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EquipamentoStorage {
    private final EquipamentoRepository equipamentoRepository;
    private final int corteEquipamento;
    private final Map<String, Equipamento> equipamentoFlyweightMap;
    private String ultimoIdEquipamentoAdicionado;

    public EquipamentoStorage(EquipamentoRepository equipamentoRepository, @Value("${corte.equipamento}") int corteEquipamento) {
        this.equipamentoRepository = equipamentoRepository;
        this.corteEquipamento = corteEquipamento;
        equipamentoFlyweightMap = new ConcurrentHashMap<>();
    }

    public Equipamento getInstance(String idEquipamento) {
        Equipamento equipamento = equipamentoFlyweightMap.get(idEquipamento);
        if (equipamento == null) {
            equipamento = findEquipamentoByIdEquipamento(idEquipamento);
            adicionarEquipamentoNoMap(idEquipamento, equipamento);
            ultimoIdEquipamentoAdicionado = idEquipamento;
        }
        return equipamento;
    }

    private Equipamento findEquipamentoByIdEquipamento(String idEquipamento) {
        return equipamentoRepository
                .findEquipamentoByIdEquipamento(idEquipamento)
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("Equipamento não encontrado: %s.", idEquipamento)));
    }

    private void adicionarEquipamentoNoMap(String idEquipamento, Equipamento equipamento) {
        if (equipamentoFlyweightMap.size() == corteEquipamento)
            equipamentoFlyweightMap.remove(ultimoIdEquipamentoAdicionado);
        equipamentoFlyweightMap.put(idEquipamento, equipamento);
    }
}
