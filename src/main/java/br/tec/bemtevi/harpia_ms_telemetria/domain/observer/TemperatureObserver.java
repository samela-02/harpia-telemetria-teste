package br.tec.bemtevi.harpia_ms_telemetria.domain.observer;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Temperature;

public interface TemperatureObserver {
    void onEvent(Temperature temperature);
}
