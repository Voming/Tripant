package mclass.store.tripant.trip.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/trip/edit")
public class TripController {
	
	@GetMapping("")
	public String mapMain(Model model) {
		//model.addAttribute();
		return "trip/trip";
	}
	
	
	//sts꺼
	@Value("${kakao.map.rest.api}")
	private String apikey;
	
	//지점 간 이동시간 구하기 ajax
	@PostMapping("duration")
	@ResponseBody //ajax 사용
	public String getduration(
			@RequestParam double startLng,
			@RequestParam double startLat,
			@RequestParam double endLng,
			@RequestParam double endLat,
			Model model) {
		String aurlStr= String.format("https://apis-navi.kakaomobility.com/v1/directions?origin=%f,%f&destination=%f,%f&priority=TIME", startLng,startLat,endLng,endLat);
		String duration ="";
		try {
			URL url = new URL(aurlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", apikey);
			conn.setRequestProperty("Content-Type", "application/json");
			
			int responseCode = conn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();
				while((inputLine = br.readLine()) != null) {
					response.append(inputLine);
				}
				System.out.println("\n response \n "+response.toString());
				br.close();
				
				 // JSON 파싱하여 duration 값만 추출
                JSONObject jsonObject = new JSONObject(response.toString());
                duration = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONObject("summary").getString("duration");
				
			}else {
				return ">>>> 에러났어요 : " + responseCode;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\n\n >>>>>>>>>>>>>>>>ERROR 확인해주세요<<<<<<<<<<<<<<<<");
		} 
		return duration;
	}
}
