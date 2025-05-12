package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade;

import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.LoggerFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Slf4jLoggerFacade implements LoggerFacade {
    private static final Logger log = LoggerFactory.getLogger(Slf4jLoggerFacade.class);

    @Override
    public void debug(String message) {
        log.debug(message);
    }

    @Override
    public void error(String message) {
        log.error(message);
    }

    @Override
    public void info(String message) {
        log.info(message);
    }

    @Override
    public void warn(String message) {
        log.warn(message);
    }
}
