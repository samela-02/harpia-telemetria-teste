package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.gps;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.gps.GPS;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.GPSRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GPSRepositoryImpl implements GPSRepository {
    private final GPSRepositoryMongo gpsRepositoryMongo;

    public GPSRepositoryImpl(GPSRepositoryMongo gpsRepositoryMongo) {
        this.gpsRepositoryMongo = gpsRepositoryMongo;
    }

    @Override
    public void saveAll(List<GPS> gpsList) {
        gpsRepositoryMongo.saveAll(gpsList);
    }
}
