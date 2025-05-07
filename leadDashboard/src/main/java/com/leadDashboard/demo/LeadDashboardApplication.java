package com.leadDashboard.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.leadDashboard")
@EntityScan("com.leadDashboard..model")
@EnableJpaRepositories("com.leadDashboard.repository")
public class LeadDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeadDashboardApplication.class, args);
	}

}
