package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.anticorruptionlayer;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Mensagem;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia.HarpiaTelemetryMessage;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.mapper.HarpiaDispositivoMapper;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.mapper.HarpiaSensorsMapper;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.storage.EquipamentoStorage;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.storage.InstituicaoStorage;

public class HarpiaAntiCorruptionLayerDefeituoso extends HarpiaAntiCorruptionLayer {
    public HarpiaAntiCorruptionLayerDefeituoso(InstituicaoStorage instituicaoStorage, EquipamentoStorage equipamentoStorage, HarpiaDispositivoMapper harpiaDispositivoMapper, HarpiaSensorsMapper harpiaSensorsMapper) {
        super(instituicaoStorage, equipamentoStorage, harpiaDispositivoMapper, harpiaSensorsMapper);
    }

    @Override
    public Mensagem fromHarpiaTelemetryMessage(HarpiaTelemetryMessage harpiaTelemetryMessage) {
        throw new UnsupportedOperationException();
    }
}
