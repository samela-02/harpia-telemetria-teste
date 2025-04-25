package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.filter;

import br.tec.bemtevi.harpia_ms_telemetria.domain.gateway.CCOGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Order(2)
@Component
public class JWTAuthenticationFilter implements WebFilter {
    private static final Logger log = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    private final CCOGateway ccoGateway;

    public JWTAuthenticationFilter(CCOGateway ccoGateway) {
        this.ccoGateway = ccoGateway;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        try {
            String bearerToken = getBearerToken(exchange.getRequest());
            validarToken(bearerToken);
        } catch (IllegalArgumentException e) {
            log.warn("Request com token inválido recebido.");
            ServerHttpResponse serverHttpResponse = exchange.getResponse();
            serverHttpResponse.setStatusCode(HttpStatus.FORBIDDEN);
            return serverHttpResponse.setComplete();
        }
        return chain.filter(exchange);
    }

    private String getBearerToken(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().getFirst("Authorization");
        if (bearerToken == null || bearerToken.isBlank())
            throw new IllegalArgumentException("Token não fornecido.");
        return bearerToken;
    }

    private void validarToken(String bearerToken) {
        ccoGateway.findUserInfo(bearerToken);
    }
}
