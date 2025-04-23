package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.filter;

import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.HttpFacade;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.impl.http.HttpMethod;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.impl.http.HttpRequestContainer;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.impl.http.HttpResponseContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class JWTAuthenticationFilter implements WebFilter {
    private static final String USER_INFO_ENDPOINT = "/api/v1/usuarios/user-info";
    private static final String ROLE_PREFIX = "ROLE_";
    private static final Logger log = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    private final HttpFacade httpFacade;
    private final String ccoUrl;

    public JWTAuthenticationFilter(HttpFacade httpFacade,
                                   @Value("${cco.url}") String ccoUrl) {
        this.httpFacade = httpFacade;
        this.ccoUrl = ccoUrl;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        try {
            String bearerToken = getBearerToken(exchange.getRequest().getHeaders());
            validarToken(bearerToken);
        } catch (IllegalArgumentException e) {
            log.warn("Request com token inválido recebido.");
            ServerHttpResponse serverHttpResponse = exchange.getResponse();
            serverHttpResponse.setStatusCode(HttpStatus.FORBIDDEN);
            return serverHttpResponse.setComplete();
        }
        return chain.filter(exchange);
    }


    private String getBearerToken(HttpHeaders httpHeaders) {
        String bearerToken = httpHeaders.getFirst("Authorization");
        if (bearerToken == null || bearerToken.isBlank())
            throw new IllegalArgumentException("Token não fornecido.");
        return bearerToken;
    }

    private void validarToken(String bearerToken) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", bearerToken);
        HttpRequestContainer httpRequestContainer = new HttpRequestContainer
                .Builder(HttpMethod.GET, ccoUrl + USER_INFO_ENDPOINT)
                .comHeaders(headers)
                .build();
        HttpResponseContainer httpResponseContainer = httpFacade.send(httpRequestContainer);
        if (httpResponseContainer.getStatusCode() == 403)
            throw new IllegalArgumentException("Token inválido.");
        if (httpResponseContainer.getStatusCode() != 200)
            throw new RuntimeException("Erro ao fazer requisição no CCO.");
    }
}
