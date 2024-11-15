package com.kjw.ecommerce.service.join;

import com.kjw.ecommerce.controller.dto.common.CommonResponseDto;
import com.kjw.ecommerce.controller.dto.join.JoinRequestDto;
import com.kjw.ecommerce.controller.dto.join.JoinResponseDto;

public interface JoinService {

    CommonResponseDto<JoinResponseDto> join(JoinRequestDto requestDto);

}
