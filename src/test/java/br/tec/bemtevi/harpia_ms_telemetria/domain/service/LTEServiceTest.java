package br.tec.bemtevi.harpia_ms_telemetria.domain.service;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.LTE;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.LTERepository;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.lte.LTERepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.testutils.ListManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LTEServiceTest {
    private LTEService lteService;
    private LTERepository lteRepository;

    @BeforeEach
    void setUp() {
        lteRepository = new LTERepositoryInMemory();
        lteService = new LTEService(lteRepository);
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoLTE_QuandoOnEventForChamado_EntaoOLTEDeveSerSalvo() {
        List<LTE> lteListAntesDoOnEvent = (List<LTE>) ListManager.getListFromRepositoryInMemory("lteList", lteRepository);
        assertTrue(lteListAntesDoOnEvent.isEmpty());

        LTE lte = new LTE(null, "name", 0.0, "nmcarrier", "nminternalstate", "nmsimcardstate", "nmstatus", null);
        lteService.onEvent(lte);

        assertFalse(lteListAntesDoOnEvent.isEmpty());
        assertEquals(1, lteListAntesDoOnEvent.size());
    }
}