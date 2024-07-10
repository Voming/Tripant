package mclass.store.tripant.plan.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;

import mclass.store.tripant.place.domain.AreaEntity;
import mclass.store.tripant.plan.domain.CalendarPlanEntity;
import mclass.store.tripant.plan.domain.AfterDto;
import mclass.store.tripant.plan.domain.BeforDto;
import mclass.store.tripant.plan.domain.PlanDate;
import mclass.store.tripant.plan.domain.Spot;
import mclass.store.tripant.plan.domain.Stay;
import mclass.store.tripant.plan.model.repostiory.PlanRepository;

@Service
public class PlanningAlgorithm {
	@Autowired
	private PlanRepository planRepository;

	int spotN = 0;
	int dayN = 0;
	int distribute = 0;

	List<BeforDto> stayPointList = new ArrayList<>();
	List<AfterDto> spotPointList = new ArrayList<>();

	public void planning(CalendarPlanEntity calendarPlan, int areaCode) { 
		System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(calendarPlan));
		
		spotN = 0;
		dayN = 0;
		distribute = 0;

		// 출발 장소 좌표
		AreaEntity startPoint = planRepository.selectAreaInfo(areaCode);

		// 날짜 별 정보(하루, 숙소)
		List<PlanDate> dateArr = calendarPlan.getDateArr();
		dayN = dateArr.size(); // 여행할 날짜 수
		System.out.println("dayN : " + dayN);

		for (int i = 0; i < dayN - 1; i++) { // 여행날 -1 = 숙박장소 개수 , 여행 마지막날에는 숙박장소가 없음.
			Stay stay = dateArr.get(i).getStay();

			// id만 잘라내기
			String idLongStr = stay.getId();
			int id = Integer.parseInt(idLongStr.substring(10, idLongStr.length() - 1));

			stayPointList.add(new BeforDto(dateArr.get(i).getDate(), id, stay.getType(), "", // 숙소는 머무는 시간 없음
					stay.getMapx(), stay.getMapy()));
		}
		System.out.println("stayPointList===");
		System.out.println(stayPointList);

		// 선택 한 장소
		List<Spot> spotArr = calendarPlan.getSpotArr();
		spotN = spotArr.size(); // 정점 개수 -> 선택한 장소 수
		System.out.println("spotN : " + spotN);

		// 출발지에서 가장 가까운 장소 구하기
		int weight[] = new int[spotN];
		int minIndex = 0;
		int min = 0;
		for (int i = 0; i < spotN; i++) {
			weight[i] = getWeight(startPoint.getAreaX(), startPoint.getAreaY(), spotArr.get(i).getMapx(),
					spotArr.get(i).getMapy());
		}
		min = weight[0]; // 가장 가까운 곳이 몇번째에 있는지
		for (int i = 0; i < spotN; i++) {
			if (min > weight[i]) {
				min = weight[i];
				minIndex = i;
			}
		}
		Collections.swap(spotArr, 0, minIndex); // 가장 가까운 곳을 첫번째로 위치 변경
		// A-B 이동일때 이동시간 B에 저장함.
		spotArr.get(0).setWeight(min);  // 출발지 - 첫번째 장소 이동시간을 첫번째 장소에 저장 
		System.out.println(spotArr.get(0)); // 출발지 가장 가까운 장소

