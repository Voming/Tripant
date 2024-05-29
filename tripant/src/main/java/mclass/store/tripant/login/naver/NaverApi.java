package mclass.store.tripant.login.naver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import mclass.store.tripant.apikeys.KeysJaewon;

@RequiredArgsConstructor
@Component
public class NaverApi {
	
	private final KeysJaewon keysJaewon;
	
	//토큰 받기
	public String getAccessToken(String code) {
		String accessToken = "";
		String refreshToken = "";
		String reqUrl = "https://nid.naver.com/oauth2.0/token";
		
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			//파라미터 입력
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			conn.setDoOutput(true);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=").append(keysJaewon.getNaverLoginClientId());
			sb.append("&client_secret=").append(keysJaewon.getNaverLoginClientSecret());
			sb.append("&code=").append(code);
			sb.append("&state=");
			
			bw.write(sb.toString());
			bw.flush();
			
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("client_id", keysJaewon.getNaverLoginClientId());
//			conn.setRequestProperty("client_secret", keysJaewon.getNaverLoginClientSecret());
//			conn.setRequestProperty("code", code);
//			conn.setRequestProperty("grant_type", "authorization_code");
//			conn.setRequestProperty("state", state);
			
			int responseCode = conn.getResponseCode();
			System.out.println("[NaverApi.getAccessToken] responseCode = "+responseCode);
			
			BufferedReader br;
			if (responseCode >= 200 && responseCode <= 300) {
	            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
			
			String line = "";
			StringBuilder responseSb = new StringBuilder();
			while((line = br.readLine())!=null) {
				responseSb.append(line);
			}
			String result = responseSb.toString();
			System.out.println("responseBody = "+result);
			
			Map<String, String> resultMap = new Gson().fromJson(result, Map.class);
			accessToken = resultMap.get("access_token");
			refreshToken = resultMap.get("refresh_token");
			
			br.close();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}
	
	//사용자 정보 가져오기
	public HashMap<String, Object> getUserInfo(String accessToken){
		HashMap<String, Object> userInfo = new HashMap<>();
		String reqUrl = "https://openapi.naver.com/v1/nid/me";
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			//파라미터 입력
			conn.setRequestProperty("Authorization", "Bearer "+accessToken);
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;utf-8");
			
			int responseCode = conn.getResponseCode();
			System.out.println("[NaverApi.getUserInfo] responseCode = "+responseCode);
			
			BufferedReader br;
			if (responseCode >= 200 && responseCode <= 300) {
	            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
			
			String line = "";
			StringBuilder responseSb = new StringBuilder();
			while((line = br.readLine())!=null) {
				responseSb.append(line);
			}
			String result = responseSb.toString();
			System.out.println("responseBody = "+result);
			
			Gson gson = new Gson();
			
			HashMap<String, Object> resultMap = gson.fromJson(result, HashMap.class);
			
			
			String responseStr = gson.toJson(resultMap.get("response"));
			System.out.println(responseStr);
			
			HashMap<String, Object> response = gson.fromJson(responseStr, HashMap.class);
			
			String email = (String)response.get("email");
			String nickname = (String)response.get("nickname");
			String birthday = (String)response.get("birthday");
			String birthyear = (String)response.get("birthyear");
			
			userInfo.put("email", email);
			userInfo.put("nickname", nickname);
			userInfo.put("birthday", birthday);
			userInfo.put("birthyear", birthyear);
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userInfo;
	}
	
	//로그아웃 처리
	public void logout(String accessToken) {
		String reqUrl = "";
	}
	
	//네이버 연동 해제
	public void unlink(String accessToken) {
		String reqUrl = "https://nid.naver.com/oauth2.0/token";
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			conn.setDoOutput(true);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			
			//파라미터 입력
			sb.append("client_id=").append(keysJaewon.getNaverLoginClientId());
			sb.append("&client_secret=").append(keysJaewon.getNaverLoginClientSecret());
			sb.append("&access_token=").append(accessToken);
			sb.append("&grant_type=delete");
			
			bw.write(sb.toString());
			bw.flush();
			
			int responseCode = conn.getResponseCode();
			System.out.println("[NaverApi.getAccessToken] responseCode = "+responseCode);
			
			BufferedReader br;
			if (responseCode >= 200 && responseCode <= 300) {
	            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
			
			String line = "";
			StringBuilder responseSb = new StringBuilder();
			while((line = br.readLine())!=null) {
				responseSb.append(line);
			}
			String result = responseSb.toString();
			System.out.println("responseBody = "+result);
			
			br.close();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
