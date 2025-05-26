package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.mapper;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Equipamento;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Instituicao;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.dispositivo.*;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia.HarpiaTelemetryMessage;
import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia.dispositivo.*;
import org.springframework.stereotype.Component;

@Component
public class HarpiaDispositivoMapper {
    public Dispositivo fromHarpiaDevice(HarpiaTelemetryMessage harpiaTelemetryMessage) {
        Tempo tempo = fromHarpiaTime(harpiaTelemetryMessage.getDevice().getTime());
        Cpu cpu = fromHarpiaCpu(harpiaTelemetryMessage.getDevice().getCpu());
        Memoria memoria = fromHarpiaMemoria(harpiaTelemetryMessage.getDevice().getMemory());
        Gpu gpu = fromHarpiaGpu(harpiaTelemetryMessage.getDevice().getGpu());
        Disco disco = fromHarpiaDisk(harpiaTelemetryMessage.getDevice().getDisk());
        TemperaturaDispositivo temperatura = fromHarpiaDeviceTemperature(harpiaTelemetryMessage.getDevice().getTemperature());
        Fan fan = fromHarpiaFan(harpiaTelemetryMessage.getDevice().getFan());
        Power power = fromHarpiaPower(harpiaTelemetryMessage.getDevice().getPower());
        return new Dispositivo(null,
                harpiaTelemetryMessage.getInstitutionId(),
                harpiaTelemetryMessage.getSerial(),
                tempo,
                cpu,
                memoria,
                gpu,
                disco,
                temperatura,
                fan,
                power);
    }

    private Tempo fromHarpiaTime(HarpiaTime time) {
        return new Tempo(time.getTimestamp(), time.getUptime());
    }

    private Cpu fromHarpiaCpu(HarpiaCpu cpu) {
        return new Cpu(cpu.getCpu1(), cpu.getCpu2(), cpu.getCpu3(), cpu.getCpu4(), cpu.getCpu5(), cpu.getCpu6());
    }

    private Memoria fromHarpiaMemoria(HarpiaMemory memory) {
        return new Memoria(memory.getRam(), memory.getSwap(), memory.getEmc());
    }

    private Gpu fromHarpiaGpu(HarpiaGpu gpu) {
        return new Gpu(gpu.getGpuUsage());
    }

    private Disco fromHarpiaDisk(HarpiaDisk disk) {
        return new Disco(disk.getTotal(), disk.getUsed(), disk.getFree(), disk.getPercent());
    }

    private TemperaturaDispositivo fromHarpiaDeviceTemperature(HarpiaTemperaturaDispositivo temperature) {
        return new TemperaturaDispositivo(temperature.getGpu(),
                temperature.getCpu(),
                temperature.getCv0(),
                temperature.getCv1(),
                temperature.getCv2(),
                temperature.getSoc0(),
                temperature.getSoc1(),
                temperature.getSoc2(),
                temperature.getTj());
    }

    private Fan fromHarpiaFan(HarpiaFan fan) {
        return new Fan(fan.getPwmfan0());
    }

    private Power fromHarpiaPower(HarpiaPower power) {
        return new Power(power.getVddCpuGpuCd(), power.getVddSoc(), power.getTotal());
    }
}
