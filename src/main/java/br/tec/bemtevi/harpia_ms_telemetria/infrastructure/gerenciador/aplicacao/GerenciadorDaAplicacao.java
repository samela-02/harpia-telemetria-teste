package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.gerenciador.aplicacao;

import br.tec.bemtevi.harpia_ms_telemetria.domain.facade.LoggerFacade;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class GerenciadorDaAplicacao {
    private static final int MAXIMO_TENTATIVAS = 5;

    private final LoggerFacade loggerFacade;
    private final ApplicationContext applicationContext;
    private final InformacoesDoShutdown informacoesDoShutdown;

    public GerenciadorDaAplicacao(LoggerFacade loggerFacade,
                                  ApplicationContext applicationContext) {
        this.loggerFacade = loggerFacade;
        this.applicationContext = applicationContext;
        this.informacoesDoShutdown = InformacoesDoShutdown.novaInstanciaZerada();
        adicionarLimpadorDaContagem();
    }

    public void tentarDesligarAAplicacao() {
        loggerFacade.info("Tentativa de shutdown da aplicação recebida.");
        informacoesDoShutdown.incrementarTentativa();
        informacoesDoShutdown.atualizarDataDaUltimaTentativa();
        loggerFacade.info(String.format("Quantidade de tentativas realizadas: %s.", informacoesDoShutdown.getTentativas()));
        if (informacoesDoShutdown.getTentativas() == MAXIMO_TENTATIVAS) {
            shutdown();
            return;
        }
        loggerFacade.info("Quantidade máxima de tentativas não atingida. A aplicação não será desligada.");
    }

    protected void shutdown() {
        loggerFacade.info("A quantidade máxima de tentativas foi atingida. A aplicação será desligada.");
        try {
            ((ConfigurableApplicationContext) applicationContext).close();
        } catch (Exception e) {
            loggerFacade.error(String.format("Não foi possível desligar a aplicação pelo contexto do spring: %s.", e.getMessage()));
            loggerFacade.info("Desligando a aplicação via jvm.");
            System.exit(1);
        }
    }

    private void adicionarLimpadorDaContagem() {
        Runnable limpadorDaContagem = () -> {
            if (informacoesDoShutdown.getUltimaTentativa() != null &&
                    informacoesDoShutdown.getUltimaTentativa().plusHours(1).isBefore(LocalDateTime.now())) {
                loggerFacade.info("Mais de 1 hora se passou desde a última solicitação de desligamento. As tentativas serão resetadas.");
                informacoesDoShutdown.zerarEstado();
            }
            else {
                try {
                    Thread.sleep(1000 * 60 * 5);
                } catch (InterruptedException e) {
                    throw new RuntimeException("Erro ao executar o sleep.", e);
                }
            }
        };
        Thread thread = new Thread(limpadorDaContagem);
        thread.start();
    }
}
