package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia.sensores;

import java.time.LocalDateTime;

public class HarpiaGPS {
    private String id;
    private String name;
    private LocalDateTime timestamp;
    private Double latitude;
    private Double longitude;
    private Double trueCourse;

    public HarpiaGPS() {
    }

    public HarpiaGPS(String id,
                     String name,
                     LocalDateTime timestamp,
                     Double latitude,
                     Double longitude,
                     Double trueCourse) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.trueCourse = trueCourse;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getTrueCourse() {
        return trueCourse;
    }
}
