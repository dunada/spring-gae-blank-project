package com.blank.util.json;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.blank.util.enumerator.JsonSerializerEnum;
import com.blank.util.json.vo.JsonResult;
import com.blank.util.json.vo.Result;



/**
 * 
 * @author gb
 *
 */
public class JsonView {
	
	private static final Logger log = Logger.getLogger(JsonView.class.getName());
	
	HttpServletResponse response;
	
	private JsonResult jsonResult = new JsonResult();
	
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
		this.jsonResult.setData(serializer.getObject());
	}
	
	public void setData(Object data){
		this.jsonResult.setData(data);
	}
	
	public void setResult(boolean result){
		this.jsonResult.setResult(new Result(result));
		try {
			this.response.getWriter().write(this.getJson());
		} catch (IOException e) {
			log.log(Level.SEVERE, "JSON write error",e);
		}
	}
	
	public void setResult(boolean result, String message){
		this.jsonResult.setResult(new Result(result, message));
		try {
			this.response.getWriter().write(this.getJson());
		} catch (IOException e) {
			log.log(Level.SEVERE, "JSON write error",e);
		}
	}
	
	public String getJson(){
		return serializer.getSerializer().serialize(jsonResult);
	}
}
