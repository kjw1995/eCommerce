package com.kjw.ecommerce.service.join;

import com.kjw.ecommerce.controller.common.status.ResponseStatus;
import com.kjw.ecommerce.controller.dto.common.CommonResponseDto;
import com.kjw.ecommerce.controller.dto.join.JoinRequestDto;
import com.kjw.ecommerce.controller.dto.join.JoinResponseDto;
import com.kjw.ecommerce.controller.dto.join.inquiry.response.InquiryMemberResponseDto;
import com.kjw.ecommerce.jpa.entity.JoinLog;
import com.kjw.ecommerce.jpa.entity.User;
import com.kjw.ecommerce.jpa.repository.JoinLogRepository;
import com.kjw.ecommerce.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class JoinServiceImpl implements JoinService {

    private final UserRepository userRepository;

    private final JoinLogRepository joinLogRepository;

    @Override
    @Transactional
    public CommonResponseDto<JoinResponseDto> join(JoinRequestDto requestDto) {

        try {

            User user = User.builder()
                    .id(requestDto.getUserId())
                    .password(requestDto.getPassword())
                    .email(requestDto.getEmail())
                    .phonenumber(requestDto.getPhoneNumber())
                    .address(requestDto.getAddress())
                    .build();

            User result = userRepository.save(user);

            JoinLog joinLog = JoinLog.builder()
                    .userIdx(result.getIdx())
                    .createdAt(LocalDateTime.now())
                    .build();

            joinLogRepository.save(joinLog);

            return new CommonResponseDto<JoinResponseDto>(ResponseStatus.SUCCESS, "회원가입 성공");

        } catch(Exception e) {
            log.error("회원가입 실패, {}", e.getMessage(), e);
            return new CommonResponseDto<JoinResponseDto>(ResponseStatus.FAILED, "회원가입 실패");
        }

    }

    @Override
    public CommonResponseDto<InquiryMemberResponseDto> memberIdByMemberSearch(String userId) {

        Optional<User> userOpt = userRepository.findById(userId);

        if(userOpt.isPresent()) {
            return new CommonResponseDto<>(ResponseStatus.FAILED, "이미 존재하는 회원ID입니다.");
        }

        return new CommonResponseDto<>(ResponseStatus.SUCCESS, "회원ID 사용 가능");
    }

}
