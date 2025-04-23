package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.gps;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.gps.GPS;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.GPSRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GPSRepositoryInMemory implements GPSRepository {
    private final List<GPS> gpsList;

     public GPSRepositoryInMemory() {
        gpsList = new ArrayList<>();
    }

    @Override
    public void saveAll(List<GPS> gpsList) {
         gpsList
                 .stream()
                 .forEach(this::save);
    }

    private void save(GPS gps) {
        UUID uuid = UUID.randomUUID();
        GPS novo = new GPS(uuid.toString(),
                gps.getNmGPS(),
                gps.getVlLatitude(),
                gps.getVlLongitude(),
                gps.getVlTrueCourse(),
                null,
                null
        );
        novo.setEquipamento(gps.getEquipamento());
        gpsList.add(novo);
    }
}