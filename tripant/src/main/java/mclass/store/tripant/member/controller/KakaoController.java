package mclass.store.tripant.member.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import mclass.store.tripant.member.model.service.UserOAuth2UserService;
import mclass.store.tripant.member.model.service.MemberSecurityService;
import mclass.store.tripant.member.model.service.MemberService;

@RequiredArgsConstructor
@Controller
public class KakaoController {
	
	private final KakaoApi kakaoApi;
	private final MemberService memberService;
	private final MemberSecurityService memberSecurityService;
	private final UserOAuth2UserService userOAuth2UserService;

	// 카카오 로그인
	@GetMapping("/login/oauth2/code/kakao")
	public void login(String code, HttpSession session) {
		// 1. 인가 코드 받기
		
		// 2. 토큰 받기
		String accessToken = kakaoApi.getAccessToken(code);
		session.setAttribute("kakaoToken", accessToken);
		
		// 3. 사용자 정보 받기
		Map<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);
		
		Object userId = userInfo.get("userId");
		String memEmail = (String) userInfo.get("email");
		System.out.println("memEmail = "+memEmail);
		
		// Spring Security 
		// 1. 이메일 여부 
		int yn = memberService.existEmail(memEmail);
		
		System.out.println("userId = "+userId.toString());
		System.out.println("memEmail = "+memEmail);
		System.out.println("accessToken = "+accessToken);
	}
	
	
	//카카오 로그아웃
	@GetMapping("/logout/kakao")
	public String logout(HttpSession session) {
		String kakaoToken = (String) session.getAttribute("kakaoToken");
		System.out.println("[kakaoToken] = "+kakaoToken);
		if(kakaoToken != null) {
			kakaoApi.logout(kakaoToken);
			session.invalidate();
		}
		return "redirect:/login";
	}
	
	//카카오 연동 해제
	@GetMapping("/unlink/kakao")
	public String unlink(HttpSession session) {
		String kakaoToken = (String) session.getAttribute("kakaoToken");
		System.out.println("[kakaoToken] = "+kakaoToken);
		if(kakaoToken != null) {
			kakaoApi.unlink(kakaoToken);
			session.invalidate();
			return "redirect:/unlink/kakao";
		}else {
			return "redirect:/login";
		}
	}
}