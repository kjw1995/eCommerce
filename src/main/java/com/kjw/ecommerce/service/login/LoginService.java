package com.kjw.ecommerce.service.login;

import org.springframework.http.ResponseEntity;

import com.kjw.ecommerce.dto.common.CommonResponseDto;
import com.kjw.ecommerce.dto.login.request.LoginRequestDto;

public interface LoginService {

	ResponseEntity<CommonResponseDto> login(LoginRequestDto loginRequestDto);

	ResponseEntity<CommonResponseDto> logout();

}
