package br.com.cinq.spring.data.repository.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cinq.spring.data.application.Application;
import br.com.cinq.spring.data.model.City;
import br.com.cinq.spring.data.model.Country;
import br.com.cinq.spring.data.repository.CityRepository;

/**
 * @author Cinq Technologies, Marcel
 * This class was 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class,webEnvironment=WebEnvironment.DEFINED_PORT)
@ActiveProfiles("unit")
public class CityRepositoryTest {

	@Autowired
	private CityRepository cityRepository;

	@Test
	public void testCityRepositoryConstruction() {
		Assert.assertNotNull(cityRepository); //Asserts the 'successfulness' of the injection
		Assert.assertTrue(cityRepository.count()>0); //Asserts that at least one entity is available
	}

	@Test
	public void testFindCityById() {
		//Given
		int cityId = 82;
		String expectedCityName = "Curitiba";

		//When
		Optional<City> foundCityOptional = cityRepository.findById(cityId);

		//Then
		Assert.assertEquals(true, foundCityOptional.isPresent());

		String foundCityName = foundCityOptional.get().getName();
		Assert.assertEquals(expectedCityName, foundCityName);

		foundCityOptional
		.ifPresent(foundCity -> assertEquals(cityId, foundCity.getId())); // Just presenting some Java 8 features

	}

	@Test
	public void testFindCityByCountry() {
		Country country = new Country();
		country.setId(3); // Should be France

		List<City> foundContries = cityRepository.findByCountry(country);

		//KNOWN error here: The insertion of the endpoint test is not rolling back. Thus, this assertion
		//will sometimes fail. Will register an issue
		Assert.assertEquals(2, foundContries.size()); 
	}

	@Test
	public void testFindByCountryNameIgnoreCaseContaining() {
		String fullCountryName = "United States";
		int expectedResultSize = 3;

		List<City> foundContries = cityRepository.findByCountryNameIgnoreCaseContaining(fullCountryName);
		Assert.assertEquals(expectedResultSize, foundContries.size());

		String partialCountryName = fullCountryName.split(" ")[0]; 
		foundContries = cityRepository.findByCountryNameIgnoreCaseContaining(partialCountryName);
		Assert.assertEquals(expectedResultSize, foundContries.size());
	}
}
