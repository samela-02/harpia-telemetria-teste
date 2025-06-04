package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.gerenciador.aplicacao;

import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.LoggerFacade;
import org.springframework.context.ApplicationContext;

public class GerenciadorDaAplicacaoFake extends GerenciadorDaAplicacao {
    private boolean desligou = false;

    public GerenciadorDaAplicacaoFake(LoggerFacade loggerFacade, ApplicationContext applicationContext) {
        super(loggerFacade, applicationContext);
    }

    @Override
    protected void shutdown() {
        desligou = true;
    }
}