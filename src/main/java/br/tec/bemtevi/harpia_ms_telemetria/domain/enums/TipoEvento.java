package br.tec.bemtevi.harpia_ms_telemetria.domain.enums;

public enum TipoEvento {
    SENSORS("SENSORS"),
    GPS("GPS"),
    LTE("LTE"),
    TEMPERATURE("TEMPERATURE"),
    BATERIA("BATERIA");

    private final String nome;

    TipoEvento(String nome) {
        this.nome = nome;
    }

    public String asString() {
        return nome;
    }

    public static TipoEvento fromString(String tipoEventoString) {
        for (TipoEvento tipoEvento : TipoEvento.values())
            if (tipoEvento.nome.equals(tipoEventoString))
                return tipoEvento;
        throw new IllegalArgumentException(String.format("TipoEvento não encontrado: %s.", tipoEventoString));
    }
}
