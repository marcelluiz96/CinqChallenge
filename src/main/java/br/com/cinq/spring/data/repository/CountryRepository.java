package br.com.cinq.spring.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cinq.spring.data.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    List<Country> findByNameIgnoreCaseContaining(String cityName);
}
