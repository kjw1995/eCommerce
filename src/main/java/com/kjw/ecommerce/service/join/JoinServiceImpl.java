package com.kjw.ecommerce.service.join;

import com.kjw.ecommerce.controller.common.status.ResponseStatus;
import com.kjw.ecommerce.controller.dto.common.CommonResponseDto;
import com.kjw.ecommerce.controller.dto.join.JoinRequestDto;
import com.kjw.ecommerce.controller.dto.join.JoinResponseDto;
import com.kjw.ecommerce.jpa.entity.User;
import com.kjw.ecommerce.jpa.repository.JoinLogRepository;
import com.kjw.ecommerce.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService {

    private final UserRepository userRepository;

    private final JoinLogRepository joinLogRepository;

    @Override
    public CommonResponseDto<JoinResponseDto> join(JoinRequestDto requestDto) {

        User user = User.builder()
                        .id(requestDto.getUserId())
                        .password(requestDto.getPassword())
                        .email(requestDto.getEmail())
                        .phonenumber(requestDto.getPhoneNumber())
                        .address(requestDto.getAddress())
                        .build();

        userRepository.save(user);

        return new CommonResponseDto<JoinResponseDto>(ResponseStatus.SUCCESS, "회원가입 성공");


    }

}
