package br.tec.bemtevi.harpia_ms_telemetria.domain.observer;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.LTE;

public interface LTEObserver {
    void onEvent(LTE lte);
}
