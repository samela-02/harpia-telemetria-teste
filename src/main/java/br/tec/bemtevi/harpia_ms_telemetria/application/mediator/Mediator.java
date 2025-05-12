package br.tec.bemtevi.harpia_ms_telemetria.application.mediator;

import br.tec.bemtevi.harpia_ms_telemetria.domain.enums.TipoEvento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Mediator {
    private final Map<TipoEvento, Set<Observer>> tipoEventoObservers;

    public Mediator() {
        tipoEventoObservers = new ConcurrentHashMap<>();
    }

    public void registrar(TipoEvento tipoEvento, Observer observer) {
        Set<Observer> observers = findObserversByTipoEvento(tipoEvento);
        observers.add(observer);
    }

    public void emitirEvento(TipoEvento tipoEvento, Object evento) {
        Set<Observer> observers = findObserversByTipoEvento(tipoEvento);
        observers
                .parallelStream()
                .forEach(observer -> observer.onEvent(evento));
    }

    private Set<Observer> findObserversByTipoEvento(TipoEvento tipoEvento) {
        Set<Observer> observers = tipoEventoObservers.get(tipoEvento);
        if (observers == null) {
            observers = new HashSet<>();
            tipoEventoObservers.put(tipoEvento, observers);
        }
        return observers;
    }
}
