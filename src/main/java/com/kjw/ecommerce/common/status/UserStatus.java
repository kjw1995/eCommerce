package com.kjw.ecommerce.common.status;

public enum UserStatus {

	ACTIVE("1"), INACTIVE("0");

	UserStatus(String value) {
		this.value = value;
	}

	private String value;

	public String getValue() {
		return value;
	}

}
