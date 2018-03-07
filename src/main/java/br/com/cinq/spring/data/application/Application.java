package br.com.cinq.spring.data.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Greeting Service. The ServletInitializer which it extends allows it to be deployed
 * as a WAR file within an existing WebServer
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "br.com.cinq.spring.data.*"})
@EntityScan(basePackages = { "br.com.cinq.spring.data.model" })
@EnableJpaRepositories(basePackages = {"br.com.cinq.spring.data.repository"})
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}
