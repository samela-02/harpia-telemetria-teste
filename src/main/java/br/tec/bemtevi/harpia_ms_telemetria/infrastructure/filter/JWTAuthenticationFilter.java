package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.filter;

import br.tec.bemtevi.harpia_ms_telemetria.domain.gateway.CCOGateway;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private static final String ROLE_PREFIX = "ROLE_";

    private final CCOGateway ccoGateway;

    public JWTAuthenticationFilter(CCOGateway ccoGateway) {
        this.ccoGateway = ccoGateway;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = getBearerToken(request);
        Usuario usuario = getUserInfoByBearerToken(bearerToken);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(usuario.getNmUsuario() + "/" + usuario.getIdInstituicao(),
                        null,
                        List.of(new SimpleGrantedAuthority(ROLE_PREFIX + usuario.getRole().asString())));
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
        return ccoGateway.findUserInfo(bearerToken);
    }
}
