package br.tec.bemtevi.harpia_ms_telemetria.application.usecase.telemetria;

import br.tec.bemtevi.harpia_ms_telemetria.application.factory.SensorObserverFactory;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Sensors;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.stream;

@Service
public class ProcessarTelemetriaUseCase {
    private static final String[] CAMPOS_IGNORADOS_DO_SENSORS = {
            "idInstituicao",
            "idEquipamento"
    };

    private final SensorObserverFactory sensorObserverFactory;

    public ProcessarTelemetriaUseCase(SensorObserverFactory sensorObserverFactory) {
        this.sensorObserverFactory = sensorObserverFactory;
    }

    @Transactional(rollbackFor = Exception.class)
    public void execute(Sensors sensors) {
        List<String> sensorsFieldsString = getSensorsFieldsAsString(sensors);
        for (String sensorFieldName : sensorsFieldsString) {
            try {
                processarSensores(sensorFieldName, sensors);
            } catch (Exception e) {
                throw new RuntimeException(String.format("Erro ao processar os sensores de: %s", sensorFieldName), e);
            }
        }
    }

    private List<String> getSensorsFieldsAsString(Sensors sensors) {
        return stream(sensors.getClass().getDeclaredFields())
                .map(field -> {
                    String[] fieldFullNameSplitado = field.toString().split("\\.");
                    return fieldFullNameSplitado[fieldFullNameSplitado.length - 1];
                })
                .filter(campo -> {
                    for (String campoIgnorado : CAMPOS_IGNORADOS_DO_SENSORS) {
                        if (campo.equals(campoIgnorado))
                            return false;
                    }
                    return true;
                })
                .toList();
    }

    private void processarSensores(String sensorFieldName, Sensors sensors) {
        Object campo = getCampo(sensorFieldName, sensors);
        Set<Observer> observers = sensorObserverFactory.getObservers(sensorFieldName.toUpperCase());
        observers
                .parallelStream()
                .forEach(observer -> observer.onEvent(campo));
    }

    private Object getCampo(String sensorFieldName, Sensors sensors) {
        try {
            Field field = sensors
                    .getClass()
                    .getDeclaredField(sensorFieldName);
            field.setAccessible(true);
            return field.get(sensors);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Não foi possível recuperar o campo da classe.", e);
        }
    }
}
