package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.anticorruptionlayer;

import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.HarpiaTelemetryMessage;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPS;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.LTE;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Sensors;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Temperature;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HarpiaAntiCorruptionLayer {
    public Sensors fromHarpiaTelemetryMessage(HarpiaTelemetryMessage harpiaTelemetryMessage) {
        List<LTE> lteList = harpiaLteToLteDomain(harpiaTelemetryMessage);
        List<GPS> gpsList = harpiaGpsToGpsDomain(harpiaTelemetryMessage);
        List<Temperature> temperatureList = harpiaTemperatureToTemperatureDomain(harpiaTelemetryMessage);
        return new Sensors(harpiaTelemetryMessage.getInstitutionId(), harpiaTelemetryMessage.getSerial(), lteList, gpsList, temperatureList);
    }

    private List<LTE> harpiaLteToLteDomain(HarpiaTelemetryMessage harpiaTelemetryMessage) {
        return harpiaTelemetryMessage
                .getSensors()
                .getLte()
                .stream()
                .map(lte -> new LTE(null, lte.getName(), lte.getSignalStrength(), lte.getCarrier(), lte.getInternetState(), lte.getSimCardState(), lte.getStatus()))
                .toList();
    }

    private List<GPS> harpiaGpsToGpsDomain(HarpiaTelemetryMessage harpiaTelemetryMessage) {
        return harpiaTelemetryMessage
                .getSensors()
                .getGps()
                .stream()
                .map(gps -> new GPS(null, gps.getName(), gps.getLatitude(), gps.getLongitude(), gps.getTrueCourse()))
                .toList();
    }

    private List<Temperature> harpiaTemperatureToTemperatureDomain(HarpiaTelemetryMessage harpiaTelemetryMessage) {
        return harpiaTelemetryMessage
                .getSensors()
                .getTemperature()
                .stream()
                .map(temperature -> new Temperature(null, temperature.getName(), temperature.getValue()))
                .toList();
    }
}
