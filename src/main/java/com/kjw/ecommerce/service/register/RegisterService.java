package com.kjw.ecommerce.service.register;

import org.springframework.http.ResponseEntity;

import com.kjw.ecommerce.dto.common.CommonResponseDto;
import com.kjw.ecommerce.dto.register.request.RegisterRequestDto;
import com.kjw.ecommerce.dto.register.response.RegisterResponseDto;

public interface RegisterService {

	ResponseEntity<CommonResponseDto<RegisterResponseDto>> join(RegisterRequestDto requestDto);

	ResponseEntity<CommonResponseDto> memberIdByMemberSearch(String userId);

}
