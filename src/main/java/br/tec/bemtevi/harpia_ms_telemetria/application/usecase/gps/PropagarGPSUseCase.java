package br.tec.bemtevi.harpia_ms_telemetria.application.usecase.gps;

import br.tec.bemtevi.harpia_ms_telemetria.application.mediator.SensorMediator;
import br.tec.bemtevi.harpia_ms_telemetria.domain.enums.TipoSensor;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.*;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.domain.sse.SSE;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropagarGPSUseCase implements Observer {
    private final SSE sse;

    public PropagarGPSUseCase(@Qualifier(value = "GPSSSE") SSE sse, SensorMediator sensorMediator) {
        this.sse = sse;
        sensorMediator.registrar(TipoSensor.GPS, this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onEvent(Object object) {
        List<GPS> gpsList = (List<GPS>) object;
        if (!gpsList.isEmpty()) {
            propagarSensoresDeGPS(gpsList);
        }
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private void propagarSensoresDeGPS(List<GPS> gpsList) {
        GPS gps = gpsList.stream().findFirst().get();
        Instituicao instituicao = gps.getInstituicao();
        Equipamento equipamento = gps.getEquipamento();
        List<GPSSSEResponse> gpssseResponseList = converterGPSListEmGPSSSEList(gpsList);
        GPSTracker gpsTracker = new GPSTracker(instituicao.getIdInstituicao(),
                equipamento.getIdEquipamento(),
                gpssseResponseList);
        sse.emit(gpsTracker);
    }

    private List<GPSSSEResponse> converterGPSListEmGPSSSEList(List<GPS> gpsList) {
        return gpsList
                .stream()
                .map(GPSSSEResponse::fromGPS)
                .toList();
    }
}
