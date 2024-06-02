package mclass.store.tripant.plan.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import mclass.store.tripant.place.domain.PlaceAreaEntity;
import mclass.store.tripant.plan.model.repostiory.TimeRepository;

@Service
public class TimeService {
	@Autowired
	private static TimeRepository timeRepository;

	public int deleteAllPlaceMoveTime() {
		return timeRepository.deleteAllPlaceMoveTime();
	}

	public List<PlaceAreaEntity> selectAreaCodeList(int areacode) {
		return timeRepository.selectAreaCodeList(areacode);
	}

	public int selectAreaCodeCount(int areacode) {
		return timeRepository.selectAreaCodeCount(areacode);
	}

	// api 보내기 전에 보낼 리스트 만들어두기
	// 순열(순서있게 배열)


	// map Api 다녀와서 시간 계산하기
	// sts꺼
//	@Value("${kakao.map.rest.api}")
//	private String apikey;
//	
//	//지점 간 이동시간 구하기 ajax
//	public String getduration(
//			 double startLng,  //시작 경도
//			 double startLat,  //시작 위도
//			 double endLng,    //도착 경도
//			 double endLat,    //도착 위도
//			) {
//		String aurlStr= String.format("https://apis-navi.kakaomobility.com/v1/directions?origin=%f,%f&destination=%f,%f&priority=TIME", startLng,startLat,endLng,endLat);
//		String duration ="";
//		try {
//			URL url = new URL(aurlStr);
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			conn.setRequestProperty("Authorization", apikey);
//			conn.setRequestProperty("Content-Type", "application/json");
//			
//			int responseCode = conn.getResponseCode();
//			if (responseCode == HttpURLConnection.HTTP_OK) {
//				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//				String inputLine;
//				StringBuilder response = new StringBuilder();
//				while((inputLine = br.readLine()) != null) {
//					response.append(inputLine);
//				}
//				System.out.println("\n response \n "+response.toString());
//				br.close();
//				
//				 // JSON 파싱하여 duration 값만 추출
//                JSONObject jsonObject = new JSONObject(response.toString());
//                duration = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONObject("summary").getString("duration");
//				
//			}else {
//				return ">>>> 에러났어요 : " + responseCode;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("\n\n >>>>>>>>>>>>>>>>ERROR 확인해주세요<<<<<<<<<<<<<<<<");
//		} 
//		return duration;
//	}

}
