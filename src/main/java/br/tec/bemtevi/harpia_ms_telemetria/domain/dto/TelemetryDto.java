package br.tec.bemtevi.harpia_ms_telemetria.domain.dto;

public class TelemetryDto {
    private String idEquipamento;
    private SensorsDto sensorsDto;

    public TelemetryDto() {
    }

    public TelemetryDto(String idEquipamento, SensorsDto sensorsDto) {
        this.idEquipamento = idEquipamento;
        this.sensorsDto = sensorsDto;
    }

    public String getIdEquipamento() {
        return idEquipamento;
    }

    public SensorsDto getSensorsDto() {
        return sensorsDto;
    }
}
