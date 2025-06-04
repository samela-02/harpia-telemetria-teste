package br.tec.bemtevi.harpia_ms_telemetria.domain.facade;

public interface SerializationFacade {
    String asSnakeCaseString(Object object);
    byte[] asSnakeCaseBytes(Object object);
    <T> T fromCamelCaseString(String object, Class<T> classType);
    <T, S> T fromCamelCaseStringParameterized(String object, Class<T> classType, Class<S> parameterizedClass);
    <T> T fromSnakeCaseBytes(byte[] bytes, Class<T> classType);
}
