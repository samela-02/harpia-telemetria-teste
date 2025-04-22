package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.observer;

import br.tec.bemtevi.harpia_ms_telemetria.application.usecase.ProcessarTelemetriaUseCase;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.*;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.HarpiaTelemetryMessage;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.anticorruptionlayer.HarpiaAntiCorruptionLayer;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.facade.SerializationFacade;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.storage.EquipamentoStorage;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.storage.InstituicaoStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(value = "TelemetriaObserver")
public class TelemetriaObserver implements Observer {
    private static final Logger log = LoggerFactory.getLogger(TelemetriaObserver.class);
    private final SerializationFacade serializationFacade;
    private final HarpiaAntiCorruptionLayer harpiaAntiCorruptionLayer;
    private final InstituicaoStorage instituicaoStorage;
    private final EquipamentoStorage equipamentoStorage;
    private final ProcessarTelemetriaUseCase processarTelemetriaUseCase;

    public TelemetriaObserver(SerializationFacade serializationFacade,
                              HarpiaAntiCorruptionLayer harpiaAntiCorruptionLayer,
                              InstituicaoStorage instituicaoStorage,
                              EquipamentoStorage equipamentoStorage,
                              ProcessarTelemetriaUseCase processarTelemetriaUseCase) {
        this.serializationFacade = serializationFacade;
        this.harpiaAntiCorruptionLayer = harpiaAntiCorruptionLayer;
        this.instituicaoStorage = instituicaoStorage;
        this.equipamentoStorage = equipamentoStorage;
        this.processarTelemetriaUseCase = processarTelemetriaUseCase;
    }

    @Override
    public void onEvent(Object object) {
        log.info("Notificação de mensagem recebida.");
        byte[] mensagem = (byte[]) object;
        HarpiaTelemetryMessage harpiaTelemetryMessage = serializationFacade
                .fromSnakeCaseBytes(mensagem, HarpiaTelemetryMessage.class);
        Sensors sensors = harpiaAntiCorruptionLayer.fromHarpiaTelemetryMessage(harpiaTelemetryMessage);
        Instituicao instituicao = instituicaoStorage.getInstance(sensors.getIdInstituicao());
        associarSensoresAInstituicao(sensors, instituicao);
        Equipamento equipamento = equipamentoStorage.getInstance(sensors.getIdEquipamento());
        associarSensoresAoEquipamento(sensors, equipamento);
        processarTelemetriaUseCase.execute(sensors);
        log.info("Mensagem processada com sucesso.");
    }

    private void associarSensoresAInstituicao(Sensors sensors, Instituicao instituicao) {
        for (GPS gps : sensors.getGps())
            gps.setInstituicao(instituicao);
        for (LTE lte : sensors.getLte())
            lte.setInstituicao(instituicao);
        for (Temperature temperature : sensors.getTemperature())
            temperature.setInstituicao(instituicao);
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
