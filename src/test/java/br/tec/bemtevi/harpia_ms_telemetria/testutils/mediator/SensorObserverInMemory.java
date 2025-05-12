package br.tec.bemtevi.harpia_ms_telemetria.testutils.mediator;

import br.tec.bemtevi.harpia_ms_telemetria.application.mediator.Mediator;
import br.tec.bemtevi.harpia_ms_telemetria.domain.enums.TipoEvento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class SensorObserverInMemory implements Observer {
    private final List<Object> eventos;

    public SensorObserverInMemory(Mediator sensorMediator) {
        eventos = new ArrayList<>();
        sensorMediator.registrar(TipoEvento.GPS, this);
    }

    @Override
    public void onEvent(Object object) {
        eventos.add(object);
    }
}
