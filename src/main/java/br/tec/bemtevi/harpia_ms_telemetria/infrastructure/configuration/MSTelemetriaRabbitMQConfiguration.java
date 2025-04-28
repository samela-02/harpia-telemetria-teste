package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MSTelemetriaRabbitMQConfiguration {
    private final String msTelemetriaQueueName;
    private final String msExchageName;
    private final String telemetriaRoutingKey;

    public MSTelemetriaRabbitMQConfiguration(@Value("${ms-telemetria.queue.name}") String msTelemetriaQueueName,
                                          @Value("${ms.exchange.name}") String msExchageName,
                                          @Value("${telemetria.routing-key}") String TelemetriaRoutingKey) {
        this.msTelemetriaQueueName = msTelemetriaQueueName;
        this.msExchageName = msExchageName;
        this.telemetriaRoutingKey = TelemetriaRoutingKey;
    }

    @Bean
    Queue msComandoQueue() {
        return new Queue(msTelemetriaQueueName, true, false, false);
    }

    @Bean
    DirectExchange exchangeOut() {
        return new DirectExchange(msExchageName, true, false);
    }

    @Bean
    Binding msComandoQueueBinding() {
        return BindingBuilder
                .bind(msComandoQueue())
                .to(exchangeOut())
                .with(telemetriaRoutingKey);
    }
}
