package com.kjw.ecommerce.config.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kjw.ecommerce.common.message.UserResponseMessage;
import com.kjw.ecommerce.service.login.LoginService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final PasswordEncoder bcryptoPasswordEncoder;

	private final LoginService loginService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		UserDetails user = loginService.loadUserByUsername(authentication.getPrincipal().toString());

		if (!bcryptoPasswordEncoder.matches(user.getPassword(), authentication.getPrincipal().toString())) {
			throw new UsernameNotFoundException(UserResponseMessage.NOT_FIND.getValue());
		}

		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
	}

}
