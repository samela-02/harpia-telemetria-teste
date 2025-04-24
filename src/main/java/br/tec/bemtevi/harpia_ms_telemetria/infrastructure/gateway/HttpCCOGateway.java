package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.gateway;

import br.tec.bemtevi.harpia_ms_telemetria.domain.gateway.CCOGateway;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Usuario;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.ResponseData;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.HttpFacade;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.SerializationFacade;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.impl.http.HttpMethod;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.impl.http.HttpRequestContainer;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.impl.http.HttpResponseContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HttpCCOGateway implements CCOGateway {
    private static final String USER_INFO_ENDPOINT = "/api/v1/usuarios/user-info";

    private final HttpFacade httpFacade;
    private final SerializationFacade serializationFacade;
    private final String ccoUrl;

    public HttpCCOGateway(HttpFacade httpFacade,
                          SerializationFacade serializationFacade,
                          @Value("${cco.url}") String ccoUrl) {
        this.httpFacade = httpFacade;
        this.serializationFacade = serializationFacade;
        this.ccoUrl = ccoUrl;
    }

    @Override
    public Usuario findUserInfo(String bearerToken) {
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
