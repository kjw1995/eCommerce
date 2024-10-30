package com.kjw.ecommerce.service.join;

import com.kjw.ecommerce.controller.dto.join.JoinRequestDto;
import com.kjw.ecommerce.controller.dto.join.JoinResponseDto;

public interface JoinService {

    JoinResponseDto join(JoinRequestDto requestDto);

}
