package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.anticorruptionlayer;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Equipamento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Instituicao;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPS;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.LTE;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Sensors;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Temperature;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia.HarpiaTelemetryMessage;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.storage.EquipamentoStorage;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.storage.InstituicaoStorage;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class HarpiaAntiCorruptionLayer {
    private final InstituicaoStorage instituicaoStorage;
    private final EquipamentoStorage equipamentoStorage;

    public HarpiaAntiCorruptionLayer(InstituicaoStorage instituicaoStorage, EquipamentoStorage equipamentoStorage) {
        this.instituicaoStorage = instituicaoStorage;
        this.equipamentoStorage = equipamentoStorage;
    }

    public Sensors fromHarpiaTelemetryMessage(HarpiaTelemetryMessage harpiaTelemetryMessage) {
        Equipamento equipamento = equipamentoStorage.getInstance(harpiaTelemetryMessage.getSerial());
        Instituicao instituicao = instituicaoStorage.getInstance(harpiaTelemetryMessage.getInstitutionId());
        LocalDateTime dtCriacao = LocalDateTime.now();
        List<LTE> lteList = harpiaLteToLteDomain(harpiaTelemetryMessage, dtCriacao, equipamento, instituicao);
        List<GPS> gpsList = harpiaGpsToGpsDomain(harpiaTelemetryMessage, dtCriacao, equipamento, instituicao);
        List<Temperature> temperatureList = harpiaTemperatureToTemperatureDomain(harpiaTelemetryMessage, dtCriacao, equipamento, instituicao);
        return new Sensors(harpiaTelemetryMessage.getInstitutionId(),
                harpiaTelemetryMessage.getSerial(),
                lteList,
                gpsList,
                temperatureList);
    }

    private List<LTE> harpiaLteToLteDomain(HarpiaTelemetryMessage harpiaTelemetryMessage,
                                           LocalDateTime dtCriacao,
                                           Equipamento equipamento,
                                           Instituicao instituicao) {
        if (harpiaTelemetryMessage.getSensors().getLte() == null)
            return null;
        return harpiaTelemetryMessage
                .getSensors()
                .getLte()
                .stream()
                .map(lte -> new LTE(null,
                        lte.getName(),
                        lte.getSignalStrength(),
                        lte.getCarrier(),
                        lte.getInternetState(),
                        lte.getSimCardState(),
                        lte.getStatus(),
                        dtCriacao,
                        equipamento,
                        instituicao))
                .toList();
    }

    private List<GPS> harpiaGpsToGpsDomain(HarpiaTelemetryMessage harpiaTelemetryMessage,
                                           LocalDateTime dtCriacao,
                                           Equipamento equipamento,
                                           Instituicao instituicao) {
        if (harpiaTelemetryMessage.getSensors().getGps() == null)
            return null;
        return harpiaTelemetryMessage
                .getSensors()
                .getGps()
                .stream()
                .map(gps -> new GPS(null,
                        gps.getName(),
                        gps.getLatitude(),
                        gps.getLongitude(),
                        gps.getTrueCourse(),
                        dtCriacao,
                        equipamento,
                        instituicao))
                .toList();
    }

    private List<Temperature> harpiaTemperatureToTemperatureDomain(HarpiaTelemetryMessage harpiaTelemetryMessage,
                                                                   LocalDateTime dtCriacao,
                                                                   Equipamento equipamento,
                                                                   Instituicao instituicao) {
        if (harpiaTelemetryMessage.getSensors().getTemperature() == null)
            return null;
        return harpiaTelemetryMessage
                .getSensors()
                .getTemperature()
                .stream()
                .map(temperature -> new Temperature(null,
                        temperature.getName(),
                        temperature.getValue(),
                        dtCriacao,
                        equipamento,
                        instituicao))
                .toList();
    }
}
