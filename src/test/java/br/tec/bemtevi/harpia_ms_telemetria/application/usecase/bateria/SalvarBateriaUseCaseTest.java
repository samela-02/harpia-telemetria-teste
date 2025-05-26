package br.tec.bemtevi.harpia_ms_telemetria.application.usecase.bateria;

import br.tec.bemtevi.harpia_ms_telemetria.application.mediator.Mediator;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.Bateria;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.BateriaRepository;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.bateria.BateriaRepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.testutils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalvarBateriaUseCaseTest {
    private SalvarBateriaUseCase salvarBateriaUseCase;
    private BateriaRepository bateriaRepository;

    @BeforeEach
    void setUp() {
        bateriaRepository = new BateriaRepositoryInMemory();
        Mediator sensorMediator = new Mediator();
        salvarBateriaUseCase = new SalvarBateriaUseCase(bateriaRepository, sensorMediator);
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoListaDeBaterias_QuandoOnEventForChamado_EntaoOsSensoresDeBateriaDevemSerSalvos() {
        List<Bateria> bateriaList = (List<Bateria>) TestUtils.getFieldFromClass("bateriaList", bateriaRepository);
        assertTrue(bateriaList.isEmpty());
        Bateria bateria = new Bateria("cd", "id", "nm", LocalDateTime.now(), 0.0, 0.0, 0.0, 0.0, "idequipamento", "idinstituicao");

        salvarBateriaUseCase.onEvent(List.of(bateria));

        assertFalse(bateriaList.isEmpty());
        assertEquals(1, bateriaList.size());
    }
}