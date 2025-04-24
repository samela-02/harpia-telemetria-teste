package br.tec.bemtevi.harpia_ms_telemetria.application.usecase;

import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.equipamento.CriarEquipamentoUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Equipamento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.EquipamentoRepository;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.equipamento.EquipamentoRepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.testutils.ListManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CriarEquipamentoUseCaseTest {
    private CriarEquipamentoUseCase criarEquipamentoUseCase;
    private EquipamentoRepository equipamentoRepository;

    @BeforeEach
    void setUp() {
        equipamentoRepository = new EquipamentoRepositoryInMemory();
        criarEquipamentoUseCase = new CriarEquipamentoUseCase(equipamentoRepository);
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoIdEquipamento_QuandoExecuteForChamado_EntaoUmEquipamentoDeveSerCriado() {
        String idEquipamento = "H1234";

        Equipamento response = criarEquipamentoUseCase.execute(idEquipamento);

        assertEquals(idEquipamento, response.getIdEquipamento());
        List<Equipamento> equipamentoList = (List<Equipamento>) ListManager.getListFromRepositoryInMemory("equipamentos", equipamentoRepository);
        assertFalse(equipamentoList.isEmpty());
        Equipamento equipamento = equipamentoList.stream().findFirst().get();
        assertEquals(response.getIdEquipamento(), equipamento.getIdEquipamento());
    }
}