package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.impl.http;

import java.util.Iterator;
import java.util.Map;

public class HttpRequestContainer {
    private HttpMethod method;
    private String url;
    private String body;
    private Map<String, String> headers;
    private Map<String, Object> params;

    public static class Builder {
        private final HttpRequestContainer instance;

        public Builder(HttpMethod httpMethod, String url) throws NullPointerException, IllegalArgumentException {
            if (httpMethod == null) throw new NullPointerException("O método http deve ser especificado!");
            if (url == null) throw new NullPointerException("A url deve ser especificada!");
            if (url.isBlank()) throw new IllegalArgumentException("A url deve ser especificada!");
            instance = new HttpRequestContainer();
            instance.method = httpMethod;
            instance.url = url;
        }

        public Builder comBody(String body) {
            instance.body = body;
            return this;
        }

        public Builder comHeaders(Map<String, String> headers) {
            instance.headers = headers;
            return this;
        }

        public Builder comParams(Map<String, Object> params) {
            instance.params = params;
            return this;
        }

        public HttpRequestContainer build() {
            return instance;
        }
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getUrl() {
        String params = getHttpParams();
        return url + params;
    }

    public String getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    private String getHttpParams() {
        if (params == null) return "";
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<String> paramsKeyIterator = params.keySet().iterator();
        String paramKey = paramsKeyIterator.next();
        Object paramValue = params.get(paramKey);
        stringBuilder.append("?").append(paramKey).append("=").append(paramValue);
        while (paramsKeyIterator.hasNext()) {
            paramKey = paramsKeyIterator.next();
            paramValue = params.get(paramKey);
            stringBuilder.append("&").append(paramKey).append("=").append(paramValue);
        }
        return stringBuilder.toString();
    }
}
