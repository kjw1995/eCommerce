package com.kjw.ecommerce.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
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

	// 정적리소스 인증 설정
	@Bean
	public WebSecurityCustomizer webConfigure() {
		return (web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
	}

	@Bean
	public SecurityFilterChain filter(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {

		MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);

		MvcRequestMatcher[] whiteList = {
			mvc.pattern("/"),
			mvc.pattern(CommonURL.VIEW_LOGIN),
			mvc.pattern(CommonURL.VIEW_REGISTRATION + "/**"),
			mvc.pattern(CommonURL.VIEW_MAIN + "/**"),
			mvc.pattern(CommonURL.VIEW_PRODUCT + "/**"),
		};

		http
			.csrf(AbstractHttpConfigurer::disable)
			.cors(AbstractHttpConfigurer::disable)
			.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
			.authorizeHttpRequests((httpRequests -> httpRequests
				.requestMatchers(whiteList).permitAll()))
			.formLogin((loginOptions -> loginOptions
				.loginPage(CommonURL.VIEW_LOGIN)
				.loginProcessingUrl(CommonURL.MEMBER_LOGIN)))
			.userDetailsService(loginService)
			.logout((logoutOptions -> logoutOptions
				.logoutUrl(CommonURL.MEMBER_LOGOUT)
				.clearAuthentication(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl(CommonURL.VIEW_MAIN)));

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
