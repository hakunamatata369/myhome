package org.hakunamatata.myhome.interfaces;

import java.util.List;

public interface iDao<T> {

	void save(T entity);

	public void update(T entity);

	public T getById(long id);

	public void delete(T entity);

	public List<T> getAll();

	public void deleteAll();
}