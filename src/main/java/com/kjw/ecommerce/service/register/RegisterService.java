package com.kjw.ecommerce.service.register;

import com.kjw.ecommerce.dto.common.CommonResponseDto;
import com.kjw.ecommerce.dto.register.request.RegisterRequestDto;
import com.kjw.ecommerce.dto.register.response.RegisterResponseDto;

public interface RegisterService {

	CommonResponseDto<RegisterResponseDto> join(RegisterRequestDto requestDto);

	CommonResponseDto memberIdByMemberSearch(String userId);

}
