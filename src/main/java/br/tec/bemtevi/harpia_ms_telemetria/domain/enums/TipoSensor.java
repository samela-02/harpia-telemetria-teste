package br.tec.bemtevi.harpia_ms_telemetria.domain.enums;

public enum TipoSensor {
    GPS("GPS"),
    LTE("LTE"),
    TEMPERATURE("TEMPERATURE");

    private final String nome;

    TipoSensor(String nome) {
        this.nome = nome;
    }

    public String asString() {
        return nome;
    }

    public static TipoSensor fromString(String tipoSensorString) {
        for (TipoSensor tipoSensor : TipoSensor.values())
            if (tipoSensor.nome.equals(tipoSensorString))
                return tipoSensor;
        throw new IllegalArgumentException(String.format("TipoSensor não encontrado: %s.", tipoSensorString));
    }
}
