package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

public final class GPSSSEResponse {
    private final String nmGPS;
    private final Double vlLatitude;
    private final Double vlLongitude;
    private final Double vlTrueCourse;

    private GPSSSEResponse(String nmGPS, Double vlLatitude, Double vlLongitude, Double vlTrueCourse) {
        this.nmGPS = nmGPS;
        this.vlLatitude = vlLatitude;
        this.vlLongitude = vlLongitude;
        this.vlTrueCourse = vlTrueCourse;
    }

    public static GPSSSEResponse fromGPS(GPS gps) {
        return new GPSSSEResponse(gps.getNmGPS(), gps.getVlLatitude(), gps.getVlLongitude(), gps.getVlTrueCourse());
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
}
