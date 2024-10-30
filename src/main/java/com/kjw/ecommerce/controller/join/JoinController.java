package com.kjw.ecommerce.controller.join;

import com.kjw.ecommerce.controller.dto.join.JoinRequestDto;
import com.kjw.ecommerce.controller.dto.join.JoinResponseDto;
import com.kjw.ecommerce.service.join.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/users")
    @ResponseBody
    public JoinResponseDto joinMember(@RequestBody JoinRequestDto requestDto) {
        return joinService.join(requestDto);
    }

}
