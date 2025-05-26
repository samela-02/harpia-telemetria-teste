package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.anticorruptionlayer;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Equipamento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Instituicao;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Mensagem;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.dispositivo.Dispositivo;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.*;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia.HarpiaTelemetryMessage;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.mapper.HarpiaDispositivoMapper;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.storage.EquipamentoStorage;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.storage.InstituicaoStorage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HarpiaAntiCorruptionLayer {
    private final InstituicaoStorage instituicaoStorage;
    private final EquipamentoStorage equipamentoStorage;
    private final HarpiaDispositivoMapper harpiaDispositivoMapper;

    public HarpiaAntiCorruptionLayer(InstituicaoStorage instituicaoStorage,
                                     EquipamentoStorage equipamentoStorage,
                                     HarpiaDispositivoMapper harpiaDispositivoMapper) {
        this.instituicaoStorage = instituicaoStorage;
        this.equipamentoStorage = equipamentoStorage;
        this.harpiaDispositivoMapper = harpiaDispositivoMapper;
    }

    public Mensagem fromHarpiaTelemetryMessage(HarpiaTelemetryMessage harpiaTelemetryMessage) {
        Equipamento equipamento = equipamentoStorage.getInstance(harpiaTelemetryMessage.getSerial());
        Instituicao instituicao = instituicaoStorage.getInstance(harpiaTelemetryMessage.getInstitutionId());
        Dispositivo dispositivo = harpiaDispositivoMapper.fromHarpiaDevice(harpiaTelemetryMessage, instituicao, equipamento);
        Sensors sensors = getSensors(harpiaTelemetryMessage, equipamento, instituicao);
        return new Mensagem(dispositivo, sensors);
    }

    private Sensors getSensors(HarpiaTelemetryMessage harpiaTelemetryMessage,
                               Equipamento equipamento,
                               Instituicao instituicao) {
        List<LTE> lteList = harpiaLteToLteDomain(harpiaTelemetryMessage, equipamento, instituicao);
        List<GPS> gpsList = harpiaGpsToGpsDomain(harpiaTelemetryMessage, equipamento, instituicao);
        List<Temperature> temperatureList = harpiaTemperatureToTemperatureDomain(harpiaTelemetryMessage, equipamento, instituicao);
        List<Bateria> bateriaList = harpiaBatteryToBateriaDomain(harpiaTelemetryMessage, equipamento, instituicao);
        return new Sensors(harpiaTelemetryMessage.getInstitutionId(),
                harpiaTelemetryMessage.getSerial(),
                lteList,
                gpsList,
                temperatureList,
                bateriaList);
    }

    private List<LTE> harpiaLteToLteDomain(HarpiaTelemetryMessage harpiaTelemetryMessage,
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
                        lte.getTimestamp(),
                        equipamento.getIdEquipamento(),
                        instituicao.getIdInstituicao()))
                .toList();
    }

    private List<GPS> harpiaGpsToGpsDomain(HarpiaTelemetryMessage harpiaTelemetryMessage,
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
                        gps.getTimestamp(),
                        equipamento.getIdEquipamento(),
                        instituicao.getIdInstituicao()))
                .toList();
    }

    private List<Temperature> harpiaTemperatureToTemperatureDomain(HarpiaTelemetryMessage harpiaTelemetryMessage,
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
                        temperature.getTemperature(),
                        temperature.getTimestamp(),
                        equipamento.getIdEquipamento(),
                        instituicao.getIdInstituicao()))
                .toList();
    }

    private List<Bateria> harpiaBatteryToBateriaDomain(HarpiaTelemetryMessage harpiaTelemetryMessage,
                                                       Equipamento equipamento,
                                                       Instituicao instituicao) {
        if (harpiaTelemetryMessage.getSensors().getBattery() == null)
            return null;
        return harpiaTelemetryMessage
                .getSensors()
                .getBattery()
                .stream()
                .map(harpiaBattery -> new Bateria(
                        null,
                        harpiaBattery.getId(),
                        harpiaBattery.getName(),
                        harpiaBattery.getTimestamp(),
                        harpiaBattery.getBusVoltage(),
                        harpiaBattery.getLoadVoltage(),
                        harpiaBattery.getCurrent(),
                        harpiaBattery.getCurrent(),
                        equipamento.getIdEquipamento(),
                        instituicao.getIdInstituicao()
                ))
                .toList();
    }
}
