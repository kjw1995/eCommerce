package com.kjw.ecommerce.dto.register.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequestDto {

	@NotBlank(message = "회원가입 시 주소는 필수입니다.")
	private String defaultAddress;

	@NotBlank(message = "회원가입 시 상세주소는 필수입니다.")
	private String detailAddress;

	private String lotNumber;

	private String streetName;
	
	private String province;

	private String district;

}
