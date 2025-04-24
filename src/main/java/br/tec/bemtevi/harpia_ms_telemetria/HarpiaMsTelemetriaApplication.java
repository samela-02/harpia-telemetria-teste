package br.tec.bemtevi.harpia_ms_telemetria;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class HarpiaMsTelemetriaApplication {
	public static void main(String[] args) {
		SpringApplication.run(HarpiaMsTelemetriaApplication.class, args);
	}
}
