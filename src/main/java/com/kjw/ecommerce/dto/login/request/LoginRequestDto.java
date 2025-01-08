package com.kjw.ecommerce.dto.login.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
