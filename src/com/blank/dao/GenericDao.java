package com.blank.dao;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly=true)
public class GenericDao<T>  {
	
	protected EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@Transactional
	public void insert(T entity) {
		em.persist(entity);
	}

	@Transactional
	public T update(T entity) {
		return (T) em.merge(entity);
	}
	
	@Transactional
	public void remove( Object key) {
		T entity = findById( key);
		em.remove(entity);
	}

	@Transactional
	public void remove( Integer[] keys) {
		for(int i = 0; i < keys.length; i++) {
			Integer id = keys[i];
			this.remove(id);
		}
	}

	@SuppressWarnings("unchecked")
	public T findById(Object key) {
		try {
			Class<T> cls = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			return (T) em.find(cls, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
