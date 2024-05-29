package mclass.store.tripant.login.controller;

import java.nio.charset.StandardCharsets;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import mclass.store.tripant.apikeys.KeysJaewon;
import mclass.store.tripant.member.domain.MemberEntity;

@RequiredArgsConstructor
@Controller
public class LoginController {
	
	private final KeysJaewon keysJaewon; 
	
	//로그인 페이지
	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false)String error, 
						@RequestParam(value = "exception", required = false)String exception, 
//						AuthenticationException exception2,
//			RedirectAttributes rttr,
						HttpServletRequest request,
						Model model) {
		System.out.println("==========="+ request.getAttribute("aaa"));
//		No primary or single unique constructor found
//for class org.springframework.security.core.AuthenticationException
		
//		System.out.println(exception2);
		model.addAttribute("error", error);
//		String exception = new String(exceptionArr, StandardCharsets.UTF_8);
		model.addAttribute("exception", exception);
		return "login/login";
	}
	
	//회원가입 페이지
	@GetMapping("/join")
	public String join() {
		return "login/join";
	}
	
	//회원가입
	@PostMapping("/join")
	@ResponseBody
	public void joinMember() {
	}
	
	//카카오 로그인
	@GetMapping("/login/kakao")
	public String kakaoLogin() {
		return "redirect:"+keysJaewon.getKakaoLoginUrl();
	}
	
	//네이버 로그인
	@GetMapping("/login/naver")
	public String naverLogin() {
		return "redirect:"+keysJaewon.getNaverLoginUrl();
	}
	
}
