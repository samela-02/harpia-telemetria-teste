package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.dispositivo;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.dispositivo.Dispositivo;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.DispositivoRepository;
import org.springframework.stereotype.Component;

@Component
public class DispositivoRepositoryImpl implements DispositivoRepository {
    private final DispositivoMongoRepository dispositivoMongoRepository;

    public DispositivoRepositoryImpl(DispositivoMongoRepository dispositivoMongoRepository) {
        this.dispositivoMongoRepository = dispositivoMongoRepository;
    }

    @Override
    public void save(Dispositivo dispositivo) {
        dispositivoMongoRepository.save(dispositivo);
    }
}
