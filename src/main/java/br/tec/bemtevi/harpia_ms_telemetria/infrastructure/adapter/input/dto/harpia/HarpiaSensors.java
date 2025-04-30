package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia;

import java.util.List;

public class HarpiaSensors {
    private List<HarpiaLTE> lte;
    private List<HarpiaGPS> gps;
    private List<HarpiaTemperature> temperature;
    private List<HarpiaBattery> battery;

    public HarpiaSensors() {
    }

    public HarpiaSensors(List<HarpiaLTE> lte,
                         List<HarpiaGPS> gps,
                         List<HarpiaTemperature> temperature,
                         List<HarpiaBattery> battery) {
        this.lte = lte;
        this.gps = gps;
        this.temperature = temperature;
        this.battery = battery;
    }

    public List<HarpiaLTE> getLte() {
        return lte;
    }

    public List<HarpiaGPS> getGps() {
        return gps;
    }

    public List<HarpiaTemperature> getTemperature() {
        return temperature;
    }

    public List<HarpiaBattery> getBattery() {
        return battery;
    }
}
