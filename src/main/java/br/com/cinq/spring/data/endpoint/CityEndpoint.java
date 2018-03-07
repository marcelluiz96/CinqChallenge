package br.com.cinq.spring.data.endpoint;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cinq.spring.data.model.City;
import br.com.cinq.spring.data.repository.CityRepository;

@Component
@Path("/cities")
public class CityEndpoint {

	@Autowired
	private CityRepository cityRepository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{cityid}")
	public Response findCityById(@PathParam("cityid") int cityId) {
		Optional<City> requestedCity = cityRepository.findById(cityId);
		
		if (requestedCity.isPresent())
			return Response.ok(requestedCity.get()).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}


	/**
	 * Lists all cities, or those matching the parameters
	 * @param countryName - Optional
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listCities(@QueryParam("country") String countryName) {
		
		if (countryName == null || countryName.isEmpty()) { 
			List<City> allCities = cityRepository.findAll();
			return Response
					.ok(allCities)
					.build();
		} else { 
			List<City> citiesSearchResult = cityRepository.findByCountryNameIgnoreCaseContaining(countryName);
			return Response
					.ok(citiesSearchResult)
					.build();
		}
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.TEXT_PLAIN)
	@Transactional
	@Path("/insertCities")
	public Response ingestAndInsertCities(List<City> cities) {
		
		cities.stream()
			.parallel() 								 
			.forEach(city -> cityRepository.save(city));
		cityRepository.flush();
		
		return Response.ok("All insertions were completed").build();
	}
	
}
