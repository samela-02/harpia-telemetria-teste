package br.tec.bemtevi.harpia_ms_telemetria.testutils;

import java.lang.reflect.Field;

public class ListManager {
    public static Object getListFromRepositoryInMemory(String fieldName, Object repository) {
        try {
            Field field = repository
                    .getClass()
                    .getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(repository);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
