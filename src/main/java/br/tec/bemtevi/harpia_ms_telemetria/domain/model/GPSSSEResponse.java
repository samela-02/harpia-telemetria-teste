package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

import java.time.LocalDateTime;

public final class GPSSSEResponse {
    private final String nmGPS;
    private final Double vlLatitude;
    private final Double vlLongitude;
    private final Double vlTrueCourse;
    private final LocalDateTime dtEvento;

    private GPSSSEResponse(String nmGPS, Double vlLatitude, Double vlLongitude, Double vlTrueCourse, LocalDateTime dtEvento) {
        this.nmGPS = nmGPS;
        this.vlLatitude = vlLatitude;
        this.vlLongitude = vlLongitude;
        this.vlTrueCourse = vlTrueCourse;
        this.dtEvento = dtEvento;
    }

    public static GPSSSEResponse fromGPS(GPS gps) {
        return new GPSSSEResponse(gps.getNmGPS(), gps.getVlLatitude(), gps.getVlLongitude(), gps.getVlTrueCourse(), gps.getDtEvento());
    }

    public String getNmGPS() {
        return nmGPS;
    }

    public Double getVlLatitude() {
        return vlLatitude;
    }

    public Double getVlLongitude() {
        return vlLongitude;
    }

    public Double getVlTrueCourse() {
        return vlTrueCourse;
    }

    public LocalDateTime getDtEvento() {
        return dtEvento;
    }
}
