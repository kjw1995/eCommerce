package com.kjw.ecommerce.controller.join;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjw.ecommerce.controller.common.url.CommonUrl.CommonURL;
import com.kjw.ecommerce.controller.dto.common.CommonResponseDto;
import com.kjw.ecommerce.controller.dto.join.JoinRequestDto;
import com.kjw.ecommerce.controller.dto.join.JoinResponseDto;
import com.kjw.ecommerce.controller.dto.join.inquiry.response.InquiryMemberResponseDto;
import com.kjw.ecommerce.service.join.JoinService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class JoinController {

	private final JoinService joinService;

	@GetMapping(CommonURL.USER + "/{userId}")
	@ResponseBody
	public CommonResponseDto<InquiryMemberResponseDto> memberIdByMemberSearch(
		@PathVariable("userId") @Valid String userId) {
		return joinService.memberIdByMemberSearch(userId);
	}

	@PostMapping(CommonURL.USER)
	@ResponseBody
	public CommonResponseDto<JoinResponseDto> joinMember(@RequestBody @Valid JoinRequestDto requestDto) {
		return joinService.join(requestDto);
	}

}
