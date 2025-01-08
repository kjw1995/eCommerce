package com.kjw.ecommerce.register;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kjw.ecommerce.common.message.UserResponseMessage;
import com.kjw.ecommerce.dto.common.CommonResponseDto;
import com.kjw.ecommerce.dto.register.request.RegisterRequestDto;
import com.kjw.ecommerce.jpa.entity.user.Address;
import com.kjw.ecommerce.jpa.entity.user.User;
import com.kjw.ecommerce.jpa.repository.user.AddressRepository;
import com.kjw.ecommerce.jpa.repository.user.AuthRepository;
import com.kjw.ecommerce.jpa.repository.user.UserRepository;
import com.kjw.ecommerce.service.register.RegisterServiceImpl;

@ExtendWith(MockitoExtension.class)
@DisplayName("[단위] 테스트 - RegisterController")
public class RegisterServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private RegisterServiceImpl registerService;

	@Test
	@DisplayName("[단위] - 중복된 아이디 검색")
	void testMemberIdByMemberSearchFail() {

		// given
		String testId = "wjddn312";
		User user = User.builder()
			.id(testId)
			.build();
		when(userRepository.findById(testId)).thenReturn(Optional.of(user));

		// when
		ResponseEntity<CommonResponseDto<Void>> response = registerService.memberIdByMemberSearch(testId);

		// then
		assertThat(response)
			.extracting(ResponseEntity::getStatusCode, resp -> resp.getBody().msg())
			.containsExactly(HttpStatus.BAD_REQUEST, UserResponseMessage.NOT_FIND.getValue());

	}

	@Test
	@DisplayName("[단위] - 사용 가능한 아이디 검색")
	void testMemberIdByMemberSearchSuccess() {

		// given
		String testUserId = "test01";
		when(userRepository.findById(testUserId)).thenReturn(Optional.empty());

		// when
		ResponseEntity<CommonResponseDto<Void>> response = registerService.memberIdByMemberSearch(testUserId);

		// then
		assertThat(response)
			.extracting(ResponseEntity::getStatusCode, resp -> resp.getBody().msg())
			.containsExactly(HttpStatus.ACCEPTED, UserResponseMessage.FIND.getValue());

	}

	@Test
	@DisplayName("[단위] - 회원가입 실패, 이미 가입된 유저")
	void testJoinFail() {

		// given
		String testUserId = "wjddn312";
		User user = User.builder()
			.id(testUserId)
			.build();
		RegisterRequestDto requestDto = new RegisterRequestDto();
		requestDto.setUserId(testUserId);
		when(userRepository.findById(testUserId)).thenReturn(Optional.of(user));

		// when
		ResponseEntity<CommonResponseDto<Void>> response = registerService.join(requestDto);

		// then
		assertThat(response)
			.extracting(ResponseEntity::getStatusCode, resp -> resp.getBody().msg())
			.containsExactly(HttpStatus.BAD_REQUEST, UserResponseMessage.REGISTER_FAIL.getValue());
	}



}
