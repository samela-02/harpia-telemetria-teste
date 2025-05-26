package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia.sensores;

import java.time.LocalDateTime;

public class HarpiaLTE {
    private String id;
    private String name;
    private LocalDateTime timestamp;
    private Double signalStrength;
    private String carrier;
    private String internetState;
    private String simCardState;
    private String status;

    public HarpiaLTE() {
    }

    public HarpiaLTE(String id,
                     String name,
                     LocalDateTime timestamp,
                     Double signalStrength,
                     String carrier,
                     String internetState,
                     String simCardState,
                     String status) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
        this.signalStrength = signalStrength;
        this.carrier = carrier;
        this.internetState = internetState;
        this.simCardState = simCardState;
        this.status = status;
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

    public Double getSignalStrength() {
        return signalStrength;
    }

    public String getCarrier() {
        return carrier;
    }

    public String getInternetState() {
        return internetState;
    }

    public String getSimCardState() {
        return simCardState;
    }

    public String getStatus() {
        return status;
    }
}
