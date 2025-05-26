package br.tec.bemtevi.harpia_ms_telemetria.domain.model.dispositivo;

import java.time.LocalDateTime;

public class Tempo {
    private LocalDateTime timestamp;
    private String uptime;

    public Tempo() {
    }

    public Tempo(LocalDateTime timestamp, String uptime) {
        this.timestamp = timestamp;
        this.uptime = uptime;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getUptime() {
        return uptime;
    }
}
