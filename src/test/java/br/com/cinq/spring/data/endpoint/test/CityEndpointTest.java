package br.com.cinq.spring.data.endpoint.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.google.gson.Gson;

import br.com.cinq.spring.data.application.Application;
import br.com.cinq.spring.data.model.City;
import br.com.cinq.spring.data.model.Country;
import br.com.cinq.spring.data.repository.CountryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class,webEnvironment=WebEnvironment.DEFINED_PORT)
@ActiveProfiles("unit")
public class CityEndpointTest extends AbstractTransactionalJUnit4SpringContextTests{
	Logger logger = LoggerFactory.getLogger(CityEndpointTest.class);

	
	@Autowired
	private WebTestClient webClient;
	
	@Autowired CountryRepository countryRepository;
	
	
	private static String cityEndpointUri;
	
	@BeforeClass
	public static void setUpClass() {
		cityEndpointUri = "/rest/cities";
	}

	@Test
	public void testFindCityByIdOnExistingId() {
		//given
		int testId = 82;
		String findCityByIdUri = cityEndpointUri + "/" + String.valueOf(testId); 
		
		//then
		this.webClient.get().uri(findCityByIdUri).exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBody()
		.jsonPath("$.id").isEqualTo(testId);
	}
	
	
	@Test
	public void testFindCityByIdOnNonExistingId() {
		//given
		int testId = 2432;
		String findCityByIdURI = cityEndpointUri + "/" + String.valueOf(testId); 
		
		//then
		this.webClient
		.get().uri(findCityByIdURI).exchange()
		.expectStatus().isNotFound(); //The test's main focus is here
	}
	
	@Test
	public void testListCities() {
		
		this.webClient.get().uri(this.cityEndpointUri)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBody().jsonPath("$").isArray();
	}
	
	@Test
	public void testListCitiesWithCountryNameContaining() {
		String query = "?country=Uni";
		String uri = this.cityEndpointUri + query;
				
		this.webClient.get().uri(uri)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBody().jsonPath("$").isArray();
		
		//Room for improvement: Verify the size of the received array
	}
	
	
	@Test
	@Rollback(true)
	public void testIngestAndInsertCitiesInsertMultipleValid() {
		String ingestAndInsertUri = this.cityEndpointUri +"/"+ "insertCities";
		
		
		//Given
		Gson gson = new Gson();
		List<City> citiesToBeInserted = new ArrayList<City>();
		
		//TODO Extract these into a mock List
		Country firstCountry = countryRepository.findById(1).get(); //Brazil
		
		City firstCity = new City();
		firstCity.setCountry(firstCountry);
		firstCity.setName("Moscow");
		
		citiesToBeInserted.add(firstCity);
		
		Country secondCountry = countryRepository.findById(3).get(); //France
		secondCountry.setName("Ukraine");
		
		City secondCity = new City();
		secondCity.setCountry(secondCountry);
		secondCity.setName("Kiev");
		
		citiesToBeInserted.add(secondCity);
		
		gson.toJson(citiesToBeInserted);
		
		
		this.webClient.post().uri(ingestAndInsertUri)
		.contentType(MediaType.APPLICATION_JSON)
		.syncBody(citiesToBeInserted)
		.exchange()
		.expectStatus().isCreated();
	}


}
