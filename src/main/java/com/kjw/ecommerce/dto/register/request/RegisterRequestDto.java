package com.kjw.ecommerce.dto.register.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequestDto {

	@NotBlank(message = "회원가입 시 아이디는 필수입니다.")
	private String userId;

	@NotBlank(message = "회원가입 시 비밀번호는 필수입니다.")
	private String password;

	@NotBlank(message = "회원가입 시 이메일은 필수입니다.")
	private String email;

	@NotBlank(message = "회원가입 시 전화번호는 필수입니다.")
	private String phoneNumber;

	private AddressRequestDto address;

}
