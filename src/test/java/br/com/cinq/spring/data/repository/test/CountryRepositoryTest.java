package br.com.cinq.spring.data.repository.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cinq.spring.data.application.Application;
import br.com.cinq.spring.data.model.Country;
import br.com.cinq.spring.data.repository.CountryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("unit")
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;
    
    @Test
    public void testCountryRepositoryConstruction() {
    	 Assert.assertNotNull(countryRepository); //Asserts the 'successfulness' of the injection
         Assert.assertTrue(countryRepository.count()>0); //Asserts that at least one entity is available
    }

    @Test
    public void testGetAllCountries() {
    	
    	long totalOfCountries = countryRepository.count();
        List<Country> countries = countryRepository.findAll();
        Assert.assertEquals((int) totalOfCountries, countries.size());
        
    }

    @Test
    public void testFindByNameIgnoreCaseContaining() {

        List<Country> countries = countryRepository.findByNameIgnoreCaseContaining("Fra");
        int expectedNumberOfCountries = 1;
        
        Assert.assertEquals(expectedNumberOfCountries, countries.size());

    }

}
