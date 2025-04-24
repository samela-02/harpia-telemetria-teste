package br.tec.bemtevi.harpia_ms_telemetria.domain.enums;

import static java.util.Arrays.stream;

public enum Role {
    ADMINISTRADOR(1, "ADMINISTRADOR"),
    COORDENADOR_OPERACAO(2, "COORDENADOR_OPERACAO"),
    OPERADOR_CENTRAL(3, "OPERADOR_CENTRAL"),
    OPERADOR_CAMPO(4, "OPERADOR_CAMPO");

    private int nivel;
    private String nome;

    Role(int nivel, String nome) {
        this.nivel = nivel;
        this.nome = nome;
    }

    public static Role fromString(String nome) {
        return stream(Role.values())
                .filter(role -> role.asString().equals(nome))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Não foi encontrado um role com esse nome: %s.", nome)));
    }

    public int getNivel() {
        return nivel;
    }

    public String asString() {
        return nome;
    }
}
