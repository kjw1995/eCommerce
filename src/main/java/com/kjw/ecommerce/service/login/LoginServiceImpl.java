package com.kjw.ecommerce.service.login;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kjw.ecommerce.dto.common.CommonResponseDto;
import com.kjw.ecommerce.dto.login.request.LoginRequestDto;
import com.kjw.ecommerce.jpa.entity.user.User;
import com.kjw.ecommerce.jpa.repository.user.UserRepository;
import com.kjw.ecommerce.util.SessionUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final UserRepository userRepository;

	private final PasswordEncoder customBcryptoPasswordEncoder;

	@Override
	public ResponseEntity<CommonResponseDto<Void>> login(LoginRequestDto loginRequestDto) {

		Optional<User> userOpt = userRepository.findById(loginRequestDto.getId());
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			if (!customBcryptoPasswordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new CommonResponseDto("아이디 또는 비밀번호가 잘못되었습니다."));
			}
			SessionUtils.createUserSession(user);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CommonResponseDto("로그인 성공"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDto("아이디 또는 비밀번호가 잘못되었습니다."));

	}

	@Override
	public ResponseEntity<CommonResponseDto<Void>> logout() {

		try {
			SessionUtils.removeUserSession();
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CommonResponseDto<>("로그아웃 성공"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDto<>("문제가 발생했습니다."));
		}

	}

}
