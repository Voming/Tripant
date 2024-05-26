package mclass.store.tripant.test.login.kakao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import mclass.store.tripant.test.common.KeysJaewon;
import mclass.store.tripant.test.login.LoginController;

@RequiredArgsConstructor
@Controller
public class KakaoController {
	
	private final KeysJaewon keysJaewon;
	
	private final KakaoApi kakaoApi;

	@GetMapping("/login/oauth2/code/kakao")
	public String kakaoLogin(@RequestParam String code, HttpSession session) {
		//1. 인가 코드 받기
		
		//2. 토큰 받기
		String accessToken = kakaoApi.getAccessToken(code);
		session.setAttribute("kakaoToken", accessToken);
		
		//3. 사용자 정보 받기
		Map<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);
		
		Object userId = userInfo.get("userId");
		String email = (String) userInfo.get("email");
		String nickname = (String) userInfo.get("nickname");
		
		System.out.println("userId = "+userId.toString());
		System.out.println("email = "+email);
		System.out.println("nickname = "+nickname);
		System.out.println("accessToken = "+accessToken);
		
		return "redirect:/login";
	}
	
	@GetMapping("/logout/kakao")
	public String kakaoLogout(HttpSession session) {
		String kakaoToken = (String) session.getAttribute("kakaoToken");
		System.out.println("[kakaoToken] = "+kakaoToken);
		if(kakaoToken != null) {
			kakaoApi.kakaoLogout((String)session.getAttribute("kakaoToken"));
			session.removeAttribute("kakaoToken");
		}
		return "redirect:/login";
	}
	
	@GetMapping("/unlink/kakao")
	public String kakaoUnlink(HttpSession session) {
		String kakaoToken = (String) session.getAttribute("kakaoToken");
		System.out.println("[kakaoToken] = "+kakaoToken);
		if(kakaoToken != null) {
			kakaoApi.kakaoUnlink(kakaoToken);
			session.removeAttribute("kakaoToken");
			return "redirect:/main";
		}else {
			return "redirect:/login";
		}
	}
	
}