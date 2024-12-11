package com.kjw.ecommerce.config.crypto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CustomCryptoConfig {

	@Bean
	public PasswordEncoder customBcryptoPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
