package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia.dispositivo;

import java.time.LocalDateTime;

public class HarpiaTime {
    private LocalDateTime timestamp;
    private String uptime;

    public HarpiaTime() {
    }

    public HarpiaTime(LocalDateTime timestamp, String uptime) {
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
