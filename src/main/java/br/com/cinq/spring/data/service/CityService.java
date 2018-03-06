package br.com.cinq.spring.data.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cinq.spring.data.model.City;
import br.com.cinq.spring.data.repository.CityDAO;


public class CityService implements GenericService<City> {
	
	@Autowired
	private CityDAO cityDAO;

	public List<City> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public City getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insert(City object) {
		// TODO Auto-generated method stub
		
	}

	public void update(City object) {
		// TODO Auto-generated method stub
		
	}

	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

}
