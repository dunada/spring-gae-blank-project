package com.blank.util.json;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;




import org.apache.commons.lang3.StringUtils;

import com.blank.util.enumerator.JsonSerializerEnum;



/**
 * 
 * @author gb
 *
 */
public class JsonView {
	
	private static final Logger log = Logger.getLogger(JsonView.class.getName());
	
	HttpServletResponse response;
	
	private Object data;
	
	public JsonSerializerEnum serializer;

	public JsonView(HttpServletResponse response, JsonSerializerEnum jsonSerializerEnum){
		if(jsonSerializerEnum == null){
			log.log(Level.SEVERE, "Invalid JSONSerializer");
		}else{
			
			this.response = response;
			this.response.setContentType("application/json");
			serializer = jsonSerializerEnum;
		}
	}
	
	public void addObject(String property, Object object){
		Class<? extends Object> clazz = serializer.getObject().getClass();
		Method meth = null;
		for(Method method : clazz.getMethods()){
			if(method.getName().equals("set" + StringUtils.capitalize(property))){
				meth = method;	
			}
		}
        try {
        	if(meth!=null){
        		meth.invoke(serializer.getObject(), object);
        	}
		} catch (Exception e) {
			log.log(Level.SEVERE, "JSON set data error",e);
		}
        data = serializer.getObject();
	}
	
	public void setData(Object data){
		this.data=data;
	}
	
	public void success(){
		try {
			this.response.getWriter().write(this.getJson());
		} catch (IOException e) {
			log.log(Level.SEVERE, "JSON write error",e);
		}
	}
	
	public void error(String message){
		try {
			this.response.setStatus(503);
			if(StringUtils.isNotBlank(message)){
				this.response.getWriter().write(message);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "JSON write error",e);
		}
	}
	
	public String getJson(){
		return serializer.getSerializer().serialize(data);
	}
}
