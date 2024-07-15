package mclass.store.tripant.member.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import mclass.store.tripant.member.model.service.MemberService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/my")
public class MypageController {

	private final MemberService memberService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final Gson gson;
	
	@Value("${spring.security.oauth2.client.registration.naver.client-id}")
	private String naverClientId;
	@Value("${spring.security.oauth2.client.registration.naver.client-secret}")
	private String naverClientSecret;
	
	@Value("${spring.security.oauth2.client.registration.google.client-secret}")
	private String googleClientSecret;
	@Value("${spring.security.oauth2.client.registration.google.client-id}")
	private String googleClientId;
	
	// 마이페이지
	@GetMapping("/home")
	public String myHome(Model model, Principal principal) {
		String memEmail = principal.getName();
		Map<String, Object> map = memberService.myInfo(memEmail);
		model.addAttribute("memEmail", map.get("MEM_EMAIL"));
		model.addAttribute("memNick", map.get("MEM_NICK"));
		
		int memType =  Integer.parseInt(String.valueOf(map.get("MEM_TYPE")));
		
		int isKakao = memType & (1<<2);
		int isNaver = memType & (1<<1);
		int isGoogle = memType & (1<<0);
		
		model.addAttribute("isKakao", isKakao);
		model.addAttribute("isNaver", isNaver);
		model.addAttribute("isGoogle", isGoogle);
		
		return "mypage/home";
	}
	
	// 카카오 연동 해제
	@PostMapping("/unlink/kakao")
	@ResponseBody
	public int unlinkKakao(Principal principal) throws IOException, InterruptedException {
		int result = 0;
		String memEmail = principal.getName();
		Map<String, Object> map = memberService.tokenValue(memEmail);
		
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create("https://kapi.kakao.com/v1/user/unlink"))
			    .header("Content-Type", "application/json")
			    .header("Authorization", "Bearer " + map.get("MEM_TOKEN_KAKAO"))
			    .method("POST", HttpRequest.BodyPublishers.ofString("{}"))
			    .build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
		if(response.statusCode() == 200) {
			Map<String, Object> myInfo = memberService.myInfo(memEmail);
			Map<String, Object> updateToken = new HashMap<>();
			updateToken.put("memEmail", memEmail);
			int memType = Integer.parseInt(String.valueOf(myInfo.get("MEM_TYPE")));
			memType -= 4;
			updateToken.put("memType", memType);
			result = memberService.updateType(updateToken);
		}else if(response.statusCode() == 401) {
			return result = -1;
		}
		
		return result;
	}

	// 네이버 연동 해제
	@PostMapping("/unlink/naver")
	@ResponseBody
	public int unlinkNaver(Principal principal) throws IOException, InterruptedException {
		int result = 0;
		String memEmail = principal.getName();
		Map<String, Object> map = memberService.tokenValue(memEmail);
		
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("client_id", naverClientId);
		requestBody.put("client_secret", naverClientSecret);
		requestBody.put("access_token", map.get("MEM_TOKEN_NAVER"));
//		requestBody.put("grant_type", "delete");
		
//		import com.github.scribejava.core.builder.api.DefaultApi20;
//		OAuth20Service
		
		
		HttpRequest request = HttpRequest.newBuilder()
//			    .header("Content-Type", "application/json")
//			    .header("Content-Type", "application/x-www-form-urlencoded")
//			    .header("Authorization", "Bearer " + map.get("MEM_TOKEN_KAKAO"))
			    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(requestBody)))
			    .uri(URI.create("https://nid.naver.com/oauth2.0/token?grant_type=delete&"))
			    .build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
		return result;
	}
	
	/** 네이버 접근 토큰 삭제 요청하기 **/
