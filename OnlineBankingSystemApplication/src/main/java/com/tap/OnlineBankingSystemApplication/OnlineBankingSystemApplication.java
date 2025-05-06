package com.tap.OnlineBankingSystemApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tap"})
@EnableJpaRepositories(basePackages = {"com.tap.repository"})
@EntityScan(basePackages = {"com.tap.model"})
public class OnlineBankingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBankingSystemApplication.class, args);
	}

}
