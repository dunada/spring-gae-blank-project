package com.blank.util.enumerator;

public enum UserAdminStatusEnum {
	ENABLED("Habilitado"),
	DISABLED("Desabilitado");
		
	
	private final String alias;
	
	UserAdminStatusEnum(String alias)	{
		this.alias = alias;
	}


	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}
}
