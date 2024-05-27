package mclass.store.tripant.test.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import mclass.store.tripant.apikeys.KeysJaewon;

@RequiredArgsConstructor
@Controller
public class LoginController {
	
	private final KeysJaewon keysJaewon; 
	
	@GetMapping("/login")
	public String login() {
		return "test/login";
	}
	
	@GetMapping("/login/kakao")
	public String kakaoLogin() {
		return "redirect:"+keysJaewon.getKakaoLoginUrl();
	}
	
	@GetMapping("/login/naver")
	public String naverLogin() {
		return "redirect:"+keysJaewon.getNaverLoginUrl();
	}
	
}
