package br.com.cinq.spring.data.repository;

import java.util.List;

/**
 * Generic DAO interface. The most common operations were included even
 * though they won't be implemented in this solution.
 * @author Marcel
 *
 * @param <T>
 */
public interface GenericDAO<T> {
	
	public abstract List<T> getAll();
	public abstract T getById(long id);
	public abstract void insert(T object);
	public abstract void update(T object);
	public abstract void delete(long id);

}
