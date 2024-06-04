package mclass.store.tripant.member.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mclass.store.tripant.member.model.service.MemberService;

@Component
@RequiredArgsConstructor
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private final MemberService memberService;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String error;
		if(exception instanceof BadCredentialsException) {
			error = "BadCredentialsException";
		}else if(exception instanceof InternalAuthenticationServiceException) {
			error = "InternalAuthenticationServiceException";
		}else if(exception instanceof UsernameNotFoundException) {
			error = "UsernameNotFoundException";
		}else if(exception instanceof AuthenticationException) {
			error = "AuthenticationException";
		}else {
			error = "others";
		}
		error = URLEncoder.encode(error, "UTF-8");
		setDefaultFailureUrl("/login?error=true&exception="+error);
		
		String memEmail = request.getParameter("memEmail");
		String ipAddr = request.getRemoteAddr();
		
		if(memberService.existEmail(memEmail) > 0) {
			Map<String, Object> map = new HashMap<>();
			
			map.put("memEmail", memEmail);
			map.put("logIp", ipAddr);
			map.put("logTf", "F");
			
			memberService.log(map);
		}
		super.onAuthenticationFailure(request, response, exception);
	}
}
