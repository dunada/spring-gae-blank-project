/**
 * 
 */
package com.blank.util.enumerator;

import com.blank.util.json.vo.TesteResult;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

public enum JsonSerializerEnum {
	WITHOUT_DATA(null, new JSONSerializer().include(
		"result.success",
		"result.message"
		).exclude("*")),
	TESTE1(new TesteResult(), new JSONSerializer().include(
		"result.success",
		"result.message",
		"data.userAdmin.name",
		"data.userAdmin.createdAt",
		"data.userAdmin.email"
		).exclude("*")
		.transform(new DateTransformer("dd/MM/yyyy HH'h'mm"), "data.userAdmin.createdAt")),
	TESTE2(null, new JSONSerializer().include(
		"result.success",
		"result.message",
		"data.name",
		"data.email"
		).exclude("*")),
	TESTE3(null, new JSONSerializer().include(
		"result.success",
		"result.message",
		"data.name",
		"data.email"
		).exclude("*"));
	
	
	private final JSONSerializer serializer;
	
	private Object object;
	
	JsonSerializerEnum(Object object, JSONSerializer serializer)	{
		this.object = object;
		this.serializer = serializer;
	}

	/**
	 * @return the serializer
	 */
	public JSONSerializer getSerializer() {
		return serializer;
	}
	/**
	 * @return the object
	 */
	public Object getObject() {
		return this.object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
}
