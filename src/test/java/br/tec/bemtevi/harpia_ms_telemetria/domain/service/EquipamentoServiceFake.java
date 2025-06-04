package br.tec.bemtevi.harpia_ms_telemetria.domain.service;

import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.EquipamentoRepository;

public class EquipamentoServiceFake extends EquipamentoService {
    public EquipamentoServiceFake(EquipamentoRepository equipamentoRepository) {
        super(equipamentoRepository);
    }

    @Override
    public void atualizarDtUltimaAtualizacao(String idEquipamento) {
    }
}