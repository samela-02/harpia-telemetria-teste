package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.storage;

import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.equipamento.CriarEquipamentoUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Equipamento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.EquipamentoRepository;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.equipamento.EquipamentoRepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.testutils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class EquipamentoStorageTest {
    private EquipamentoStorage equipamentoStorage;
    private EquipamentoRepository equipamentoRepository;
    private CriarEquipamentoUseCase criarEquipamentoUseCase;

    @BeforeEach
    void setUp() {
        equipamentoRepository = new EquipamentoRepositoryInMemory();
        criarEquipamentoUseCase = new CriarEquipamentoUseCase(equipamentoRepository);
        equipamentoStorage = new EquipamentoStorage(criarEquipamentoUseCase, 2);
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoIdEquipamento_QuandoGetInstanceForChamado_EntaoOEquipamentoDeveSerRetornadoEAdicionadoNoFlyweightMap() {
        List<Equipamento> equipamentos = (List<Equipamento>) TestUtils.getFieldFromClass("equipamentos", equipamentoRepository);
        Equipamento equipamento1 = new Equipamento("H1");
        Equipamento equipamento2 = new Equipamento("H2");
        Equipamento equipamento3 = new Equipamento("H3");
        equipamentos.addAll(asList(equipamento1, equipamento2, equipamento3));

        Map<String, Equipamento> equipamentoFlyweightMap = (Map<String, Equipamento>) TestUtils.getFieldFromClass("equipamentoFlyweightMap", equipamentoStorage);
        assertTrue(equipamentoFlyweightMap.isEmpty());
        equipamentoStorage.getInstance("H1");
        assertEquals(1, equipamentoFlyweightMap.size());
        equipamentoStorage.getInstance("H2");
        assertEquals(2, equipamentoFlyweightMap.size());
        equipamentoStorage.getInstance("H3");
        assertEquals(2, equipamentoFlyweightMap.size());
        equipamentoStorage.getInstance("H3");
        assertEquals(2, equipamentoFlyweightMap.size());
        for (Equipamento equipamento : equipamentoFlyweightMap.values())
            assertNotEquals("H2", equipamento.getIdEquipamento());
    }
}