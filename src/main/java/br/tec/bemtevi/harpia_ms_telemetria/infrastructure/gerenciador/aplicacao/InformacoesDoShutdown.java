package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.gerenciador.aplicacao;

import java.time.LocalDateTime;

class InformacoesDoShutdown {
    private int tentativas;
    private LocalDateTime ultimaTentativa;

    private InformacoesDoShutdown(int tentativas, LocalDateTime ultimaTentativa) {
        this.tentativas = tentativas;
        this.ultimaTentativa = ultimaTentativa;
    }

    public static InformacoesDoShutdown novaInstanciaZerada() {
        return new InformacoesDoShutdown(0, null);
    }

    public int getTentativas() {
        return tentativas;
    }

    public void incrementarTentativa() {
        tentativas += 1;
    }

    public LocalDateTime getUltimaTentativa() {
        return ultimaTentativa;
    }

    public void atualizarDataDaUltimaTentativa() {
        ultimaTentativa = LocalDateTime.now();
    }

    public void zerarEstado() {
        tentativas = 0;
        ultimaTentativa = null;
    }
}
