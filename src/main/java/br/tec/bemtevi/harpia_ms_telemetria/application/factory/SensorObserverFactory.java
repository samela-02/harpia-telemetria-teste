package br.tec.bemtevi.harpia_ms_telemetria.application.factory;

import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.gps.SalvarGPSUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.lte.SalvarLTEUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.temperature.SalvarTemperatureUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class SensorObserverFactory {
    private final Map<String, Set<Observer>> observersMap;

    public SensorObserverFactory(SalvarGPSUseCase salvarGPSUseCase,
                                 SalvarLTEUseCase salvarLTEUseCase,
                                 SalvarTemperatureUseCase salvarTemperatureUseCase) {
        observersMap = new HashMap<>();

        Set<Observer> gpsObservers = Set.of(salvarGPSUseCase);
        Set<Observer> lteObservers = Set.of(salvarLTEUseCase);
        Set<Observer> temperatureObservers = Set.of(salvarTemperatureUseCase);

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
