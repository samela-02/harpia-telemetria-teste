package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.dto.harpia.dispositivo;

public class HarpiaPower {
    private Double vddCpuGpuCd;
    private Double vddSoc;
    private Double total;

    public HarpiaPower() {
    }

    public HarpiaPower(Double vddCpuGpuCd, Double vddSoc, Double total) {
        this.vddCpuGpuCd = vddCpuGpuCd;
        this.vddSoc = vddSoc;
        this.total = total;
    }

    public Double getVddCpuGpuCd() {
        return vddCpuGpuCd;
    }

    public Double getVddSoc() {
        return vddSoc;
    }

    public Double getTotal() {
        return total;
    }
}
