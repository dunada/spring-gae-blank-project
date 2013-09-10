package com.blank.util.database;

public class StringQueryBuilder {
	
	private StringBuilder query;
	private boolean firstParam=true;
	
	public StringQueryBuilder(String query) {
		super();
		this.query = new StringBuilder(query);
	}
	
	public void appendParam(String param){
		if(firstParam){
			query.append(" where ");
			firstParam = false;
		}else{
			query.append(" and ");
		}
		query.append(param);
	}
	
	public String toString(){
		return query.toString();
	}
	
}
