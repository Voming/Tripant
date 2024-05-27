package mclass.store.tripant.test.login.naver;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;
import mclass.store.tripant.apikeys.KeysJaewon;

import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class NaverController {

	private final KeysJaewon keysJaewon;
	
	private final NaverApi naverApi;
	
	//네이버 로그인
	@GetMapping("/login/naver/callback")
	public String naverLogin(String code, HttpSession session) {
		//1. 코드 받기
		
		//2. 토큰 받기
		String accessToken = naverApi.getAccessToken(code);
		session.setAttribute("naverToken", accessToken);
		
		//3. 사용자 정보 받기
		Map<String, Object> userInfo = naverApi.getUserInfo(accessToken);
		
		String email = (String) userInfo.get("email");
		String nickname = (String) userInfo.get("nickname");
		String birthday = (String) userInfo.get("birthday");
		String birthyear = (String) userInfo.get("birthyear");
		
		System.out.println("email = "+email);
		System.out.println("nickname = "+nickname);
		System.out.println("birthday = "+birthday);
		System.out.println("birthyear = "+birthyear);
		System.out.println("accessToken = "+accessToken);
		
		return "redirect:/login";
	}
	
	//네이버 로그아웃
	@GetMapping("/logout/naver")
	public String logout(HttpSession session) {
		String naverToken = (String) session.getAttribute("naverToken");
		System.out.println("[naverToken] = "+naverToken);
		if(naverToken != null) {
			naverApi.logout(naverToken);
			session.removeAttribute("naverToken");
		}
		return "redirect:/login";
	}

	//네이버 연동 해제
	@GetMapping("/unlink/naver")
	public String unlink(HttpSession session) {
		String naverToken = (String) session.getAttribute("naverToken");
		System.out.println("[naverToken] = "+naverToken);
		if(naverToken != null) {
			naverApi.unlink(naverToken);
			session.removeAttribute("naverToken");
			return "redirect:/main";
		}else {
			return "redirect:/login";
		}
	}
	
}
