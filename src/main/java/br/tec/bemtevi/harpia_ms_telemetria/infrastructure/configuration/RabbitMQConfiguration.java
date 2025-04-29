package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    private final String msTelemetriaQueueName;

    public RabbitMQConfiguration(@Value("${ms-telemetria.queue.name}") String msTelemetriaQueueName) {
        this.msTelemetriaQueueName = msTelemetriaQueueName;
    }

    @Bean
    Queue queue() {
        return new Queue(msTelemetriaQueueName, true, false, false);
    }
}
