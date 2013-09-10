package com.blank.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.blank.util.enumerator.UserAdminStatusEnum;
import com.blank.util.enumerator.UserAdminTypeEnum;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Entity
public class UserAdmin implements Serializable{

	private static final long serialVersionUID = 8524555599797676963L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;
	
	private String name;

	private String email;

	@Enumerated(EnumType.STRING)
	private UserAdminStatusEnum status = UserAdminStatusEnum.ENABLED;
	
	@Enumerated(EnumType.STRING)
	private UserAdminTypeEnum type;
	
	private Date createdAt = new Date();

	private Date updatedAt = new Date();
	
	

	public UserAdmin(String name, String email, UserAdminTypeEnum type) {
		super();
		this.name = name;
		this.email = email;
		this.type = type;
	}
	

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserAdminStatusEnum getStatus() {
		return status;
	}

	public void setStatus(UserAdminStatusEnum status) {
		this.status = status;
	}

	public UserAdminTypeEnum getType() {
		return type;
	}

	public void setType(UserAdminTypeEnum type) {
		this.type = type;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setId(Long id){
		if(id!=null){
			key = KeyFactory.createKey(UserAdmin.class.getSimpleName(), id);
		}
	}


	public Long getId() {
		return key!=null ? key.getId() : null;
	}
	
	
	
	
	
	
}
