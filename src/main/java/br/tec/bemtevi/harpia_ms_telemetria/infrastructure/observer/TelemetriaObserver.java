package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.observer;

public interface TelemetriaObserver {
    void onEvent(byte[] mensagem);
}
