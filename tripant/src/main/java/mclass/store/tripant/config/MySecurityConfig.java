package mclass.store.tripant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;
import mclass.store.tripant.member.controller.CustomAuthFailureHandler;
import mclass.store.tripant.member.controller.CustomAuthSuccessHandler;

@RequiredArgsConstructor
@EnableWebSecurity /* (debug = true) */
@Configuration
public class MySecurityConfig {
	
	private final CustomAuthFailureHandler customAuthFailureHandler;
	private final CustomAuthSuccessHandler customAuthSuccessHandler;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
					.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
			.csrf((csrf) -> csrf
					.disable())
//					.ignoringRequestMatchers(new AntPathRequestMatcher("/join")))
			.headers((headers) -> headers
					.addHeaderWriter(new XFrameOptionsHeaderWriter(
							XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
			.formLogin((formLogin) -> formLogin
					.loginPage("/login")
					.defaultSuccessUrl("/")
					.successHandler(customAuthSuccessHandler)
					.failureHandler(customAuthFailureHandler)
					.usernameParameter("memEmail")
					.passwordParameter("memPassword"))
			.logout((logout) -> logout
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/")
					.invalidateHttpSession(true))
			;
		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
