package com.blank.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blank.entity.UserAdmin;
import com.blank.util.database.StringQueryBuilder;
import com.blank.util.enumerator.UserAdminStatusEnum;


@Repository
public class UserAdminDao extends GenericDao<UserAdmin> {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<UserAdmin> find(String email,UserAdminStatusEnum status, Integer firstResult, Integer maxResults) throws Exception{
		StringQueryBuilder sQuery = new StringQueryBuilder("select u from UserAdmin u");
		Query query = query(sQuery, email, status, firstResult, maxResults);
		return query.getResultList();
	}
	
	@Transactional(readOnly=true)
	public Long count(String email,UserAdminStatusEnum status) throws Exception{
		StringQueryBuilder sQuery = new StringQueryBuilder("select count(u.key) from UserAdmin u");
		Query query = query(sQuery, email, status, null, null);
		return (Long) query.getSingleResult();
	}
	
	public Query query(StringQueryBuilder sQuery,String email,UserAdminStatusEnum status, Integer firstResult, Integer maxResults)throws Exception{
		if(StringUtils.isNotBlank(email)){
			sQuery.appendParam("u.email=:email");
		}
		if(status!=null){
			sQuery.appendParam("u.status=:status");
		}
		Query query = em.createQuery(sQuery.toString());
		
		if(StringUtils.isNotBlank(email)){
			query.setParameter("email", email);
		}
		if(status!=null){
			query.setParameter("status", status);
		}
		if(firstResult!=null){
			query.setFirstResult(firstResult-1);
		}
		if(maxResults!=null){
			query.setMaxResults(maxResults);
		}
		return query;
	}
	

	
}