package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.impl;

import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.SerializationFacade;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JacksonSerializationFacade implements SerializationFacade {
    private final ObjectMapper objectMapperCamelCase;
    private final ObjectMapper objectMapperSnakeCase;

    public JacksonSerializationFacade() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        objectMapperCamelCase = new ObjectMapper();
        objectMapperCamelCase.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
        objectMapperCamelCase.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapperCamelCase.registerModule(javaTimeModule);
        objectMapperCamelCase.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        objectMapperSnakeCase = new ObjectMapper();
        objectMapperSnakeCase.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        objectMapperSnakeCase.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapperSnakeCase.registerModule(javaTimeModule);
        objectMapperSnakeCase.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public <T> T fromCamelCaseString(String object, Class<T> classType) {
        try {
            return objectMapperCamelCase.readValue(object, classType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Não foi possível converter a string em objeto.", e);
        }
    }

    @Override
    public <T> T fromSnakeCaseBytes(byte[] bytes, Class<T> classType) {
        try {
            return objectMapperSnakeCase.readValue(bytes, classType);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível converter os bytes em objeto.", e);
        }
    }
}
