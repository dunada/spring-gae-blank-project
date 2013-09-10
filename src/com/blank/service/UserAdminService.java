package com.blank.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blank.dao.UserAdminDao;
import com.blank.entity.UserAdmin;
import com.blank.util.enumerator.UserAdminStatusEnum;
import com.blank.util.enumerator.UserAdminTypeEnum;
import com.blank.util.exception.ServiceException;
import com.blank.util.exception.ValidationException;


@Service
public class UserAdminService {

	private static final Logger log = Logger.getLogger(UserAdminService.class.getName());
	
	@Autowired
	private UserAdminDao userAdminDao;
	
	public List<UserAdmin> find(String email,UserAdminStatusEnum status, Integer firstResult, Integer maxResults) throws ServiceException{
		try {
			return userAdminDao.find(email, status,firstResult,maxResults);
		}catch (Exception e) {
			log.log(Level.SEVERE, "Operation failed...", e);
			throw new ServiceException(e);
		}
	}
	
	public Long count(String email, UserAdminStatusEnum status) throws ServiceException {
		try {
			return userAdminDao.count(email, status);
		}catch (Exception e) {
			log.log(Level.SEVERE, "Operation failed...", e);
			throw new ServiceException(e);
		}
	}
	
	
	public UserAdmin getByEmailStatus(String email,UserAdminStatusEnum status) throws ServiceException{
		UserAdmin userAdmin =null;
		List<UserAdmin> userAdmins = find(email,status,null,1);
		if(!userAdmins.isEmpty()){
			userAdmin=userAdmins.get(0);
		}
		return userAdmin;
	}
	
	public UserAdmin get(Long key) throws ServiceException{
		try {
			return userAdminDao.findById(key);
		}catch (Exception e) {
			log.log(Level.SEVERE, "Operation failed...", e);
			throw new ServiceException(e);
		}
	}
	
	public void remove(Long key) throws ServiceException{
		try {
			userAdminDao.remove(key);
		}catch (Exception e) {
			log.log(Level.SEVERE, "Operation failed...", e);
			throw new ServiceException(e);
		}
	}


	public void startDb()  throws ServiceException{
		try {
			List<UserAdmin> adminUsers = new ArrayList<UserAdmin>();
			
			adminUsers.add(new UserAdmin("Roy","eduardo.dev.java@gmail.com",UserAdminTypeEnum.RGA));
			
			for (UserAdmin adminUser : adminUsers) {
				if(getByEmailStatus(adminUser.getEmail(), null)==null){
					userAdminDao.insert(adminUser);
				}
			}

			
		}catch (Exception e) {
			log.log(Level.SEVERE, "Operation failed...", e);
			throw new ServiceException(e);
		}
	}

	public void save(UserAdmin userAdmin)throws ServiceException,ValidationException {
		try {
			saveValidate(userAdmin);
			UserAdmin userAdminDb=null;
			if(userAdmin.getKey()==null){
				userAdminDb=userAdmin;
				userAdminDao.insert(userAdminDb);
			}else{
				userAdminDb = userAdminDao.findById(userAdmin.getKey());
				userAdminDb.setName(userAdmin.getName());
				userAdminDb.setEmail(userAdmin.getEmail());
				userAdminDb.setStatus(userAdmin.getStatus());
				userAdminDb.setType(userAdmin.getType());
				userAdminDb.setUpdatedAt(new Date());
				userAdminDao.update(userAdminDb);
			}
		}catch (ValidationException e) {
			throw e;
		}catch (Exception e) {
			log.log(Level.SEVERE, "Operation failed...", e);
			throw new ServiceException(e);
		}
		
	}
	
	private void saveValidate(UserAdmin userAdmin) throws ValidationException{
		Map<String,String> errors=new HashMap<String, String>(); 
		if(StringUtils.isBlank(userAdmin.getName())){
			errors.put("name_error", "Informe o nome");
		}
		if(StringUtils.isBlank(userAdmin.getEmail())){
			errors.put("email_error", "Informe o e-mail");
		}
		if(userAdmin.getType()==null){
			errors.put("type_error", "Informe o tipo");
		}
		if(userAdmin.getStatus()==null){
			errors.put("status_error", "Informe o status");
		}
		if(!errors.isEmpty()){
			throw new ValidationException(errors);
		}
	}


	
	

	
}
