package br.tec.bemtevi.harpia_ms_telemetria.application.usecase;

import br.tec.bemtevi.harpia_ms_telemetria.application.mediator.Mediator;
import br.tec.bemtevi.harpia_ms_telemetria.domain.enums.TipoEvento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.LoggerFacade;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.dispositivo.Dispositivo;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.DispositivoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProcessarDispositivoUseCase implements Observer {
    private final LoggerFacade loggerFacade;
    private final DispositivoRepository dispositivoRepository;

    public ProcessarDispositivoUseCase(Mediator mediator,
                                       LoggerFacade loggerFacade,
                                       DispositivoRepository dispositivoRepository) {
        this.loggerFacade = loggerFacade;
        this.dispositivoRepository = dispositivoRepository;
        mediator.registrar(TipoEvento.DISPOSITIVO, this);
    }

    @Override
    public void onEvent(Object object) {
        execute((Dispositivo) object);
    }

    @Transactional(rollbackFor = Exception.class)
    public void execute(Dispositivo dispositivo) {
        loggerFacade.info("Persistindo telemetrias do jetson.");
        loggerFacade.debug(String.format("Valores das telemetrias enviadas: %s.", dispositivo.toString()));
        dispositivoRepository.save(dispositivo);
    }
}
