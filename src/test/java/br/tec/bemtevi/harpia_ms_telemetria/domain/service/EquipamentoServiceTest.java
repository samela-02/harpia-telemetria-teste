package br.tec.bemtevi.harpia_ms_telemetria.domain.service;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Equipamento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.EquipamentoRepository;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.equipamento.EquipamentoRepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.testutils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EquipamentoServiceTest {
    private EquipamentoService equipamentoService;
    private EquipamentoRepository equipamentoRepository;

    @BeforeEach
    void setUp() {
        equipamentoRepository = new EquipamentoRepositoryInMemory();
        equipamentoService = new EquipamentoService(equipamentoRepository);
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoIdEquipamentoExistente_QuandoAtualizarDtUltimaAtualizacaoForChamadoEADtUltimaComunicacaoForAnteriorAAtual_EntaoElaDeveSerAtualizada() {
        long cdEquipamento = 1L;
        String idEquipamento = "H-1234";
        Equipamento equipamento = new Equipamento(cdEquipamento, 1L, 1L, idEquipamento, "nm", "serie", 1, null, null);
        List<Equipamento> equipamentos = (List<Equipamento>) TestUtils.getFieldFromClass("equipamentos", equipamentoRepository);
        equipamentos.add(equipamento);

        equipamentoService.atualizarDtUltimaAtualizacao(idEquipamento);

        assertEquals(1, equipamentos.size());
        assertFalse(equipamentos.isEmpty());
        Equipamento equipamentoAtualizado = equipamentos.stream().findFirst().get();
        assertNotNull(equipamentoAtualizado.getDtUltimaComunicacao());
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoDtComunicacaoComOMSAnteriorADtUltimaComunicacao_QuandoAtualizarDtUltimaAtualizacaoForChamado_EntaoElaNaoDeveSerAtualizada() {
        long cdEquipamento = 1L;
        String idEquipamento = "H-1234";
        LocalDateTime dtUltimaComunicacao = LocalDateTime.now().plusDays(1);
        Equipamento equipamento = new Equipamento(cdEquipamento, 1L, 1L, idEquipamento, "nm", "serie", 1, null, dtUltimaComunicacao);
        List<Equipamento> equipamentos = (List<Equipamento>) TestUtils.getFieldFromClass("equipamentos", equipamentoRepository);
        equipamentos.add(equipamento);

        equipamentoService.atualizarDtUltimaAtualizacao(idEquipamento);
        assertEquals(1, equipamentos.size());
        assertFalse(equipamentos.isEmpty());
        Equipamento equipamentoAtualizado = equipamentos.stream().findFirst().get();
        assertEquals(dtUltimaComunicacao, equipamentoAtualizado.getDtUltimaComunicacao());
    }

    @Test
    void DadoIdEquipamentoInexistente_QuandoAtualizarDtUltimaAtualizacaoForChamado_EntaoIllegalStateExceptionDeveSerLancada() {
        String idEquipamento = "ID_EQUIPAMENTO";

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> equipamentoService.atualizarDtUltimaAtualizacao(idEquipamento));

        assertEquals(String.format("Equipamento não encontrado: %s.", idEquipamento), e.getMessage());
    }
}