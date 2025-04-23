package br.tec.bemtevi.harpia_ms_telemetria.application.usecase.gps;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.*;
import br.tec.bemtevi.harpia_ms_telemetria.domain.sse.SSE;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.sse.GPSSSERepositoryInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.testutils.ListManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PropagarGPSUseCaseTest {
    private PropagarGPSUseCase propagarGPSUseCase;
    private SSE sse;

    @BeforeEach
    void setUp() {
        sse = new GPSSSERepositoryInMemory();
        propagarGPSUseCase = new PropagarGPSUseCase(sse);
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoGPSListComObjetos_QuandoOnEventForChamado_EntaoOEventoDeveSerDisparado() {
        Equipamento equipamento = new Equipamento("H-1234");
        Instituicao instituicao = new Instituicao("BTV");
        GPS gps = new GPS(null, "nome", 0.0, 0.0, 0.0, equipamento, instituicao);

        propagarGPSUseCase.onEvent(List.of(gps));

        List<GPSTracker> gpsTrackerList = (List<GPSTracker>) ListManager.getListFromRepositoryInMemory("gpsTrackerList", sse);
        assertFalse(gpsTrackerList.isEmpty());
        GPSTracker gpsTracker = gpsTrackerList.stream().findFirst().get();
        assertEquals(equipamento.getIdEquipamento(), gpsTracker.getIdEquipamento());
        assertEquals(instituicao.getIdInstituicao(), gpsTracker.getIdInstituicao());
        assertEquals(1, gpsTrackerList.size());
        List<GPSSSEResponse> gpssseResponseList = gpsTracker.getSensores();
        assertFalse(gpssseResponseList.isEmpty());
        GPSSSEResponse gpssseResponse = gpssseResponseList.stream().findFirst().get();
        assertEquals(gps.getNmGPS(), gpssseResponse.getNmGPS());
        assertEquals(1, gpssseResponseList.size());
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoGPSListVazia_QuandoOnEventForChamado_EntaoOEventoNaoDeveSerDisparado() {
        propagarGPSUseCase.onEvent(List.of());

        List<GPSTracker> gpsTrackerList = (List<GPSTracker>) ListManager.getListFromRepositoryInMemory("gpsTrackerList", sse);
        assertTrue(gpsTrackerList.isEmpty());
    }
}