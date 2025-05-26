package br.tec.bemtevi.harpia_ms_telemetria.domain.model.dispositivo;

public class Power {
    private Double vlVddCpuGpuCd;
    private Double vlVddSoc;
    private Double vlTotal;

    public Power() {
    }

    public Power(Double vlVddCpuGpuCd, Double vlVddSoc, Double vlTotal) {
        this.vlVddCpuGpuCd = vlVddCpuGpuCd;
        this.vlVddSoc = vlVddSoc;
        this.vlTotal = vlTotal;
    }

    public Double getVlVddCpuGpuCd() {
        return vlVddCpuGpuCd;
    }

    public Double getVlVddSoc() {
        return vlVddSoc;
    }

    public Double getVlTotal() {
        return vlTotal;
    }
}
