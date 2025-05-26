package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.dispositivo;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.dispositivo.Dispositivo;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.DispositivoRepository;

import java.util.ArrayList;
import java.util.List;

public class DispositivoRepositoryInMemory implements DispositivoRepository {
    private final List<Dispositivo> dispositivos;

    public DispositivoRepositoryInMemory() {
        dispositivos = new ArrayList<>();
    }

    @Override
    public void save(Dispositivo dispositivo) {
        dispositivos.add(dispositivo);
    }
}