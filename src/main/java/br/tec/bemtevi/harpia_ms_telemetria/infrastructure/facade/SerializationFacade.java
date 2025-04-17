package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade;

public interface SerializationFacade {
    <T> T fromSnakeCaseBytes(byte[] bytes, Class<T> classType);
}
