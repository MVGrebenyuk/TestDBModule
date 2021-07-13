package ru.neoflex.msa.testKafkaModule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;


@EnableConfigurationProperties
@SpringBootApplication
public class TestKafkaModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestKafkaModuleApplication.class, args);
	}

}
