package br.tec.bemtevi.harpia_ms_telemetria.domain.model;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.dispositivo.Dispositivo;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.sensores.Sensors;

public class Mensagem {
    private Dispositivo dispositivo;
    private Sensors sensors;

    public Mensagem(Dispositivo dispositivo, Sensors sensors) {
        this.dispositivo = dispositivo;
        this.sensors = sensors;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public Sensors getSensors() {
        return sensors;
    }
}
