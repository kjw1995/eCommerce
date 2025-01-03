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
public class AddressRequestDto {

	// 기본 주소
	@NotBlank(message = "회원가입 시 주소는 필수입니다.")
	private String defaultAddress;

	// 상세 주소
	@NotBlank(message = "회원가입 시 상세주소는 필수입니다.")
	private String detailAddress;

	// 지번 주소
	private String lotNumber;

	// 도로명 주소
	private String streetName;

	// 시
	private String province;

	// 군구
	private String district;

}
