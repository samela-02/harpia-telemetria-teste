package br.tec.bemtevi.harpia_ms_telemetria.application.usecase.gps;

import br.tec.bemtevi.harpia_ms_telemetria.application.mediator.Mediator;
import br.tec.bemtevi.harpia_ms_telemetria.domain.enums.TipoEvento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPS;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPSSSEResponse;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPSTracker;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.domain.sse.SSE;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropagarGPSUseCase implements Observer {
    private final SSE sse;

    public PropagarGPSUseCase(@Qualifier(value = "GPSSSE") SSE sse, Mediator sensorMediator) {
        this.sse = sse;
        sensorMediator.registrar(TipoEvento.GPS, this);
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
        List<GPSSSEResponse> gpssseResponseList = converterGPSListEmGPSSSEList(gpsList);
        GPSTracker gpsTracker = new GPSTracker(gps.getIdInstituicao(),
                gps.getIdEquipamento(),
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
