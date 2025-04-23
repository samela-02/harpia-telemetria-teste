package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.service;

import br.tec.bemtevi.harpia_ms_telemetria.domain.gateway.CCOGateway;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Usuario;
import br.tec.bemtevi.harpia_ms_telemetria.domain.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class HttpUsuarioService implements UsuarioService {
    private final CCOGateway ccoGateway;

    public HttpUsuarioService(CCOGateway ccoGateway) {
        this.ccoGateway = ccoGateway;
    }

    @Override
    public Usuario findUsuarioLogado() {
        String bearerToken = getBearerToken();
        return ccoGateway.findUserInfo(bearerToken);
    }

    private String getBearerToken() {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        String bearerToken = httpServletRequest.getHeader("Authorization");
        if (bearerToken == null || bearerToken.isBlank())
            throw new IllegalArgumentException("Token não fornecido.");
        return bearerToken;
    }

    private HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}
