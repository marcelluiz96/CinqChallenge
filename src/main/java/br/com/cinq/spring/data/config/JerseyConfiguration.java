package br.com.cinq.spring.data.config;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import br.com.cinq.spring.data.endpoint.CityEndpoint;

@Component
@ApplicationPath("rest")
public class JerseyConfiguration extends ResourceConfig{

	@PostConstruct
	public void setUp() {
		register(CityEndpoint.class);
	}
}
