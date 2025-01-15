package com.kjw.ecommerce.service.login;

import org.springframework.http.ResponseEntity;
import org.springframework.security.provisioning.UserDetailsManager;

import com.kjw.ecommerce.dto.common.CommonResponseDto;

public interface LoginService extends UserDetailsManager {

	ResponseEntity<CommonResponseDto<Void>> logout();

}
