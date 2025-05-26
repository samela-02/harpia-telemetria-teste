package br.tec.bemtevi.harpia_ms_telemetria.application.usecase;

import br.tec.bemtevi.harpia_ms_telemetria.application.mediator.Mediator;
import br.tec.bemtevi.harpia_ms_telemetria.domain.enums.TipoEvento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.Sensors;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.domain.service.ReflectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProcessarSensorsUseCase implements Observer {
    private static final String[] CAMPOS_IGNORADOS_DO_SENSORS = {
            "idInstituicao",
            "idEquipamento"
    };

    private final ReflectionService reflectionService;
    private final Mediator sensorMediator;

    public ProcessarSensorsUseCase(ReflectionService reflectionService,
                                   Mediator sensorMediator) {
        this.reflectionService = reflectionService;
        this.sensorMediator = sensorMediator;
        sensorMediator.registrar(TipoEvento.SENSORS, this);
    }

    @Override
    public void onEvent(Object object) {
        execute((Sensors) object);
    }

    @Transactional(rollbackFor = Exception.class)
    public void execute(Sensors sensors) {
        List<String> sensorsFieldsString = reflectionService.converterAtributosEmString(sensors, CAMPOS_IGNORADOS_DO_SENSORS);
        for (String sensorFieldName : sensorsFieldsString) {
            try {
                processarSensores(sensorFieldName, sensors);
            } catch (Exception e) {
                throw new RuntimeException(String.format("Erro ao processar os sensores de: %s", sensorFieldName), e);
            }
        }
    }

    private void processarSensores(String sensorFieldName, Sensors sensors) {
        Object campo = reflectionService.getAtributoByNome(sensorFieldName, sensors);
        if (campo != null) {
            TipoEvento tipoEvento = TipoEvento.fromString(sensorFieldName.toUpperCase());
            sensorMediator.emitirEvento(tipoEvento, campo);
        }
    }
}
