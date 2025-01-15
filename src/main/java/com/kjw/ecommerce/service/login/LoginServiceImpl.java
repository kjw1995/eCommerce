package com.kjw.ecommerce.service.login;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kjw.ecommerce.common.message.UserResponseMessage;
import com.kjw.ecommerce.dto.common.CommonResponseDto;
import com.kjw.ecommerce.jpa.entity.user.Auth;
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
	public ResponseEntity<CommonResponseDto<Void>> logout() {

		try {
			SessionUtils.removeUserSession();
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CommonResponseDto<>("로그아웃 성공"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDto<>("문제가 발생했습니다."));
		}

	}

	@Override
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findById(username)
			.orElseThrow(() -> new UsernameNotFoundException(UserResponseMessage.NOT_FIND.getValue()));

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Auth auth : user.getAuth()) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(auth.getType());
			authorities.add(authority);
		}

		return org.springframework.security.core.userdetails.User.builder()
			.username(user.getId())
			.password(user.getPassword())
			.authorities(authorities)
			.build();

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void createUser(UserDetails user) {

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateUser(UserDetails user) {

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteUser(String username) {

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void changePassword(String oldPassword, String newPassword) {

	}

	@Override
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public boolean userExists(String username) {
		return false;
	}
}
