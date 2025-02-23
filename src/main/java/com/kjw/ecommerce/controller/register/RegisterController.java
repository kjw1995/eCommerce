package com.kjw.ecommerce.controller.register;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kjw.ecommerce.common.url.CommonUrl.CommonURL;
import com.kjw.ecommerce.dto.common.CommonResponseDto;
import com.kjw.ecommerce.dto.register.request.RegisterRequestDto;
import com.kjw.ecommerce.service.register.RegisterService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegisterController {

	private final RegisterService registerService;

	@GetMapping(CommonURL.PAGE_REGISTRATION)
	public ModelAndView getRegisterPage() {
		return new ModelAndView("/register/register");
	}

	@GetMapping(CommonURL.USER + "/{userId}")
	@ResponseBody
	public ResponseEntity<CommonResponseDto<Void>> memberIdByMemberSearch(
		@PathVariable("userId") @Valid String userId) {
		return registerService.memberIdByMemberSearch(userId);
	}

	@PostMapping(CommonURL.USER)
	@ResponseBody
	public ResponseEntity<CommonResponseDto<Void>> joinMember(
		@RequestBody @Valid RegisterRequestDto requestDto) {
		return registerService.join(requestDto);
	}

}