//	public void deleteInfo(OAuth2AccessToken oauthToken, String access_token) throws IOException{		
//		String getAccessTokenEndpoint= "https://nid.naver.com/oauth2.0/token";
//		OAuth20Service oauthService = new ServiceBuilder()
//				.apiKey(CLIENT_ID)
//    			.apiSecret(CLIENT_SECRET)
//    			.callback(REDIRECT_URI).build(NaverLoginApi.instance());
//		/** 접근 토큰 삭제 요청을 위한 setting
//		  		
//		URL에 보낼 정보
//		=> https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id=CLIENT_ID&client_secret=CLIENT_SECRET&access_token=ACCESS_TOKEN&service_provider=NAVER
//		
//		**/		
//        oAuthRequest.addQuerystringParameter("client_secret", CLIENT_SECRET);
//        oAuthRequest.addQuerystringParameter("access_token", access_token);
//        oAuthRequest.addQuerystringParameter("service_provider", "NAVER");
//		
//		oauthService.signRequest(oauthToken, oAuthRequest);
//		
//		Response response = oAuthRequest.send();		
//	}
	
	
	
	
	// 구글 연동 해제
	@PostMapping("/unlink/google")
	@ResponseBody
	public int unlinkGoogle(Principal principal) throws IOException, InterruptedException {
		int result = 0;
		String memEmail = principal.getName();
		Map<String, Object> map = memberService.tokenValue(memEmail);
		
		googleRefreshToken(principal);
		
		return result;
	}
	
	// 구글 갱신 토큰 받기
	public String googleRefreshToken(Principal principal) throws IOException, InterruptedException {
		String memEmail = principal.getName();
		Map<String, Object> map = memberService.tokenValue(memEmail);
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("token", "Bearer " + map.get("MEM_TOKEN_GOOGLE"));
		requestBody.put("returnSecureToken", true);
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://identitytoolkit.googleapis.com/v1/accounts:signInWithCustomToken?key="+googleClientId))
				.header("Content-Type", "application/json")
//				.header("Authorization", "Bearer " + map.get("MEM_TOKEN_GOOGLE"))
				.method("POST", HttpRequest.BodyPublishers.ofString(gson.toJson(requestBody)))
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		return "";
	}
	
	// 닉네임 변경 페이지
	@GetMapping("/nick")
	public String myNick() {
		return "mypage/chNick";
	}

	// 닉네임 변경
	@PostMapping("/nick")
	@ResponseBody
	public int saveNick(String memNick, Principal principal) {
		String memEmail = principal.getName();
		Map<String, Object> map = new HashMap<>();
		map.put("memNick", memNick);
		map.put("memEmail", memEmail);
		int result = memberService.saveNick(map);
		return result;
	}

	// 비밀번호 변경 페이지
	@GetMapping("/pwd")
	public String myPwd() {
		return "mypage/chPwd";
	}
	
	// 비밀번호 변경
	@PostMapping("/pwd")
	@ResponseBody
	public int savePwd(String memPassword, Principal principal) {
		String memEmail = principal.getName();
		Map<String, Object> map = new HashMap<>();
		map.put("memPassword", bCryptPasswordEncoder.encode(memPassword));
		map.put("memEmail", memEmail);
		int result = memberService.savePwd(map);
		return result;
	}

	// 회원 탈퇴 페이지
	@GetMapping("/quit")
	public String myQuit(Model model) {
		String quitExplain = "1. 탈퇴 완료 시 해당 정보는 복구되지 않습니다.\n"
				+ "2. 구매하신 상품은 환불 및 취소되지 않으며 잔여 폰트 기간은 소멸됩니다.\n"
				+ "3. 작성한 여행기는 삭제되지 않습니다.";
		model.addAttribute("quitExplain", quitExplain);
		return "mypage/quit";
	}
	
	// 현재 비밀번호
	@PostMapping("/pwd/use")
	@ResponseBody
	public int pwdUse(String memPassword, Principal principal) {
		String memEmail = principal.getName();
		String currPwd = memberService.currPwd(memEmail);
		if(bCryptPasswordEncoder.matches(memPassword, currPwd)) {
			return 1;
		}else {
			return 0;
		}
	}
	
	// 회원 탈퇴
	@PostMapping("/quit")
	@ResponseBody
	public int myQuit(Principal principal) {
		String memEmail = principal.getName();
		int result = memberService.memQuit(memEmail);
		return result;
	}

}
