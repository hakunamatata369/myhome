package org.hakunamatata.myhome.interfaces;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hakunamatata.myhome.database.HibernateUtil;
import org.hakunamatata.myhome.exception.DataNotFoundException;

public interface iDao<T> {

	default T save(T entity){
		try {
			HibernateUtil.getCurrentSession().save(entity);
		} catch (PersistenceException e) {
			throw new PersistenceException(e.getMessage());
		}
		return entity;
	}

	default T update(T entity) {
		try {
			HibernateUtil.getCurrentSession().update(entity);
		} catch (PersistenceException e) {
			throw new PersistenceException(e.getMessage());
		}
		return entity;
	}

	default T getById(Class<T> entityClass, long id){
		T entity = (T) HibernateUtil.getCurrentSession().get(entityClass, id);
		if (entity == null) {
			throw new DataNotFoundException("Node with id : " + id + " is not available");
		}
		return entity;
	}

	default void delete(T entity) {
		HibernateUtil.getCurrentSession().delete(entity);
	}

	default List<T> getAll(String hql){
		@SuppressWarnings("unchecked")
		List<T> entities = (List<T>) HibernateUtil.getCurrentSession().createQuery(hql).list();
		return entities;
	}

	default void deleteAll(String hql){
		List<T> entityList = getAll(hql);
		for (T entity : entityList) {
			delete(entity);
		}
	}
}