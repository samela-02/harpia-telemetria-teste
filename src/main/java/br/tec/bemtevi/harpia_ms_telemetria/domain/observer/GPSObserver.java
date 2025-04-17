package br.tec.bemtevi.harpia_ms_telemetria.domain.observer;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPS;

public interface GPSObserver {
    void onEvent(GPS gps);
}
