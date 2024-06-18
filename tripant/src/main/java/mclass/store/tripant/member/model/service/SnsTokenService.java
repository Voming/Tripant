package mclass.store.tripant.member.model.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SnsTokenService {
	
	private final Gson gson; 
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String kakaoClientId;
	@Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
	private String kakaoRedirectUri;
	
	// 카카오 인가 코드
	public String kakaoPermCode() throws IOException, InterruptedException {
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
		Map<String, Object> responseMap = gson.fromJson(gson.toJson(response), Map.class);
		return (String) responseMap.get("error");
	}
}
