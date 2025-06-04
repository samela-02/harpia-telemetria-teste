package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.dto;

import java.time.LocalDateTime;

public final class FallbackDto {
    private final String servico;
    private final LocalDateTime dataEvento;
    private final String mensagemException;
    private final Object causa;
    private final Object conteudoFallback;

    public FallbackDto(String servico,
                       LocalDateTime dataEvento,
                       String mensagemException,
                       Object causa,
                       Object conteudoFallback) {
        this.servico = servico;
        this.dataEvento = dataEvento;
        this.mensagemException = mensagemException;
        this.causa = causa;
        this.conteudoFallback = conteudoFallback;
    }

    public String getServico() {
        return servico;
    }

    public LocalDateTime getDataEvento() {
        return dataEvento;
    }

    public String getMensagemException() {
        return mensagemException;
    }

    public Object getCausa() {
        return causa;
    }

    public Object getConteudoFallback() {
        return conteudoFallback;
    }
}
