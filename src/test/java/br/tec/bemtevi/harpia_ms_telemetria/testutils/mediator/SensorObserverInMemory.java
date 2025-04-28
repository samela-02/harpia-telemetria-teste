package br.tec.bemtevi.harpia_ms_telemetria.testutils.mediator;

import br.tec.bemtevi.harpia_ms_telemetria.application.mediator.SensorMediator;
import br.tec.bemtevi.harpia_ms_telemetria.domain.enums.TipoSensor;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class SensorObserverInMemory implements Observer {
    private final List<Object> eventos;

    public SensorObserverInMemory(SensorMediator sensorMediator) {
        eventos = new ArrayList<>();
        sensorMediator.registrar(TipoSensor.GPS, this);
    }

    @Override
    public void onEvent(Object object) {
        eventos.add(object);
    }
}
