package com.kjw.ecommerce.service.login;

import com.kjw.ecommerce.dto.common.CommonResponseDto;
import com.kjw.ecommerce.dto.login.request.LoginRequestDto;

public interface LoginService {

	CommonResponseDto login(LoginRequestDto loginRequestDto);

	CommonResponseDto logout();

}
