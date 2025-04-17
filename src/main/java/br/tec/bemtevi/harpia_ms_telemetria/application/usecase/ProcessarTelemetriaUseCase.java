package br.tec.bemtevi.harpia_ms_telemetria.application.usecase;

import br.tec.bemtevi.harpia_ms_telemetria.domain.dto.TelemetryDto;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Equipamento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.GPS;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.LTE;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Temperature;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.GPSObserver;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.LTEObserver;
import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.TemperatureObserver;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.EquipamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProcessarTelemetriaUseCase {
    private final EquipamentoRepository equipamentoRepository;
    private final List<GPSObserver> gpsObserverList;
    private final List<LTEObserver> lteObserverList;
    private final List<TemperatureObserver> temperatureObserverList;

    public ProcessarTelemetriaUseCase(EquipamentoRepository equipamentoRepository,
                                      List<GPSObserver> gpsObserverList,
                                      List<LTEObserver> lteObserverList,
                                      List<TemperatureObserver> temperatureObserverList) {
        this.equipamentoRepository = equipamentoRepository;
        this.gpsObserverList = gpsObserverList;
        this.lteObserverList = lteObserverList;
        this.temperatureObserverList = temperatureObserverList;
    }

    @Transactional(rollbackFor = Exception.class)
    public void execute(TelemetryDto telemetryDto) {
        Equipamento equipamento = salvarEquipamento(telemetryDto);
        associarEquipamentoNosSensores(telemetryDto, equipamento);
        notificarObservers(telemetryDto);
    }

    private Equipamento salvarEquipamento(TelemetryDto telemetryDto) {
        Equipamento equipamento = new Equipamento(telemetryDto.getIdEquipamento());
        equipamento = equipamentoRepository.save(equipamento);
        return equipamento;
    }

    private void associarEquipamentoNosSensores(TelemetryDto telemetryDto, Equipamento equipamento) {
        associarEquipamentoNosSensoresDeGPS(telemetryDto, equipamento);
        associarEquipamentoNosSensoresDeLTE(telemetryDto, equipamento);
        associarEquipamentoNosSensoresDeTemperatura(telemetryDto, equipamento);
    }

    private void associarEquipamentoNosSensoresDeGPS(TelemetryDto telemetryDto, Equipamento equipamento) {
        for (GPS gps : telemetryDto.getSensorsDto().getGpsList())
            gps.associarEquipamento(equipamento);
    }

    private void associarEquipamentoNosSensoresDeLTE(TelemetryDto telemetryDto, Equipamento equipamento) {
        for (LTE lte : telemetryDto.getSensorsDto().getLteList())
            lte.associarEquipamento(equipamento);
    }

    private void associarEquipamentoNosSensoresDeTemperatura(TelemetryDto telemetryDto, Equipamento equipamento) {
        for (Temperature temperature : telemetryDto.getSensorsDto().getTemperatureList())
            temperature.associarEquipamento(equipamento);
    }

    private void notificarObservers(TelemetryDto telemetryDto) {
        notificarObserversGPS(telemetryDto.getSensorsDto().getGpsList());
        notificarObserversLTE(telemetryDto.getSensorsDto().getLteList());
        notificarObserversTemperature(telemetryDto.getSensorsDto().getTemperatureList());
    }

    private void notificarObserversGPS(List<GPS> gpsList) {
        for (GPSObserver gpsObserver : gpsObserverList)
            for (GPS gps : gpsList)
                gpsObserver.onEvent(gps);
    }

    private void notificarObserversLTE(List<LTE> lteList) {
        for (LTEObserver lteObserver : lteObserverList)
            for (LTE lte : lteList)
                lteObserver.onEvent(lte);
    }

    private void notificarObserversTemperature(List<Temperature> temperatureList) {
        for (TemperatureObserver temperatureObserver : temperatureObserverList)
            for (Temperature temperature : temperatureList)
                temperatureObserver.onEvent(temperature);
    }
}
