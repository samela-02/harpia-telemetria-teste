package br.tec.bemtevi.harpia_ms_telemetria.application.mediator;

import br.tec.bemtevi.harpia_ms_telemetria.domain.enums.TipoSensor;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SensorMediator {
    private final Map<TipoSensor, Set<Observer>> tipoSensorObservers;

    public SensorMediator() {
        tipoSensorObservers = new ConcurrentHashMap<>();
    }

    public void registrar(TipoSensor tipoSensor, Observer observer) {
        Set<Observer> observers = findObserversByTipoSensor(tipoSensor);
        observers.add(observer);
    }

    public void emitirEvento(TipoSensor tipoSensor, Object evento) {
        Set<Observer> observers = findObserversByTipoSensor(tipoSensor);
        observers
                .parallelStream()
                .forEach(observer -> observer.onEvent(evento));
    }

    private Set<Observer> findObserversByTipoSensor(TipoSensor tipoSensor) {
        Set<Observer> observers = tipoSensorObservers.get(tipoSensor);
        if (observers == null) {
            observers = new HashSet<>();
            tipoSensorObservers.put(tipoSensor, observers);
        }
        return observers;
    }
}
