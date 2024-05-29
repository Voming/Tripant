package mclass.store.tripant.login.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String error;
		if(exception instanceof BadCredentialsException) {
			error = "BadCredentialsException";
//			errorMsg = "이메일 또는 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
		}else if(exception instanceof InternalAuthenticationServiceException) {
			error = "시스템 문제로 인해 요청을 처리할 수 없습니다. 관리자에게 문의해주세요.";
//			error = "시스템 문제로 인해 요청을 처리할 수 없습니다. 관리자에게 문의해주세요.";
		}else if(exception instanceof UsernameNotFoundException) {
			error = "계정이 존재하지 않습니다. 회원가입 진행 후 로그인 해주세요.";
		}else if(exception instanceof AuthenticationException) {
			error = "탈퇴 처리된 회원입니다. 관리자에게 문의해주세요.";
		}else {
			error = "알 수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의해주세요.";
		}
//		byte[] bytes = errorMsg.getBytes(StandardCharsets.UTF_8);
		error = URLEncoder.encode(error, "UTF-8");
		setDefaultFailureUrl("/login?error=true&exception="+error);
		request.setAttribute("aaa", "bbb");
	
		super.onAuthenticationFailure(request, response, exception);
	}
}
