package com.kjw.ecommerce.controller.dto.join;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class JoinRequestDto {

	@NotBlank(message = "회원가입 시 아이디는 필수입니다.")
	private String userId;

	@NotBlank(message = "회원가입 시 비밀번호는 필수입니다.")
	private String password;

	@NotBlank(message = "회원가입 시 이메일은 필수입니다.")
	private String email;

	@NotBlank(message = "회원가입 시 전화번호는 필수입니다.")
	private String phoneNumber;

	@NotBlank(message = "회원가입 시 주소는 필수입니다.")
	private String address;

	@NotBlank(message = "회원가입 시 상세주소는 필수입니다.")
	private String detailAddress;

	private String sido;

	private String sigungu;

}
