package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.input.mensageria;

import br.tec.bemtevi.harpia_ms_telemetria.domain.observer.Observer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQTelemetriaConsumer {
    private final Observer observer;

    public RabbitMQTelemetriaConsumer(@Qualifier(value = "TelemetriaObserver") Observer observer) {
        this.observer = observer;
    }

    @RabbitListener(queues = "${rabbitmq.ms.queue.name}")
    public void consume(@Payload byte[] mensagem) {
        observer.onEvent(mensagem);
    }
}
