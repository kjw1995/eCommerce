package com.kjw.ecommerce.service.register;

import org.springframework.http.ResponseEntity;

import com.kjw.ecommerce.dto.common.CommonResponseDto;
import com.kjw.ecommerce.dto.register.request.RegisterRequestDto;

public interface RegisterService {

	ResponseEntity<CommonResponseDto<Void>> join(RegisterRequestDto requestDto);

	ResponseEntity<CommonResponseDto<Void>> memberIdByMemberSearch(String userId);

}
