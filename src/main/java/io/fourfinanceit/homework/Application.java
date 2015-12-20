package io.fourfinanceit.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"io.fourfinanceit.homework.repositories"})
@EntityScan(basePackages = "io.fourfinanceit.homework.models")
@EnableTransactionManagement
@ComponentScan(basePackages = {"io.fourfinanceit.homework"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
