package br.com.cinq.spring.data.service;

import java.util.List;

public interface GenericService<T> {
	
	public abstract List<T> getAll();
	public abstract T getById(long id);
	public abstract void insert(T object);
	public abstract void update(T object);
	public abstract void delete(long id);

}

