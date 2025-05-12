package br.tec.bemtevi.harpia_ms_telemetria.domain.facade;

public interface LoggerFacade {
    void debug(String message);
    void error(String message);
    void info(String message);
    void warn(String message);
}
