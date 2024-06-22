package mclass.store.tripant.member.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;

@RequiredArgsConstructor
@Controller
public class SnsTokenController {
	
	private final Gson gson; 
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String kakaoClientId;
	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	private String kakaoClientSecret;
	@Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
	private String kakaoRedirectUri;
	
	// 카카오 연동 해제
//	@PostMapping("/unlink/kakao")
//	@ResponseBody
//	public int unlinkKakao(HttpSession session) throws IOException, InterruptedException {
//		int result = 0;
//		
//		HttpRequest request = HttpRequest.newBuilder()
//			    .uri(URI.create("https://kauth.kakao.com/oauth/authorize"))
//			    .header("Content-Type", "application/json")
//			    .header("Authorization", (String) session.getAttribute("kakaoToken"))
//			    .method("POST", HttpRequest.BodyPublishers.ofString("{}"))
//			    .build();
//		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//		Map<String, Object> responseMap = gson.fromJson(gson.toJson(response), Map.class);
//		long kakaoId = 0;
//		kakaoId = (long) responseMap.get("id");
//		if(kakaoId > 0) {
//			result = 1;
//		}
//		return result;
//	}
	
	// 카카오 인가 코드
	@PostMapping("/getKakaoCode")
	@ResponseBody
	public String kakaoPermCode() throws IOException, InterruptedException {
		String result = null;
		Map<String, Object> map = new HashMap<>();
		map.put("client_id", kakaoClientId);
		map.put("redirect_uri", kakaoRedirectUri);
		map.put("response_type", "code");
		
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create("https://kauth.kakao.com/oauth/authorize"))
			    .header("Content-Type", "application/json")
			    .method("GET", HttpRequest.BodyPublishers.ofString(gson.toJson(map)))
			    .build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println("body = " + response.body());
//		Map<String, Object> responseMap = gson.fromJson(gson.toJson(response.body()), Map.class);
//		System.out.println(responseMap);
//		result = (String) responseMap.get("error");
		return result;
	}
	
	// 카카오 토큰 받기
	@GetMapping("/login/oauth2/code/kakao")
	public void getKakaoToken(@RequestBody JSONObject jsonObject) throws IOException, InterruptedException {
		String code = jsonObject.getAsString("code");
		System.out.println("getKakaoToken >>>>>>>>"+code);
//		Map<String, Object> map = new HashMap<>();
//		map.put("grant_type", "authorization_code");
//		map.put("client_id", kakaoClientId);
//		map.put("redirect_uri", kakaoRedirectUri);
//		map.put("code", code);
//		map.put("client_secret", kakaoClientSecret);
//		
//		HttpRequest request = HttpRequest.newBuilder()
//			    .uri(URI.create("https://kauth.kakao.com/oauth/token"))
//			    .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
//			    .method("POST", HttpRequest.BodyPublishers.ofString(gson.toJson(map)))
//			    .build();
//		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//		Map<String, Object> responseMap = gson.fromJson(gson.toJson(response), Map.class);
//		String token = "Bearer " + responseMap.get("access_token");
	}
	
	
}
