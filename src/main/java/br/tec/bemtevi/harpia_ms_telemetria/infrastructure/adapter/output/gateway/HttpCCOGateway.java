package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.gateway;

import br.tec.bemtevi.harpia_ms_telemetria.domain.gateway.CCOGateway;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Usuario;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.ResponseData;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.http.HttpFacade;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.SerializationFacade;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.http.HttpMethod;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.http.HttpRequestContainer;
import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.http.HttpResponseContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HttpCCOGateway implements CCOGateway {
    private final HttpFacade httpFacade;
    private final SerializationFacade serializationFacade;
    private final String authorizationServerUrl;
    private final String authorizationServerUserInfoEndpoint;

    public HttpCCOGateway(HttpFacade httpFacade,
                          SerializationFacade serializationFacade,
                          @Value("${authorization-server.url}") String authorizationServerUrl,
                          @Value("${authorization-server.user-info-endpoint}") String authorizationServerUserInfoEndpoint) {
        this.httpFacade = httpFacade;
        this.serializationFacade = serializationFacade;
        this.authorizationServerUrl = authorizationServerUrl;
        this.authorizationServerUserInfoEndpoint = authorizationServerUserInfoEndpoint;
    }

    @Override
    public Usuario findUserInfo(String bearerToken) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", bearerToken);
        HttpRequestContainer httpRequestContainer = new HttpRequestContainer
                .Builder(HttpMethod.GET, authorizationServerUrl + authorizationServerUserInfoEndpoint)
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
