package br.tec.bemtevi.harpia_ms_telemetria.domain.repository;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.dispositivo.Dispositivo;

public interface DispositivoRepository {
    void save(Dispositivo dispositivo);
}
