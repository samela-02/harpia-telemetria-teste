package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.mapper;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Bateria;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPS;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.LTE;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Sensors;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Temperature;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc.BateriaGrpc;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc.GPSGrpc;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc.LTEGrpc;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc.SensorsGrpc;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc.TemperatureGrpc;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;

@Component
public class SensorsGrpcMapper {
    public SensorsGrpc sensorsToSensorsGrpc(Sensors sensors) {
        return SensorsGrpc
                .newBuilder()
                .setIdInstituicao(sensors.getIdInstituicao())
                .setIdEquipamento(sensors.getIdEquipamento())
                .addAllLte(lteListToLTEGrpcList(sensors.getLte()))
                .addAllGps(gpsListToGPSGrpcList(sensors.getGps()))
                .addAllTemperature(temperatureListToTemperatureGrpcList(sensors.getTemperature()))
                .addAllBateria(bateriaListToBateriaGrpcList(sensors.getBateria()))
                .build();
    }

    private List<LTEGrpc> lteListToLTEGrpcList(List<LTE> lteList) {
        if (lteList == null || lteList.isEmpty())
            return Collections.emptyList();
        return lteList
                .stream()
                .map(lte -> LTEGrpc
                        .newBuilder()
                        .setNmLte(asSafeString(lte.getNmLTE()))
                        .setVlSignalStrength(asSafeDouble(lte.getVlSignalStrength()))
                        .setNmCarrier(asSafeString(lte.getNmCarrier()))
                        .setNmInternetState(asSafeString(lte.getNmInternetState()))
                        .setNmSimCardState(asSafeString(lte.getNmSimCardState()))
                        .setNmStatus(asSafeString(lte.getNmStatus()))
                        .setDtEvento(asSafeLong(lte.getDtEvento()))
                        .setIdEquipamento(asSafeString(lte.getIdEquipamento()))
                        .setIdInstituicao(asSafeString(lte.getIdInstituicao()))
                        .setDtCriacao(asSafeLong(lte.getDtCriacao()))
                        .build())
                .toList();
    }

    private List<GPSGrpc> gpsListToGPSGrpcList(List<GPS> gpsList) {
        if (gpsList == null || gpsList.isEmpty())
            return Collections.emptyList();
        return gpsList
                .stream()
                .map(gps -> GPSGrpc
                        .newBuilder()
                        .setNmGps(asSafeString(gps.getNmGPS()))
                        .setVlLatitude(asSafeDouble(gps.getVlLatitude()))
                        .setVlLongitude(asSafeDouble(gps.getVlLongitude()))
                        .setVlTrueCourse(asSafeDouble(gps.getVlTrueCourse()))
                        .setDtEvento(asSafeLong(gps.getDtEvento()))
                        .setIdEquipamento(asSafeString(gps.getIdEquipamento()))
                        .setIdInstituicao(asSafeString(gps.getIdInstituicao()))
                        .setDtCriacao(asSafeLong(gps.getDtCriacao()))
                        .build())
                .toList();
    }

    private List<TemperatureGrpc> temperatureListToTemperatureGrpcList(List<Temperature> temperatureList) {
        if (temperatureList == null || temperatureList.isEmpty())
            return Collections.emptyList();
        return temperatureList
                .stream()
                .map(temperature -> TemperatureGrpc
                        .newBuilder()
                        .setNmTemperature(asSafeString(temperature.getNmTemperature()))
                        .setVlTemperature(asSafeDouble(temperature.getVlTemperature()))
                        .setDtEvento(asSafeLong(temperature.getDtEvento()))
                        .setIdEquipamento(asSafeString(temperature.getIdEquipamento()))
                        .setIdInstituicao(asSafeString(temperature.getIdInstituicao()))
                        .setDtCriacao(asSafeLong(temperature.getDtCriacao()))
                        .build())
                .toList();
    }

    private List<BateriaGrpc> bateriaListToBateriaGrpcList(List<Bateria> bateriaList) {
        if (bateriaList == null || bateriaList.isEmpty())
            return Collections.emptyList();
        return bateriaList
                .stream()
                .map(bateria -> BateriaGrpc
                        .newBuilder()
                        .setIdBateria(asSafeString(bateria.getIdBateria()))
                        .setNmBateria(asSafeString(bateria.getNmBateria()))
                        .setDtEvento(asSafeLong(bateria.getDtEvento()))
                        .setBusVoltage(asSafeDouble(bateria.getBusVoltage()))
                        .setLoadVoltage(asSafeDouble(bateria.getLoadVoltage()))
                        .setCurrentMa(asSafeDouble(bateria.getCurrentMA()))
                        .setCurrentMw(asSafeDouble(bateria.getCurrentMW()))
                        .setIdEquipamento(asSafeString(bateria.getIdEquipamento()))
                        .setIdInstituicao(asSafeString(bateria.getIdInstituicao()))
                        .setDtCriacao(asSafeLong(bateria.getDtCriacao()))
                        .build())
                .toList();
    }

    public String asSafeString(String string) {
        if (string == null)
            return "";
        return string;
    }

    private double asSafeDouble(Double doubleInstance) {
        if (doubleInstance == null)
            return 0;
        return doubleInstance;
    }

    private long asSafeLong(LocalDateTime localDateTime) {
        if (localDateTime == null)
            return 0;
        return localDateTime
                .toInstant(ZoneOffset.UTC)
                .toEpochMilli();
    }
}
