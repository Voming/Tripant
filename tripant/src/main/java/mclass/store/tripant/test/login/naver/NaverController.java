package mclass.store.tripant.test.login.naver;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;
import mclass.store.tripant.test.common.KeysJaewon;

@RequiredArgsConstructor
@Controller
public class NaverController {

	private final KeysJaewon keysJaewon;
	
	private final NaverApi naverApi;
	
	@GetMapping("/login/naver/callback")
	public String naverLogin(String code) {
		//1. 코드 받기
		
		//2. 토큰 받기
		String accessToken = naverApi.getAccessToken(code);
		
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
}
