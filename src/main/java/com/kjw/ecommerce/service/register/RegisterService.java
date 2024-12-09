package com.kjw.ecommerce.service.register;

import com.kjw.ecommerce.controller.dto.common.CommonResponseDto;
import com.kjw.ecommerce.controller.dto.register.request.RegisterRequestDto;
import com.kjw.ecommerce.controller.dto.register.response.RegisterResponseDto;

public interface RegisterService {

	CommonResponseDto<RegisterResponseDto> join(RegisterRequestDto requestDto);

	CommonResponseDto memberIdByMemberSearch(String userId);

}
