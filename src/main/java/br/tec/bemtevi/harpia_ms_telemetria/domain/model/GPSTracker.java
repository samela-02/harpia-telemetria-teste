package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

import java.util.List;

public final class GPSTracker {
    private final String idInstituicao;
    private final String idEquipamento;
    private final List<GPSSSEResponse> sensores;

    public GPSTracker(String idInstituicao, String idEquipamento, List<GPSSSEResponse> sensores) {
        this.idInstituicao = idInstituicao;
        this.idEquipamento = idEquipamento;
        this.sensores = List.copyOf(sensores);
    }

    public String getIdInstituicao() {
        return idInstituicao;
    }

    public String getIdEquipamento() {
        return idEquipamento;
    }

    public List<GPSSSEResponse> getSensores() {
        return sensores;
    }
}
