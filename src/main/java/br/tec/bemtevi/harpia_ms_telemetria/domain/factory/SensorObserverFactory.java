package br.tec.bemtevi.harpia_ms_telemetria.domain.factory;

import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.GPSRepository;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.LTERepository;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.TemperatureRepository;
import br.tec.bemtevi.harpia_ms_telemetria.domain.service.GPSService;
import br.tec.bemtevi.harpia_ms_telemetria.domain.service.LTEService;
import br.tec.bemtevi.harpia_ms_telemetria.domain.service.TemperatureService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SensorObserverFactory {
    private final Map<String, List<Observer>> observersMap;

    public SensorObserverFactory(GPSRepository gpsRepository,
                                 LTERepository lteRepository,
                                 TemperatureRepository temperatureRepository) {
        observersMap = new HashMap<>();

        List<Observer> gpsObservers = List.of(new GPSService(gpsRepository));
        List<Observer> lteObservers = List.of(new LTEService(lteRepository));
        List<Observer> temperatureObservers = List.of(new TemperatureService(temperatureRepository));

        observersMap.put("GPS", gpsObservers);
        observersMap.put("LTE", lteObservers);
        observersMap.put("TEMPERATURE", temperatureObservers);
    }

    public List<Observer> getObservers(String key) {
        List<Observer> observers = observersMap.get(key);
        if (observers == null)
            throw new IllegalArgumentException("Não existe observadores que observam essa chave.");
        return observers;
    }
}
