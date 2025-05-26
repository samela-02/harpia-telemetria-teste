package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.anticorruptionlayer;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Mensagem;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.dispositivo.Dispositivo;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.Sensors;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia.HarpiaTelemetryMessage;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.mapper.HarpiaDispositivoMapper;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.mapper.HarpiaSensorsMapper;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.storage.EquipamentoStorage;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.storage.InstituicaoStorage;
import org.springframework.stereotype.Component;

@Component
public class HarpiaAntiCorruptionLayer {
    private final InstituicaoStorage instituicaoStorage;
    private final EquipamentoStorage equipamentoStorage;
    private final HarpiaDispositivoMapper harpiaDispositivoMapper;
    private final HarpiaSensorsMapper harpiaSensorsMapper;

    public HarpiaAntiCorruptionLayer(InstituicaoStorage instituicaoStorage,
                                     EquipamentoStorage equipamentoStorage,
                                     HarpiaDispositivoMapper harpiaDispositivoMapper,
                                     HarpiaSensorsMapper harpiaSensorsMapper) {
        this.instituicaoStorage = instituicaoStorage;
        this.equipamentoStorage = equipamentoStorage;
        this.harpiaDispositivoMapper = harpiaDispositivoMapper;
        this.harpiaSensorsMapper = harpiaSensorsMapper;
    }

    public Mensagem fromHarpiaTelemetryMessage(HarpiaTelemetryMessage harpiaTelemetryMessage) {
        validarEquipamento(harpiaTelemetryMessage);
        validarInstituicao(harpiaTelemetryMessage);
        Dispositivo dispositivo = getDispositivo(harpiaTelemetryMessage);
        Sensors sensors = getSensors(harpiaTelemetryMessage);
        return new Mensagem(dispositivo, sensors);
    }

    private Dispositivo getDispositivo(HarpiaTelemetryMessage harpiaTelemetryMessage) {
        if (harpiaTelemetryMessage.getDevice() == null)
            return null;
        return harpiaDispositivoMapper.fromHarpiaDevice(harpiaTelemetryMessage);
    }

    private Sensors getSensors(HarpiaTelemetryMessage harpiaTelemetryMessage) {
        if (harpiaTelemetryMessage.getSensors() == null)
            return null;
        return harpiaSensorsMapper.fromHarpiaSensors(harpiaTelemetryMessage);
    }

    private void validarEquipamento(HarpiaTelemetryMessage harpiaTelemetryMessage) {
        equipamentoStorage.getInstance(harpiaTelemetryMessage.getSerial());
    }

    private void validarInstituicao(HarpiaTelemetryMessage harpiaTelemetryMessage) {
        instituicaoStorage.getInstance(harpiaTelemetryMessage.getInstitutionId());
    }
}
