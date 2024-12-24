package com.kjw.ecommerce.controller.login;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kjw.ecommerce.common.url.CommonUrl.CommonURL;
import com.kjw.ecommerce.dto.common.CommonResponseDto;
import com.kjw.ecommerce.dto.login.request.LoginRequestDto;
import com.kjw.ecommerce.service.login.LoginService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;

	@GetMapping(CommonURL.LOGIN)
	public ModelAndView getLoginPage() {
		return new ModelAndView("/login/login");
	}

	@PostMapping(CommonURL.LOGIN)
	@ResponseBody
	public ResponseEntity<CommonResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
		return loginService.login(loginRequestDto);
	}

	@PostMapping(CommonURL.LOGOUT)
	@ResponseBody
	public ResponseEntity<CommonResponseDto> logout() {
		return loginService.logout();
	}

}
