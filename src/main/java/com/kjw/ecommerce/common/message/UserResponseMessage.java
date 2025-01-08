package com.kjw.ecommerce.common.message;

public enum UserResponseMessage {

	FIND("사용가능한 아이디 입니다."),
	NOT_FIND("사용불가능한 아이디 입니다."),
	REGISTER_FAIL("회원가입 실패"),
	REGISTER_SUCCESS("회원가입 성공");

	private String value;

	UserResponseMessage(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
