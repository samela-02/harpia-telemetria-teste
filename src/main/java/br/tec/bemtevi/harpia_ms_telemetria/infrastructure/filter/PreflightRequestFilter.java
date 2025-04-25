package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Order(1)
@Component
public class PreflightRequestFilter implements WebFilter {
    private final String frontendUrl;

    public PreflightRequestFilter(@Value("${frontend.url}") String frontendUrl) {
        this.frontendUrl = frontendUrl;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if (exchange.getRequest().getMethod().equals(HttpMethod.OPTIONS)) {
            adicionarCORSHeaders(exchange);
            ServerHttpResponse serverHttpResponse = exchange.getResponse();
            serverHttpResponse.setStatusCode(HttpStatus.OK);
            return serverHttpResponse.setComplete();
        }
        return chain.filter(exchange);
    }

    private void adicionarCORSHeaders(ServerWebExchange exchange) {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        serverHttpResponse.setStatusCode(HttpStatus.OK);
        HttpHeaders headers = serverHttpResponse.getHeaders();
        headers.add("Access-Control-Allow-Origin", frontendUrl);
        headers.addAll("Access-Control-Allow-Headers", List.of("Authorization", "Cache-Control", "Content-Type"));
        headers.addAll("Access-Control-Allow-Methods", List.of("GET", "OPTIONS"));
    }
}
