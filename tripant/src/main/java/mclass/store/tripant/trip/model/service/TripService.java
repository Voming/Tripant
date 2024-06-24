package mclass.store.tripant.trip.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mclass.store.tripant.trip.domain.DayEntity;
import mclass.store.tripant.trip.model.repository.TripRepository;

@Service
@RequiredArgsConstructor
public class TripService {
	
	//map 위치간 이동시간
	@Value("${kakao.map.rest.api}")
	private String apikey;
	
	private final TripRepository repository;
	
	//여행 일정 목록 불러오기
	public List<DayEntity> detailList(Integer planId){
		return repository.detailList(planId);
	};
	
	//여행 기본(포괄)정보 불러오기
	public Map<String , Object> planInfo(Integer planId){
		return repository.planInfo(planId);
	};
	
	//장소간 이동시간 구하기
	public String getduration(
			 String startLngStr,  //시작 경도 
			 String startLatStr,  //시작 위도
			 String endLngStr,    //도착 경도
			 String endLatStr    //도착 위도
			 ) {
		double startLng = Double.parseDouble(startLngStr);
		double startLat = Double.parseDouble(startLatStr);
		double endLng = Double.parseDouble(endLngStr);
		double endLat = Double.parseDouble(endLatStr);
		
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
				br.close();
				
				 // JSON 파싱하여 duration 값만 추출
                JSONObject jsonObject = new JSONObject(response.toString());
                duration = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONObject("summary").getString("duration");
				
			}else {
				return ">>>> 에러났어요 : " + responseCode;
			}
		} catch (Exception e) {
			e.printStackTrace();
			duration = "1800";
			System.out.println("\n\n >>>>>>>>>>>>>>>>ERROR 확인해주세요<<<<<<<<<<<<<<<<");
		} 
		return duration;
	}
	
}
