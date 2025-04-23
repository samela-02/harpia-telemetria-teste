package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto;

public class ResponseData<T> {
    private String message;
    private T data;

    public ResponseData() {
    }

    public ResponseData(String message) {
        this.message = message;
    }

    public ResponseData(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
