package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.observer;

import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.CriarEquipamentoUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.ProcessarTelemetriaUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.*;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.HarpiaTelemetryMessage;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.anticorruptionlayer.HarpiaAntiCorruptionLayer;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.SerializationFacade;
import org.springframework.stereotype.Component;

@Component(value = "TelemetriaObserver")
public class TelemetriaObserver implements Observer {
    private final SerializationFacade serializationFacade;
    private final HarpiaAntiCorruptionLayer harpiaAntiCorruptionLayer;
    private final CriarEquipamentoUseCase criarEquipamentoUseCase;
    private final ProcessarTelemetriaUseCase processarTelemetriaUseCase;

    public TelemetriaObserver(SerializationFacade serializationFacade,
                              HarpiaAntiCorruptionLayer harpiaAntiCorruptionLayer,
                              CriarEquipamentoUseCase criarEquipamentoUseCase,
                              ProcessarTelemetriaUseCase processarTelemetriaUseCase) {
        this.serializationFacade = serializationFacade;
        this.harpiaAntiCorruptionLayer = harpiaAntiCorruptionLayer;
        this.criarEquipamentoUseCase = criarEquipamentoUseCase;
        this.processarTelemetriaUseCase = processarTelemetriaUseCase;
    }

    @Override
    public void onEvent(Object object) {
        byte[] mensagem = (byte[]) object;
        HarpiaTelemetryMessage harpiaTelemetryMessage = serializationFacade
                .fromSnakeCaseBytes(mensagem, HarpiaTelemetryMessage.class);
        Sensors sensors = harpiaAntiCorruptionLayer.fromHarpiaTelemetryMessage(harpiaTelemetryMessage);
        Equipamento equipamento = criarEquipamentoUseCase.execute(sensors.getIdEquipamento());
        associarSensoresAoEquipamento(sensors, equipamento);
        processarTelemetriaUseCase.execute(sensors);
    }

    private void associarSensoresAoEquipamento(Sensors sensors, Equipamento equipamento) {
        for (GPS gps : sensors.getGps())
            gps.setEquipamento(equipamento);
        for (LTE lte : sensors.getLte())
            lte.setEquipamento(equipamento);
        for (Temperature temperature : sensors.getTemperature())
            temperature.setEquipamento(equipamento);
    }
}
