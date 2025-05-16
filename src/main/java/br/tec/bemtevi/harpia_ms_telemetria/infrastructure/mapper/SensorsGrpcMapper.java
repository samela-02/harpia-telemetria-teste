package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.mapper;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.*;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Sensors;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.grpc.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
                        .setDtEvento(asSafeTimestamp(lte.getDtEvento()))
                        .setIdEquipamento(asSafeString(lte.getIdEquipamento()))
                        .setIdInstituicao(asSafeString(lte.getIdInstituicao()))
                        .setDtCriacao(asSafeTimestamp(lte.getDtCriacao()))
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
                        .setDtEvento(asSafeTimestamp(gps.getDtEvento()))
                        .setIdEquipamento(asSafeString(gps.getIdEquipamento()))
                        .setIdInstituicao(asSafeString(gps.getIdInstituicao()))
                        .setDtCriacao(asSafeTimestamp(gps.getDtCriacao()))
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
                        .setDtEvento(asSafeTimestamp(temperature.getDtEvento()))
                        .setIdEquipamento(asSafeString(temperature.getIdEquipamento()))
                        .setIdInstituicao(asSafeString(temperature.getIdInstituicao()))
                        .setDtCriacao(asSafeTimestamp(temperature.getDtCriacao()))
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
                        .setDtEvento(asSafeTimestamp(bateria.getDtEvento()))
                        .setBusVoltage(asSafeDouble(bateria.getBusVoltage()))
                        .setLoadVoltage(asSafeDouble(bateria.getLoadVoltage()))
                        .setCurrentMa(asSafeDouble(bateria.getCurrentMA()))
                        .setCurrentMw(asSafeDouble(bateria.getCurrentMW()))
                        .setIdEquipamento(asSafeString(bateria.getIdEquipamento()))
                        .setIdInstituicao(asSafeString(bateria.getIdInstituicao()))
                        .setDtCriacao(asSafeTimestamp(bateria.getDtCriacao()))
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

    private com.google.protobuf.Timestamp asSafeTimestamp(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return com.google.protobuf.Timestamp
                    .newBuilder()
                    .build();
        }
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return com.google.protobuf.Timestamp
                .newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }
}
