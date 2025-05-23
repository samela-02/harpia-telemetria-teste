package br.tec.bemtevi.harpia_ms_telemetria.domain.service;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Equipamento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.EquipamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EquipamentoService {
    private final EquipamentoRepository equipamentoRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    public void atualizarDtUltimaAtualizacao(String idEquipamento) {
        Equipamento equipamento = findEquipamentoByIdEquipamento(idEquipamento);
        LocalDateTime dtComunicacaoComOMS = LocalDateTime.now();
        if (equipamento.isDtUltimaAtualizacaoAntesDe(dtComunicacaoComOMS))
            equipamento.atualizarDtUltimaComunicacao();
        equipamentoRepository.save(equipamento);
    }

    private Equipamento findEquipamentoByIdEquipamento(String idEquipamento) {
        return equipamentoRepository
                .findEquipamentoByIdEquipamento(idEquipamento)
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("Equipamento não encontrado: %s.", idEquipamento)));
    }
}
