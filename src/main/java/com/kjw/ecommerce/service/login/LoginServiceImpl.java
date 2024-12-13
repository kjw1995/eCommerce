package com.kjw.ecommerce.service.login;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kjw.ecommerce.common.status.ResponseStatus;
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
	public CommonResponseDto login(LoginRequestDto loginRequestDto) {

		Optional<User> userOpt = userRepository.findById(loginRequestDto.getId());

		if (userOpt.isPresent()) {
			User user = userOpt.get();

			if (!customBcryptoPasswordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
				return new CommonResponseDto(ResponseStatus.FAILED, "아이디 또는 비밀번호가 잘못되었습니다.");
			}

			createSessionDto(user);

			return new CommonResponseDto(ResponseStatus.SUCCESS, "로그인 성공");

		}

		return new CommonResponseDto(ResponseStatus.FAILED, "아이디 또는 비밀번호가 잘못되었습니다.");

	}

	@Override
	public CommonResponseDto logout() {

		try {

			ServletRequestAttributes sessionAttr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
			HttpSession session = sessionAttr.getRequest().getSession(false);
			session.invalidate();

			return new CommonResponseDto(ResponseStatus.SUCCESS, "로그아웃 성공");

		} catch (Exception e) {
			return new CommonResponseDto(ResponseStatus.FAILED, "로그아웃 실패");
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
