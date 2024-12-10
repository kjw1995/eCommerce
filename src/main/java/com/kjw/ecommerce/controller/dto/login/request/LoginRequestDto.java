package com.kjw.ecommerce.controller.dto.login.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginRequestDto {

	/**
	 * 로그인 아이디
	 */
	private String id;

	/**
	 * 로그인 비밀번호
	 */
	private String password;

}
