package mclass.store.tripant.plan.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.GsonBuilder;

import mclass.store.tripant.plan.domain.CalendarPlanEntity;
import mclass.store.tripant.plan.domain.PlaceInfoEntity;
import mclass.store.tripant.plan.domain.PlanDate;
import mclass.store.tripant.plan.domain.PlanSpotEntity;
import mclass.store.tripant.plan.domain.Spot;
import mclass.store.tripant.plan.model.repostiory.PlanRepository;

@Service
public class PlanningAlgorithm {
	@Autowired
	private PlanRepository planRepository;

	int spotN = 0;
	int dayN = 0;
	int distribute = 0;
	
	PlaceInfoEntity startPoint;

	List<PlanSpotEntity> result = new ArrayList<>();
	List<PlanDate> dateArr = new ArrayList<>();
	List<Spot> spotArr = new ArrayList<>();

	public void planning(CalendarPlanEntity calendarPlan, int areaCode, String planTitle, String memEmail) {
		spotN = 0;
		dayN = 0;
		distribute = 0;
		startPoint = planRepository.selectPlaceInfo(areaCode); // 출발 장소 좌표

		// String startDate = calendarPlan.get

		// 날짜 별 정보(하루, 숙소)
		dateArr = calendarPlan.getDateArr();
		dayN = dateArr.size(); // 여행할 날짜 수
		String startDate = dateArr.get(0).getDate();
		String endDate = dateArr.get(dateArr.size() - 1).getDate();

		// 선택 한 장소
		spotArr = calendarPlan.getSpotArr();
		spotN = spotArr.size(); // 정점 개수 -> 선택한 장소 수

		// 출발지에서 가장 가까운 장소 구하기
		int weight[] = new int[spotN];
		int minIndex = 0;
		int min = 0;
		for (int i = 0; i < spotN; i++) {
			weight[i] = getWeight(startPoint.getMapx(), startPoint.getMapy(), spotArr.get(i).getMapx(),
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
		spotArr.get(0).setWeight(min); // 출발지 - 첫번째 장소 이동시간을 첫번째 장소에 저장

		ArrayList<Spot> tour = nearestNeighbor(spotArr); // 장소 한 줄 긋기===========================
		spotArr = tour; // 얕은 복사
		calendarPlan.setSpotArr(spotArr);

		distributeSpot(); // 장소 분배하기(날짜에 채우기)==================================================
		

		// insert 할 리스트 만들기
		makePlanningMap(); // insert할 내용 result에 맞춰서 담기==================================================
		
		Map<String, Object> allMap = new HashMap<>();
		
		allMap.put("areaCode", areaCode);
		allMap.put("planTitle", planTitle);
		allMap.put("startDate", startDate);
		allMap.put("endDate", endDate);

		allMap.put("planSchedule", dateArr);

		allMap.put("planSpot", result);
		
		allMap.put("email", memEmail);


		insertPlanning(allMap);
	}
	
	@Transactional
	public int insertPlanning(Map<String, Object> planningMap) {
		return planRepository.insertPlanning(planningMap);
	}
	
	// insert할 내용 result에 맞춰서 담기===============================================================
	private void makePlanningMap() {
		for (int i = 0; i < dateArr.size(); i++) {
			if (i == 0) { // 첫째날 출발지 입력
				result.add(new PlanSpotEntity(dateArr.get(i).getDate(), // 날짜
						startPoint.getType(), // 타입
						startPoint.getContentid(), // id
						1, // 방문순서
						"")); // 머무는 시간
			} else { // 둘째날이후로는 출발에 전날 숙소 입력
				int stayId = Integer.parseInt(dateArr.get(i - 1).getStay().getId().substring(10,
						dateArr.get(i - 1).getStay().getId().length()));
				result.add(new PlanSpotEntity(dateArr.get(i).getDate(), // 날짜
						dateArr.get(i - 1).getStay().getType(), // 타입
						stayId, // id
						1, // 방문순서
						"")); // 머무는 시간
			}
			for (int j = 0; j < spotArr.size(); j++) {
				if (spotArr.get(j).getSpotDay() == dateArr.get(i).getDate()) {
					int spotId = Integer
							.parseInt(spotArr.get(j).getId().substring(10, spotArr.get(j).getId().length()));
					// 장소 입력
					result.add(new PlanSpotEntity(spotArr.get(j).getSpotDay(), // 날짜
							spotArr.get(j).getType(), // 타입
							spotId, // id
							spotArr.get(j).getSpotOrder(), // 순서
							String.valueOf(spotArr.get(j).getSpotTime()))); // 머무는 시간

					// 숙소 입력
					if (spotArr.get(j).getSpotOrder() == (dateArr.get(i).getSpotCnt() + 1)) { // 마지막 장소가 위에서 들어간 상태
						if (i == dateArr.size() - 1) { // 맨마지막 날 숙소는 들어가지 않음
						} else {
							// 마지막 날을 제외한 숙소 입력
							int stayId = Integer.parseInt(dateArr.get(i).getStay().getId().substring(10,
									dateArr.get(i).getStay().getId().length()));
							result.add(new PlanSpotEntity(dateArr.get(i).getDate(), // 날짜
									dateArr.get(i).getStay().getType(), // 타입
									stayId, // id
									spotArr.get(j).getSpotOrder() + 1, // 방문순서
									"")); // 머무는 시간
						}
					}
				}
			}
		}
	}

	// 장소 분배하기(날짜에 채우기)=============================================================================
	private void distributeSpot() { 
		int idxDate = 0; // 입력을 못한 날. 멈춘날 idx
		int idxSpot = 0; // 입력을 못한 장소. 멈춘장소 idx
		int weightSum = 0; // (하루)이동거리 합
		int spanTimeSum = 0; // (하루)머물시간 합
		int spotOrder = 0; // 출발지:1, 장소는 2 부터 시작
		for (int i = 0; i < dateArr.size(); i++, idxDate++) {
			int dateTimeRange = dateArr.get(i).getDateTimeRange();
			String spotDay = dateArr.get(i).getDate();

			// 장소가 모두 채워짐. 날짜에 채울 장소가 없어 반복문 빠져나감. 멈춘날 idxDate
			if (idxSpot == spotArr.size()) {
				break;
			}


			// 장소 관련 초기화
			spotOrder = 1; // 출발지:1, 장소는 2 부터 시작
			weightSum = 0;
			spanTimeSum = 0;
			// 장소 시작은 idxSpot부터
			for (int j = idxSpot; j < spotArr.size(); j++, idxSpot++) {
				weightSum += spotArr.get(j).getWeight();
				spanTimeSum += spotArr.get(j).getSpotTime();
				// 하루 소요시간을 넘치는 경우 다음날(i) 장소 시작은 멈춘장소 idxSpot부터
				if (dateTimeRange < (weightSum + spanTimeSum)) {
					break;
				}
				spotArr.get(j).setSpotDayIdx(i); // 몇일차 -> 0부터 시작
				spotArr.get(j).setSpotDay(spotDay); // 방문일
				spotArr.get(j).setSpotOrder(++spotOrder); // 출발지:1, 장소는 2 부터로 전위증감
			}
			dateArr.get(i).setSpotCnt(spotOrder - 1); // 하루 방문 장소는 장소만 cnt 하므로 spotOrder보다 1 작음
		}

		int spotShiftCntPlus = 0;
		// 장소는 꽉, 장소가 텅빈 날짜 idxDate 를 저장
		if (idxDate != 0 && idxDate < dateArr.size()) { // 텅빈 날짜 수가 정상일때
			int spotShiftCnt = dateArr.size() - idxDate; // 총 날짜 수 - idxDate(멈춘날) = 텅빈 날짜 수 -> 바꿔야하는 수

			for (int i = dateArr.size() - 1; i >= idxDate - spotShiftCntPlus; i--) { // 9 ~ 4-0
				int spotShiftIdx = spotArr.size() + i - idxDate - spotShiftCnt; // 13+9-4-6-0

				// spotShiftIdx 번째 장소를 갖고 있던 날짜 index
				Integer dayIdxSpotShift = spotArr.get(spotShiftIdx).getSpotDayIdx();
				// spotShiftIdx 번째 장소를 갖고 있던 날짜 의 spotCnt 방문장소 -1
				int spotCnt = dateArr.get(dayIdxSpotShift).getSpotCnt();
				dateArr.get(dayIdxSpotShift).setSpotCnt(--spotCnt); // 장소를 1개 빼기

				if (spotCnt < 1) { // 출발:1, 하루의 장소 개수가 1개도 남지 않았다면 추가 shift 해야함
					spotShiftCntPlus++;
				}

				// spotShiftIdx 번째 장소를 i번째 날짜로 바꾸기
				spotArr.get(spotShiftIdx).setSpotDay(dateArr.get(i).getDate());
				spotArr.get(spotShiftIdx).setSpotDayIdx(i); // i번째 날짜 index 바꾸기
				// spotShiftIdx 번째 장소는 i번째 날짜에 첫장소이므로 spotOrder는 2
				spotArr.get(spotShiftIdx).setSpotOrder(2); // i번째 날짜에는 1개의 장소만 있을 것이므로 spotorder 는 초기값인 2(출발지1)

				// i번째 날에 장소가 생겼으므로 spotCnt 1
				dateArr.get(i).setSpotCnt(1);
			}
		}
	}

	

	// 장소 한 줄 긋기 알고리즘(모든 장소 방문하며 가장 가까운 장소 찾기)==========================================================
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
				int currWeight = getWeight(tour.get(i - 1).getMapx(), tour.get(i - 1).getMapy(), spots.get(j).getMapx(),
						spots.get(j).getMapy());
				if (!visited[j] && (currWeight < nearestDist)) {
					nearest = j;
					nearestDist = currWeight;
				}
			}
			// A-B 일때 B에 weight 저장됨.
			spots.get(nearest).setWeight(nearestDist);
			tour.add(spots.get(nearest)); // 가장 가까운 장소를 경로에 추가합니다. -> 가까운 장소의 순서대로 배치됨
			visited[nearest] = true; // 해당 장소를 방문했다고 표시합니다.
		}
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
				} else {
					durationStr = "길찾기 결과를 찾을 수 없음";
				}
			} else { // HTTP_OK 제외하고 오류 발생한 상황
				durationStr = ">>>> 에러났어요 : " + responseCode;
				duration = 0;
			}
		} catch (Exception e) {
//			e.printStackTrace();
		}
		try {
			duration = Integer.parseInt(durationStr);
		} catch (NumberFormatException e) {
			duration = 10800; // 오류상황 일단 3시간으로 측정
		}
		return duration;
	}
}
