package com.kjw.ecommerce.controller.join;

import com.kjw.ecommerce.controller.dto.common.CommonResponseDto;
import com.kjw.ecommerce.controller.dto.join.JoinRequestDto;
import com.kjw.ecommerce.controller.dto.join.JoinResponseDto;
import com.kjw.ecommerce.controller.dto.join.inquiry.response.InquiryMemberResponseDto;
import com.kjw.ecommerce.service.join.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/users" + "/{userId}")
    @ResponseBody
    public CommonResponseDto<InquiryMemberResponseDto> memberIdByMemberSearch(@PathVariable("userId")String userId) {
        return joinService.memberIdByMemberSearch(userId);
    }

    @PostMapping("/users")
    @ResponseBody
    public CommonResponseDto<JoinResponseDto> joinMember(@RequestBody JoinRequestDto requestDto) {
        return joinService.join(requestDto);
    }

}
