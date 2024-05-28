package mclass.store.tripant.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mclass.store.tripant.apikeys.KeysJaewon;
import mclass.store.tripant.member.domain.MemberEntity;

@RequiredArgsConstructor
@Controller
public class LoginController {
	
	private final KeysJaewon keysJaewon; 
	
	//로그인 페이지
	@GetMapping("/login")
	public String login() {
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