		ArrayList<Spot> tour = nearestNeighbor(spotArr);
		spotArr = tour;
		System.out.println(spotArr);
		calendarPlan.setSpotArr(spotArr);
		// 1. 총 날짜 수로 장소 개수 나누기
		distribute = (int) Math.ceil(((double) spotN / dayN)); // 하루에 방문할 장소 수
		System.out.println("distribute : " + distribute);
		System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(calendarPlan));
		int idxDate = 0;  // 입력을 못한 날. 멈춘날idx
		int idxSpot = 0;  // 입력을 못한 장소. 멈춘장소idx
		int weightSum = 0;
		int spanTimeSum = 0;
		int spotOrder = 0;
		for( int i = 0; i < dateArr.size(); i++, idxDate++) {
			int dateTimeRange = dateArr.get(i).getDateTimeRange();
			String spotDay = dateArr.get(i).getDate();
			System.out.println("spotDay:"+spotDay);
			
			// 장소가 모두 채워짐. 날짜에 채울 장소가 없어 반복문 빠져나감. 멈춘날 idxDate
			if(idxSpot == spotArr.size()) {
				System.out.println("=== 멈춘날 idxDate:"+idxDate);
				break;
			}
			
			System.out.println("===1:"+idxSpot);
			System.out.println("===4:"+spotArr.size());
			
			// 장소 관련 초기화
			spotOrder = 1;  // 출발지:1, 장소는 2 부터 시작
			weightSum = 0;
			spanTimeSum = 0;
			// 장소 시작은 idxSpot부터
			for( int j = idxSpot; j < spotArr.size(); j++, idxSpot++) {
				weightSum += spotArr.get(j).getWeight();
				spanTimeSum += spotArr.get(j).getSpotTime(); 
				// 하루 소요시간을 넘치는 경우 다음날(i) 장소 시작은 멈춘장소 idxSpot부터 
				if(dateTimeRange < (weightSum + spanTimeSum)) {
					System.out.println("====2 : "+ j);
					break;
				} 
				System.out.println("====3 : "+ j);
				spotArr.get(j).setSpotDayIdx(i);
				spotArr.get(j).setSpotDay(spotDay);
				spotArr.get(j).setSpotOrder(++spotOrder); // 출발지:1, 장소는 2 부터로 전위증감
			}
			dateArr.get(i).setSpotCnt(spotOrder-1);  // 출발지1+장소 2부터
		}
		
		System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(spotArr));
		
		int spotShiftCntPlus = 0;
		// 장소는 꽉, 장소가 텅빈 날짜 idxDate 를 저장
		if(idxDate != 0 && idxDate < dateArr.size() ) {
			int spotShiftCnt = dateArr.size() - idxDate;  // 여행날수 - 멈춘날인덱스
			System.out.println("a~~~~~spotShiftCnt: "+ spotShiftCnt);
			
			for(int i=dateArr.size()-1 ; i >= idxDate - spotShiftCntPlus; i--) {  // 9 ~ 4-0
				int spotShiftIdx = spotArr.size() + i - idxDate - spotShiftCnt;  // 13+9-4-6-0

				System.out.println("a~~~~~ i: "+ i);
				System.out.println("a~~~~~"+ spotShiftIdx);

				System.out.println("idxDate: "+ idxDate);
				System.out.println("spotShiftCntPlus: "+ spotShiftCntPlus);
				
				spotArr.get(spotShiftIdx).setSpotDay(dateArr.get(i).getDate());
				spotArr.get(spotShiftIdx).setSpotDayIdx(i);  // 옮겨진 장소의 dayIdx
				spotArr.get(spotShiftIdx).setSpotOrder(2);  // 옮겨진 장소는 spotorder 는 초기값인 2(출발지1)
				dateArr.get(i).setSpotCnt(dateArr.get(i).getSpotCnt()+1);  // 추가한 날의 장소를 1개 더하기
				// 옮겨진 날 spotShiftIdx 의 장소는 1개 빼기
				Integer dayIdxSpotShift = spotArr.get(spotShiftIdx).getSpotDayIdx();
				System.out.println("dayIdxSpotShift:"+dayIdxSpotShift);
				if(dayIdxSpotShift != null) {
					int spotCnt = dateArr.get(dayIdxSpotShift).getSpotCnt();
					System.out.println("a===== spotCnt : " + spotCnt);
					if(spotCnt < 1) {  // 출발:1, 하루의 장소 개수가 1개도 남지 않았다면 추가 shift 해야함
						System.out.println("======== 추가 shift !!!!");
						spotShiftCntPlus++;
					}
					dateArr.get(dayIdxSpotShift).setSpotCnt(--spotCnt);  // 장소를 1개 빼기
				}
			}
		}
		System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(calendarPlan));
	}

	/*
	 * // 두 장소 간의 유클리드 거리를 계산하는 메서드입니다. private double distance(int[] city1, int[]
	 * city2) { return Math.sqrt(Math.pow(city1[0] - city2[0], 2) +
	 * Math.pow(city1[1] - city2[1], 2)); }
	 */

	// Nearest Neighbor 알고리즘을 구현하는 메서드입니다.
	private ArrayList<Spot> nearestNeighbor(List<Spot> spots) {
		int n = spots.size();
		boolean[] visited = new boolean[n]; // 방문한 장소를 추적하는 배열입니다.
		ArrayList<Spot> tour = new ArrayList<>(); // 순회 경로를 저장하는 ArrayList입니다.
		tour.add(spots.get(0)); // 첫 번째 장소는 0번 장소로 설정합니다.
		visited[0] = true; // 첫 번째 장소를 방문했다고 표시합니다.

		for (int i = 1; i < n; i++) {
			int nearest = -1; // 가장 가까운 장소 index
			int nearestDist = Integer.MAX_VALUE; // 가장 가까운 거리를 구하기 위핸 MAX 초기값 
			// 모든 장소를 순회하며 가장 가까운 장소를 찾습니다.
			for (int j = 0; j < n; j++) {
				// 다음 방문한 장소까지의 거리(소요시간)
				int currWeight = getWeight(	tour.get(i - 1).getMapx(), tour.get(i - 1).getMapy(),
												spots.get(j).getMapx(), spots.get(j).getMapy());
				if (!visited[j] && (currWeight < nearestDist)) {
					nearest = j;
					nearestDist = currWeight;
				}
			}
//			System.out.println("nearest"+nearest); 
//			System.out.println("nearestDist"+nearestDist);
			// A-B 일때 B에 weight 저장됨.
			spots.get(nearest).setWeight(nearestDist);
			tour.add(spots.get(nearest)); // 가장 가까운 장소를 경로에 추가합니다. -> 가까운 장소의 순서대로 배치됨
			visited[nearest] = true; // 해당 장소를 방문했다고 표시합니다.
		}
		System.out.println("tour!!!!!!!!!");
		System.out.println(tour);
		return tour; // 최종 순회 경로를 반환합니다.
	}

	@Value("${kakao.map.rest.api}")
	private String apikey;

	// 지점 간 이동시간 구하기
	public int getWeight(String startLngStr, // 시작 경도
			String startLatStr, // 시작 위도
			String endLngStr, // 도착 경도
			String endLatStr // 도착 위도
	) {
		double startLng = Double.parseDouble(startLngStr);
		double startLat = Double.parseDouble(startLatStr);
		double endLng = Double.parseDouble(endLngStr);
		double endLat = Double.parseDouble(endLatStr);
//		System.out.println(startLng + ":" + startLat + " - " + endLng + ":" + endLat);
		String aurlStr = String.format(
				"https://apis-navi.kakaomobility.com/v1/directions?origin=%f,%f&destination=%f,%f&priority=TIME&summary=true",
				startLng, startLat, endLng, endLat);
		int duration = 0;
		String durationStr = "";
		String errorStr = "";
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
				while ((inputLine = br.readLine()) != null) {
					response.append(inputLine);
				}
				br.close();

				JSONObject jsonObject = new JSONObject(response.toString());
				// JSON 파싱하여 응답 코드 확인
				errorStr = jsonObject.getJSONArray("routes").getJSONObject(0).getString("result_code");
				if (errorStr.equals("0")) { // 정상 호출
					// JSON 파싱하여 duration 값만 추출
					durationStr = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONObject("summary")
							.getString("duration");
				}else { 
					durationStr = "길찾기 결과를 찾을 수 없음";
					duration = 10800;
				}
			} else { // HTTP_OK 제외하고 오류 발생한 상황
				durationStr = ">>>> 에러났어요 : " + responseCode;
				duration = 0;
			}
		} catch (Exception e) {
//			durationStr = "길찾기 결과를 찾을 수 없음";
//			// 오류상황 일단 3시간으로 측정
//			duration = 10800;
		}
		
		try {
			duration = Integer.parseInt(durationStr);
		} catch (NumberFormatException e) {
			System.out.println(durationStr + "!!!!!!!!!!!!!!!!!!!");
			// 오류상황 일단 3시간으로 측정
			// duration = 10800;
		}
		return duration;
	}
    private void getDailyTimeRate(List<Integer> numbers) {
        // 10개의 숫자 배열
        //double[] numbers = {45, 30, 55, 70, 20, 90, 10, 50, 80, 60};
        
    	// 전체 합을 계산
    	Integer total = 0;
        for (Integer number : numbers) {
            total += number;
        }
        // 백분율을 계산 및 출력
        for (Integer number : numbers) {
        	Integer percentage = (number / total) * 100;
            System.out.printf("%.2f%%\n", percentage);
        }
    }
    private int getPercentage(int number, int total) {
        // 백분율을 계산 및 출력
    	int percentage = (number / total) * 100;
        System.out.printf("%.2f%%\n", percentage);
        return percentage;
    }

}
