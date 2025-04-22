package br.tec.bemtevi.harpia_ms_telemetria.application.usecase;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Sensors;
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
    public void execute(Sensors sensors) {
//        Equipamento equipamento = salvarEquipamento(harpiaTelemetryMessage);
//        associarEquipamentoNosSensores(harpiaTelemetryMessage, equipamento);
//        notificarObservers(harpiaTelemetryMessage);
    }

//    private Equipamento salvarEquipamento(HarpiaTelemetryMessage harpiaTelemetryMessage) {
//        Equipamento equipamento = new Equipamento(harpiaTelemetryMessage.getSerial());
//        equipamento = equipamentoRepository.save(equipamento);
//        return equipamento;
//    }
//
//    private void associarEquipamentoNosSensores(HarpiaTelemetryMessage harpiaTelemetryMessage, Equipamento equipamento) {
//        associarEquipamentoNosSensoresDeGPS(harpiaTelemetryMessage, equipamento);
//        associarEquipamentoNosSensoresDeLTE(harpiaTelemetryMessage, equipamento);
//        associarEquipamentoNosSensoresDeTemperatura(harpiaTelemetryMessage, equipamento);
//    }
//
//    private void associarEquipamentoNosSensoresDeGPS(HarpiaTelemetryMessage harpiaTelemetryMessage, Equipamento equipamento) {
//        for (GPS gps : harpiaTelemetryMessage.getSensors().getGpsList())
//            gps.associarEquipamento(equipamento);
//    }
//
//    private void associarEquipamentoNosSensoresDeLTE(HarpiaTelemetryMessage harpiaTelemetryMessage, Equipamento equipamento) {
//        for (LTE lte : harpiaTelemetryMessage.getSensors().getLteList())
//            lte.associarEquipamento(equipamento);
//    }
//
//    private void associarEquipamentoNosSensoresDeTemperatura(HarpiaTelemetryMessage harpiaTelemetryMessage, Equipamento equipamento) {
//        for (Temperature temperature : harpiaTelemetryMessage.getSensors().getTemperatureList())
//            temperature.associarEquipamento(equipamento);
//    }
//
//    private void notificarObservers(HarpiaTelemetryMessage harpiaTelemetryMessage) {
//        notificarObserversGPS(harpiaTelemetryMessage.getSensors().getGpsList());
//        notificarObserversLTE(harpiaTelemetryMessage.getSensors().getLteList());
//        notificarObserversTemperature(harpiaTelemetryMessage.getSensors().getTemperatureList());
//    }
//
//    private void notificarObserversGPS(List<GPS> gpsList) {
//        for (GPSObserver gpsObserver : gpsObserverList)
//            for (GPS gps : gpsList)
//                gpsObserver.onEvent(gps);
//    }
//
//    private void notificarObserversLTE(List<LTE> lteList) {
//        for (LTEObserver lteObserver : lteObserverList)
//            for (LTE lte : lteList)
//                lteObserver.onEvent(lte);
//    }
//
//    private void notificarObserversTemperature(List<Temperature> temperatureList) {
//        for (TemperatureObserver temperatureObserver : temperatureObserverList)
//            for (Temperature temperature : temperatureList)
//                temperatureObserver.onEvent(temperature);
//    }
}
