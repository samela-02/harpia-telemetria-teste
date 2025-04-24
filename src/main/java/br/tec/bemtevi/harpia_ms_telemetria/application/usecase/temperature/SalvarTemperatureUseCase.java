package br.tec.bemtevi.harpia_ms_telemetria.application.usecase.temperature;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Temperature;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.TemperatureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalvarTemperatureUseCase implements Observer {
    private static final Logger log = LoggerFactory.getLogger(SalvarTemperatureUseCase.class);
    private final TemperatureRepository temperatureRepository;

    public SalvarTemperatureUseCase(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void onEvent(Object object) {
        List<Temperature> temperatureList = (List<Temperature>) object;
        temperatureRepository.saveAll(temperatureList);
        log.info("Dados de temperatura persistidos com sucesso.");
    }
}
