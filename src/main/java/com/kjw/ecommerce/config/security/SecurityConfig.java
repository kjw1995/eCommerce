package com.kjw.ecommerce.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.kjw.ecommerce.common.url.CommonUrl.CommonURL;
import com.kjw.ecommerce.service.login.LoginService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final LoginService loginService;

	@Bean
	public PasswordEncoder bcryptoPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filter(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {

		MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);

		MvcRequestMatcher[] staticResource = {
			mvc.pattern("/cs/**"),
			mvc.pattern("/js/**"),
			mvc.pattern("/fonts/**"),
			mvc.pattern("/images/**")
		};

		MvcRequestMatcher[] whiteList = {
			mvc.pattern("/"),
			mvc.pattern(CommonURL.PAGE_LOGIN),
			mvc.pattern(CommonURL.PAGE_REGISTRATION + "/**"),
			mvc.pattern(CommonURL.PAGE_MAIN + "/**"),
			mvc.pattern(CommonURL.PAGE_PRODUCT + "/**"),
		};

		http
			.csrf(AbstractHttpConfigurer::disable)
			.cors(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests((httpRequests -> httpRequests
				.requestMatchers(staticResource).permitAll()
				.requestMatchers(whiteList).permitAll()))
			.formLogin((loginOptions -> loginOptions
				.loginPage(CommonURL.PAGE_LOGIN)
				.successForwardUrl(CommonURL.PAGE_MAIN))
			)
			.userDetailsService(loginService)
			.logout((logoutOptions -> logoutOptions
				.logoutUrl(CommonURL.LOGOUT)
				.clearAuthentication(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl(CommonURL.PAGE_MAIN)));

		return http.build();
	}

	@Bean
	public CustomAuthenticationProvider customAuthenticationProvider() {
		return new CustomAuthenticationProvider(bcryptoPasswordEncoder(), loginService);
	}

	@Bean
	public AuthenticationManager authenticationManager() {
		AuthenticationProvider provider = customAuthenticationProvider();
		return new ProviderManager(provider);
	}

}
