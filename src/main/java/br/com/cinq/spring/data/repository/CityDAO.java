package br.com.cinq.spring.data.repository;

import java.util.List;

import br.com.cinq.spring.data.model.City;

public class CityDAO implements GenericDAO<City>{

	public List<City> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public City getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/** Clean code rules are clearly being broken by leaving
	 * useless code below. But its just for the sake of illustrating
	 * a fully operational DAO.
	 */
	public void insert(City object) {
		// Not needed for the solution
	}
	public void update(City object) {
		// Not needed for the solution
	}
	public void delete(long id) {
		// Not needed for the solution
	}

}
