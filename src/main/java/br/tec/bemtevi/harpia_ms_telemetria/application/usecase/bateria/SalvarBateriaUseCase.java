package br.tec.bemtevi.harpia_ms_telemetria.application.usecase.bateria;

import br.tec.bemtevi.harpia_ms_telemetria.application.mediator.SensorMediator;
import br.tec.bemtevi.harpia_ms_telemetria.domain.enums.TipoSensor;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Bateria;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.BateriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalvarBateriaUseCase implements Observer {
    private static final Logger log = LoggerFactory.getLogger(SalvarBateriaUseCase.class);

    private final BateriaRepository bateriaRepository;

    public SalvarBateriaUseCase(BateriaRepository bateriaRepository, SensorMediator sensorMediator) {
        this.bateriaRepository = bateriaRepository;
        sensorMediator.registrar(TipoSensor.BATERIA, this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onEvent(Object object) {
        List<Bateria> bateriaList = (List<Bateria>) object;
        bateriaRepository.saveAll(bateriaList);
        log.info("Dados de bateria persistidos com sucesso.");
    }
}
