package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input;

import br.tec.bemtevi.harpia_ms_telemetria.infrastructure.observer.TelemetriaObserver;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQTelemetriaConsumer {
    private final TelemetriaObserver telemetriaObserver;

    public RabbitMQTelemetriaConsumer(TelemetriaObserver telemetriaObserver) {
        this.telemetriaObserver = telemetriaObserver;
    }

    @RabbitListener(queues = "${rabbitmq.ms.queue.name}")
    public void consume(@Payload byte[] mensagem) {
        telemetriaObserver.onEvent(mensagem);
    }
}
