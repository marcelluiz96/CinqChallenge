package br.com.cinq.spring.data.endpoint.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestTemplate;

import br.com.cinq.spring.data.application.Application;
import br.com.cinq.spring.data.endpoint.CityEndpoint;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment=WebEnvironment.RANDOM_PORT)
@ActiveProfiles("default")
public class CityEndpointTest {
	Logger logger = LoggerFactory.getLogger(CityEndpointTest.class);

	@Autowired
	private ApplicationContext context;
	
	private WebTestClient webClient;
	
	@Before
	public void setUp() {
		webClient = WebTestClient.bindToApplicationContext(context).build();
	}
	

	@Test
	public void testFindCityById() {
		//given
		int testId = 82;
		String testUri = "/" + String.valueOf(testId); 
		
		//then
		this.webClient.get().uri(testUri).exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBody()
		.jsonPath("$.id").isEqualTo(testId);
	}

	@Test
	public void testListCities() {

	}

	@Test
	public void testListCitiesWithFilter() {

	}

	@Test
	public void testIngestAndInsertCities() {

	}

}
