package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.filter;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.usuario.Usuario;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.ResponseData;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.HttpFacade;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.SerializationFacade;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.impl.http.HttpMethod;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.impl.http.HttpRequestContainer;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.impl.http.HttpResponseContainer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private static final String USER_INFO_ENDPOINT = "/api/v1/usuarios/user-info";
    private static final String ROLE_PREFIX = "ROLE_";

    private final HttpFacade httpFacade;
    private final SerializationFacade serializationFacade;
    private final String ccoUrl;

    public JWTAuthenticationFilter(HttpFacade httpFacade,
                                   SerializationFacade serializationFacade,
                                   @Value("${cco.url}") String ccoUrl) {
        this.httpFacade = httpFacade;
        this.serializationFacade = serializationFacade;
        this.ccoUrl = ccoUrl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = getBearerToken(request);
        Usuario usuario = getUserInfoByBearerToken(bearerToken);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(usuario.getNmUsuario(),
                        null,
                        List.of(new SimpleGrantedAuthority(ROLE_PREFIX + usuario.getRole())));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }

    private String getBearerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null || bearerToken.isBlank())
            throw new IllegalArgumentException("Token não fornecido.");
        return bearerToken;
    }

    private Usuario getUserInfoByBearerToken(String bearerToken) {
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
        ResponseData<Usuario> usuarioResponseData = serializationFacade
                .fromCamelCaseStringParameterized(httpResponseContainer.getResponseBody(),
                        ResponseData.class,
                        Usuario.class);
        return usuarioResponseData.getData();
    }
}
