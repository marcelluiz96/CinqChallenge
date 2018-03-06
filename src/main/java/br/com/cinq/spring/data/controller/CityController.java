package br.com.cinq.spring.data.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import br.com.cinq.spring.data.model.City;
import br.com.cinq.spring.data.service.CityService;

@Component
@Path("/cities")
public class CityController {
	private CityService cityService;

	public CityController(CityService cityService) {
		this.cityService = cityService;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<City> getAllCities() {
		return cityService.getAll();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{cityid}")
	public City getBook(@PathParam("cityid") Long cityId) {
			return cityService.getById(cityId);
	}



}
