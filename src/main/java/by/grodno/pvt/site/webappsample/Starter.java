package by.grodno.pvt.site.webappsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import by.grodno.pvt.site.webappsample.config.EmailProperties;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(EmailProperties.class)
public class Starter {

	public static void main(String[] args) {
		SpringApplication.run(Starter.class, args);
	}
}
