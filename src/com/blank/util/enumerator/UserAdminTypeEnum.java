package com.blank.util.enumerator;

public enum UserAdminTypeEnum {
	GOOGLE("Google"),
	RGA("R/GA");
		
	
	private final String alias;
	
	UserAdminTypeEnum(String alias)	{
		this.alias = alias;
	}


	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}
}
