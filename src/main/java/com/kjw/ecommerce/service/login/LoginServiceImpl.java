package com.kjw.ecommerce.service.login;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kjw.ecommerce.controller.common.status.ResponseStatus;
import com.kjw.ecommerce.controller.dto.common.CommonResponseDto;
import com.kjw.ecommerce.controller.dto.login.request.LoginRequestDto;
import com.kjw.ecommerce.jpa.entity.User;
import com.kjw.ecommerce.jpa.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final UserRepository userRepository;

	private final PasswordEncoder customBcryptoPasswordEncoder;

	@Override
	public CommonResponseDto login(LoginRequestDto loginRequestDto) {

		Optional<User> userOpt = userRepository.findById(loginRequestDto.getId());

		if (userOpt.isPresent()) {
			User user = userOpt.get();

			if (!customBcryptoPasswordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
				return new CommonResponseDto(ResponseStatus.FAILED, "아이디 또는 비밀번호가 잘못되었습니다.");
			}

			return new CommonResponseDto(ResponseStatus.SUCCESS, "로그인 성공");

		}

		return new CommonResponseDto(ResponseStatus.FAILED, "아이디 또는 비밀번호가 잘못되었습니다.");

	}
}
