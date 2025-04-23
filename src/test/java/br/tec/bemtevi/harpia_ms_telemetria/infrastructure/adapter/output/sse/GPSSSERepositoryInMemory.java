package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.sse;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPSTracker;
import br.tec.bemtevi.harpia_ms_telemetria.domain.sse.SSE;

import java.util.ArrayList;
import java.util.List;

public class GPSSSERepositoryInMemory implements SSE {
    private final List<GPSTracker> gpsTrackerList;

    public GPSSSERepositoryInMemory() {
        gpsTrackerList = new ArrayList<>();
    }

    @Override
    public void emit(Object object) {
        GPSTracker gpsTracker = (GPSTracker) object;
        gpsTrackerList.add(gpsTracker);
    }
}