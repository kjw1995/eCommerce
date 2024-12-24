package com.kjw.ecommerce.service.register;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kjw.ecommerce.common.auth.UserAuth;
import com.kjw.ecommerce.common.status.UserStatus;
import com.kjw.ecommerce.dto.common.CommonResponseDto;
import com.kjw.ecommerce.dto.register.request.RegisterRequestDto;
import com.kjw.ecommerce.dto.register.response.RegisterResponseDto;
import com.kjw.ecommerce.jpa.entity.Address;
import com.kjw.ecommerce.jpa.entity.Auth;
import com.kjw.ecommerce.jpa.entity.User;
import com.kjw.ecommerce.jpa.repository.AddressRepository;
import com.kjw.ecommerce.jpa.repository.AuthRepository;
import com.kjw.ecommerce.jpa.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterServiceImpl implements RegisterService {

	private final UserRepository userRepository;

	private final AddressRepository addressRepository;

	private final AuthRepository authRepository;

	private final PasswordEncoder customBcryptoPasswordEncoder;

	@Override
	@Transactional
	public ResponseEntity<CommonResponseDto<RegisterResponseDto>> join(RegisterRequestDto requestDto) {

		try {

			Optional<User> userOpt = userRepository.findById(requestDto.getUserId());

			if (!userOpt.isPresent()) {

				saveUser(requestDto);
				return ResponseEntity.status(HttpStatus.CREATED)
					.body(new CommonResponseDto<>("회원가입 성공"));

			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDto<>("회원가입 실패"));
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDto<>("회원가입 실패"));
		}

	}

	@Override
	public ResponseEntity<CommonResponseDto> memberIdByMemberSearch(String userId) {

		Optional<User> userOpt = userRepository.findById(userId);

		if (userOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDto<>("이미 존재하는 회원ID입니다."));
		}

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CommonResponseDto<>("회원ID 사용 가능"));
	}

	private void saveUser(RegisterRequestDto requestDto) {

		User user = User.builder()
			.id(requestDto.getUserId())
			.password(customBcryptoPasswordEncoder.encode(requestDto.getPassword()))
			.email(requestDto.getEmail())
			.phonenumber(requestDto.getPhoneNumber())
			.isActive(UserStatus.INACTIVE.getValue())
			.createdAt(LocalDateTime.now())
			.build();

		User result = userRepository.save(user);

		Address address = Address.builder()
			.userIdx(result.getIdx())
			.defaultAddress(requestDto.getAddress().getDefaultAddress())
			.detailAddress(requestDto.getAddress().getDetailAddress())
			.lotNumber(requestDto.getAddress().getLotNumber())
			.province(requestDto.getAddress().getProvince())
			.district(requestDto.getAddress().getDistrict())
			.createdAt(LocalDateTime.now())
			.build();

		addressRepository.save(address);

		Auth auth = Auth.builder()
			.userIdx(result.getIdx())
			.type(UserAuth.NORMAL.name())
			.createdAt(LocalDateTime.now())
			.build();

		authRepository.save(auth);

	}

}
