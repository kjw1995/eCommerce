package com.kjw.ecommerce.service.login;

import com.kjw.ecommerce.controller.dto.common.CommonResponseDto;
import com.kjw.ecommerce.controller.dto.login.request.LoginRequestDto;

public interface LoginService {

	CommonResponseDto login(LoginRequestDto loginRequestDto);

}
