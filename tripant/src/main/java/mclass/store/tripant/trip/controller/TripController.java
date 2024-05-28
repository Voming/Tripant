package mclass.store.tripant.trip.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TripController {
	
	@GetMapping("/map")
	public String mapMain(Model model) {
		//model.addAttribute();
		return "trip/trip";
	}
	
	
	//sts꺼
	@Value("${kakao.map.rest.api}")
	private String apikey;
	
	@GetMapping("/map/getduration")
	public String getduration(
			@RequestParam double startLng,
			@RequestParam double startLat,
			@RequestParam double endLng,
			@RequestParam double endLat,
			Model model) {
		System.out.println(">>>>>>>>>>controller 들어옴");
		System.out.println(endLat);
		String aurlStr= String.format("https://apis-navi.kakaomobility.com/v1/directions?origin=%f,%f&destination=%f,%f&priority=TIME", startLng,startLat,endLng,endLat);
		System.out.println(aurlStr);
		System.out.println(apikey);
		
		try {
			URL url = new URL(aurlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", apikey);
			conn.setRequestProperty("Content-Type", "application/json");
			
			System.out.println(">>>>>>>>>>>>>>>>>>> conn \n\n");
			
			int responseCode = conn.getResponseCode();
			System.out.println("******************             아래 확인");
			System.out.println(responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();
				while((inputLine = br.readLine()) != null) {
					response.append(inputLine);
					
				}
				System.out.println("\n response \n "+response);
				br.close();
				
				model.addAttribute("directions", response.toString() );
			}else {
				model.addAttribute("directions", " \n >>>> 에러났어요 : " + responseCode);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("\n\n >>>>>>>>>>>>>>>>ERROR 확인해주세요<<<<<<<<<<<<<<<<");
		}
		return "trip/trip";
	}
}
