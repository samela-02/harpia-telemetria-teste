package br.tec.bemtevi.harpia_ms_telemetria.domain.service;

import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

import static java.util.Arrays.stream;

@Service
public class ReflectionService {
    public List<String> converterAtributosEmString(Object object, String... atributosIgnorados) {
        return stream(object.getClass().getDeclaredFields())
                .map(atributo -> {
                    String[] fieldFullNameSplitado = atributo.toString().split("\\.");
                    return fieldFullNameSplitado[fieldFullNameSplitado.length - 1];
                })
                .filter(atributo -> {
                    for (String atributoIgnorado : atributosIgnorados) {
                        if (atributo.equals(atributoIgnorado))
                            return false;
                    }
                    return true;
                })
                .toList();
    }

    public Object getAtributoByNome(String atributo, Object object) {
        try {
            Field field = object
                    .getClass()
                    .getDeclaredField(atributo);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Não foi possível recuperar o atributo da classe.", e);
        }
    }
}
