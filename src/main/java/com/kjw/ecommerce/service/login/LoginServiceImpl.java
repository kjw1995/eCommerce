package com.kjw.ecommerce.service.login;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kjw.ecommerce.dto.common.CommonResponseDto;
import com.kjw.ecommerce.dto.login.request.LoginRequestDto;
import com.kjw.ecommerce.dto.session.SessionDto;
import com.kjw.ecommerce.jpa.entity.User;
import com.kjw.ecommerce.jpa.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final UserRepository userRepository;

	private final PasswordEncoder customBcryptoPasswordEncoder;

	@Override
	public ResponseEntity<CommonResponseDto> login(LoginRequestDto loginRequestDto) {

		Optional<User> userOpt = userRepository.findById(loginRequestDto.getId());

		if (userOpt.isPresent()) {
			User user = userOpt.get();

			if (!customBcryptoPasswordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new CommonResponseDto("아이디 또는 비밀번호가 잘못되었습니다."));
			}

			createSessionDto(user);

			return ResponseEntity.status(HttpStatus.CREATED).body(new CommonResponseDto("로그인 성공"));

		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(new CommonResponseDto("아이디 또는 비밀번호가 잘못되었습니다."));

	}

	@Override
	public ResponseEntity<CommonResponseDto> logout() {

		try {

			ServletRequestAttributes sessionAttr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
			HttpSession session = sessionAttr.getRequest().getSession(false);
			session.invalidate();

			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CommonResponseDto<>("로그아웃 성공"));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDto<>("문제가 발생했습니다."));
		}

	}

	private void createSessionDto(User user) {

		ServletRequestAttributes sessionAttr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpSession session = sessionAttr.getRequest().getSession(true);

		SessionDto sessionDto = new SessionDto();
		sessionDto.setUserId(user.getId());

		session.setAttribute("userSession", sessionDto);

	}

}
