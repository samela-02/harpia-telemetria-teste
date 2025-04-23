package br.tec.bemtevi.harpia_ms_telemetria.application.factory;

import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.GPSRepository;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.LTERepository;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.TemperatureRepository;
import br.tec.bemtevi.harpia_ms_telemetria.domain.service.GPSService;
import br.tec.bemtevi.harpia_ms_telemetria.domain.service.LTEService;
import br.tec.bemtevi.harpia_ms_telemetria.domain.service.TemperatureService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class SensorObserverFactory {
    private final Map<String, Set<Observer>> observersMap;

    public SensorObserverFactory(GPSRepository gpsRepository,
                                 LTERepository lteRepository,
                                 TemperatureRepository temperatureRepository) {
        observersMap = new HashMap<>();

        Set<Observer> gpsObservers = Set.of(new GPSService(gpsRepository));
        Set<Observer> lteObservers = Set.of(new LTEService(lteRepository));
        Set<Observer> temperatureObservers = Set.of(new TemperatureService(temperatureRepository));

        observersMap.put("GPS", gpsObservers);
        observersMap.put("LTE", lteObservers);
        observersMap.put("TEMPERATURE", temperatureObservers);
    }

    public Set<Observer> getObservers(String key) {
        Set<Observer> observers = observersMap.get(key);
        if (observers == null)
            throw new IllegalArgumentException("Não existe observadores que observam essa chave.");
        return observers;
    }
}
