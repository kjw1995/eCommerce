package com.kjw.ecommerce.service.register;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kjw.ecommerce.common.auth.UserAuth;
import com.kjw.ecommerce.common.message.UserResponseMessage;
import com.kjw.ecommerce.common.status.UserStatus;
import com.kjw.ecommerce.dto.common.CommonResponseDto;
import com.kjw.ecommerce.dto.register.request.RegisterRequestDto;
import com.kjw.ecommerce.jpa.entity.user.Address;
import com.kjw.ecommerce.jpa.entity.user.Auth;
import com.kjw.ecommerce.jpa.entity.user.User;
import com.kjw.ecommerce.jpa.repository.user.AddressRepository;
import com.kjw.ecommerce.jpa.repository.user.AuthRepository;
import com.kjw.ecommerce.jpa.repository.user.UserRepository;

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
	public ResponseEntity<CommonResponseDto<Void>> join(RegisterRequestDto requestDto) {

		try {

			Optional<User> userOpt = userRepository.findById(requestDto.getUserId());

			if (!userOpt.isPresent()) {

				saveUser(requestDto);
				return ResponseEntity.status(HttpStatus.CREATED)
					.body(new CommonResponseDto<>(UserResponseMessage.REGISTER_SUCCESS.getValue()));

			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new CommonResponseDto<>(UserResponseMessage.REGISTER_FAIL.getValue()));
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new CommonResponseDto<>(UserResponseMessage.REGISTER_FAIL.getValue()));
		}

	}

	@Override
	@Transactional
	public ResponseEntity<CommonResponseDto<Void>> memberIdByMemberSearch(String userId) {

		Optional<User> userOpt = userRepository.findById(userId);

		if (userOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDto<>(UserResponseMessage.NOT_FIND.getValue()));
		}

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CommonResponseDto<>(UserResponseMessage.FIND.getValue()));
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
