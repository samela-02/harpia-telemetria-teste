package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade;

import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.SerializationFacade;

public class SerializationFacadeDefeituoso implements SerializationFacade {
    @Override
    public String asSnakeCaseString(Object object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte[] asSnakeCaseBytes(Object object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T fromCamelCaseString(String object, Class<T> classType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T, S> T fromCamelCaseStringParameterized(String object, Class<T> classType, Class<S> parameterizedClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T fromSnakeCaseBytes(byte[] bytes, Class<T> classType) {
        throw new UnsupportedOperationException();
    }
}