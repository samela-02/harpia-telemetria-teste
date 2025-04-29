package br.tec.bemtevi.harpia_ms_telemetria.application.usecase.gps;

import br.tec.bemtevi.harpia_ms_telemetria.domain.exception.PermissaoException;
import br.tec.bemtevi.harpia_ms_telemetria.domain.gateway.CCOGateway;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPSTracker;
import br.tec.bemtevi.harpia_ms_telemetria.domain.storage.GPSStorage;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.gateway.CCOGatewayInMemory;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.sse.GPSSSE;
import br.tec.bemtevi.harpia_ms_telemetria.testutils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Sinks;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BuscarGPSStreamUseCaseTest {
    private BuscarGPSStreamUseCase buscarGPSStreamUseCase;
    private GPSStorage gpsStorage;

    @BeforeEach
    void setUp() {
        CCOGateway ccoGateway = new CCOGatewayInMemory();
        gpsStorage = new GPSSSE();
        buscarGPSStreamUseCase = new BuscarGPSStreamUseCase(ccoGateway, gpsStorage);
    }

    @Test
    void DadoUsuarioComPermissoesEIdInstituicao_QuandoExecuteForChamado_EntaoOsDadosDoGPSDevemSerRetornados() {
        String idInstituicao = "BTV";

        buscarGPSStreamUseCase.execute("admin", idInstituicao);
        buscarGPSStreamUseCase.execute("coordenador", idInstituicao);
        buscarGPSStreamUseCase.execute("opcentral", idInstituicao);
    }

    @Test
    void DadoUsuarioSemPermissoes_QuandoExecuteForChamado_EntaoPermissaoExceptionDeveSerLancada() {
        String idInstituicao = "BTV";

        PermissaoException e = assertThrows(PermissaoException.class,
                () -> buscarGPSStreamUseCase.execute("opcampo", idInstituicao));

        assertEquals("O usuário não tem permissões para buscar dados de gps.", e.getMessage());
    }

    @SuppressWarnings("unchecked")
    @Test
    void DadoUsuarioQueNaoSejaAdmin_QuandoExecuteForChamado_EntaoOParametroIDInstituicaoDeveSerAjustado() {
        String idInstituicaoQualquer = "TIVIC_PDI";
        String idInstituicaoDoUsuario = "BTV";
        Map<String, Sinks.Many<ServerSentEvent<GPSTracker>>> sinkMap = (Map<String, Sinks.Many<ServerSentEvent<GPSTracker>>>) TestUtils.getFieldFromClass("sinkMap", gpsStorage);
        Sinks.Many<ServerSentEvent<GPSTracker>> sinkQualquerAntesDoExecute = sinkMap.get(idInstituicaoQualquer);
        assertNull(sinkQualquerAntesDoExecute);
        Sinks.Many<ServerSentEvent<GPSTracker>> sinkInstituicaoDoUsuarioAntesDoExecute = sinkMap.get(idInstituicaoDoUsuario);
        assertNull(sinkInstituicaoDoUsuarioAntesDoExecute);

        buscarGPSStreamUseCase.execute("coordenador", idInstituicaoQualquer);

        Sinks.Many<ServerSentEvent<GPSTracker>> sinkQualquerDepoisDoExecute = sinkMap.get(idInstituicaoQualquer);
        assertNull(sinkQualquerDepoisDoExecute);
        Sinks.Many<ServerSentEvent<GPSTracker>> sinkInstituicaoDoUsuarioDepoisDoExecute = sinkMap.get(idInstituicaoDoUsuario);
        assertNotNull(sinkInstituicaoDoUsuarioDepoisDoExecute);
    }
}