package com.kjw.ecommerce.service.register;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kjw.ecommerce.controller.common.status.ResponseStatus;
import com.kjw.ecommerce.controller.dto.common.CommonResponseDto;
import com.kjw.ecommerce.controller.dto.join.inquiry.response.InquiryMemberResponseDto;
import com.kjw.ecommerce.controller.dto.register.request.RegisterRequestDto;
import com.kjw.ecommerce.controller.dto.register.response.RegisterResponseDto;
import com.kjw.ecommerce.jpa.entity.JoinLog;
import com.kjw.ecommerce.jpa.entity.User;
import com.kjw.ecommerce.jpa.repository.JoinLogRepository;
import com.kjw.ecommerce.jpa.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterServiceImpl implements RegisterService {

	private final UserRepository userRepository;

	private final JoinLogRepository joinLogRepository;

	private final PasswordEncoder customBcryptoPasswordEncoder;

	@Override
	@Transactional
	public CommonResponseDto<RegisterResponseDto> join(RegisterRequestDto requestDto) {

		try {

			User user = User.builder()
				.id(requestDto.getUserId())
				.password(customBcryptoPasswordEncoder.encode(requestDto.getPassword()))
				.email(requestDto.getEmail())
				.phonenumber(requestDto.getPhoneNumber())
				.address(requestDto.getAddress())
				.detailAddress(requestDto.getDetailAddress())
				.sido(requestDto.getSido())
				.sigungu(requestDto.getSigungu())
				.build();

			User result = userRepository.save(user);

			JoinLog joinLog = JoinLog.builder()
				.userIdx(result.getIdx())
				.createdAt(LocalDateTime.now())
				.build();

			joinLogRepository.save(joinLog);

			return new CommonResponseDto<RegisterResponseDto>(ResponseStatus.SUCCESS, "회원가입 성공");

		} catch (Exception e) {
			log.error("회원가입 실패, {}", e.getMessage(), e);
			return new CommonResponseDto<RegisterResponseDto>(ResponseStatus.FAILED, "회원가입 실패");
		}

	}

	@Override
	public CommonResponseDto<InquiryMemberResponseDto> memberIdByMemberSearch(String userId) {

		Optional<User> userOpt = userRepository.findById(userId);

		if (userOpt.isPresent()) {
			return new CommonResponseDto<>(ResponseStatus.FAILED, "이미 존재하는 회원ID입니다.");
		}

		return new CommonResponseDto<>(ResponseStatus.SUCCESS, "회원ID 사용 가능");
	}

}
