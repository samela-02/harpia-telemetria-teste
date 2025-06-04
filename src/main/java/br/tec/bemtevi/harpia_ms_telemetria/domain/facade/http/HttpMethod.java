package br.tec.bemtevi.harpia_ms_telemetria.domain.facade.http;

public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    private final String nome;

    HttpMethod(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
