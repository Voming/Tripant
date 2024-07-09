package mclass.store.tripant.plan.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mclass.store.tripant.place.domain.AreaEntity;
import mclass.store.tripant.place.domain.AreaPointEntity;
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
	
	static int V;
	int dayN = 0;
	int distribute = 0;
	
	// 그래프를 표현 할 List
	static List<List<Node>> graph = new ArrayList<>();

	static List<BeforDto> stayPointList = new ArrayList<>();
	static List<BeforDto> spotPointList = new ArrayList<>();

//	static List<PlacePointEntity> errorPointList = new ArrayList<>();
//	static List<PlacePointEntity> resultPoinList = new ArrayList<>();

	static class Node implements Comparable<Node> {
		private int index;
		private int weight;

		public Node(int index, int weight) {
			this.index = index;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public void planningJson(CalendarPlanEntity calendarPlan, int areaCode) { // planJsonParse
		V = 0;
		dayN = 0;
		distribute = 0;
		
		// 출발 장소 좌표
		AreaEntity startPoint = planRepository.selectAreaInfo(areaCode); 
		
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		CalendarPlanEntity calendarPlan = gson.fromJson(jsonString, CalendarPlanEntity.class);

		// 날짜 별 정보(하루, 숙소)
		List<PlanDate> dateArr = calendarPlan.getDateArr();
		for (int i = 0; i < dateArr.size()-1; i++) {  // 1-2
			Stay stay = dateArr.get(i).getStay();
			System.out.println("====1");
			System.out.println(stay);
			//id만 잘라내기
			String idLongStr = stay.getId();
			int id = Integer.parseInt(idLongStr.substring(10, idLongStr.length()));
			System.out.println("idLongStr -> id : " + id);
			
			stayPointList.add(new BeforDto( i, dateArr.get(i).getDate(), id, 
					stay.getType(), "", //숙소는 머무는 시간 없음
					stay.getMapx(), stay.getMapy()));
		}
		System.out.println(stayPointList);

		// 선택 한 장소
		List<Spot> spotArr = calendarPlan.getSpotArr();
		for (int i = 0; i < spotArr.size(); i++) {
			Spot spot = spotArr.get(i);
			
			//id만 잘라내기
			String idLongStr = spot.getId();
			int id = Integer.parseInt(idLongStr.substring(10, idLongStr.length()));
			System.out.println("idLongStr -> id : " + id);
			
			spotPointList.add(new BeforDto( i, "", //장소는 아직 방문 날짜 정해지지 않음
					id, spot.getType(),
					spot.getSpotTime(), 
					spot.getMapx(), spot.getMapy()));
		}
		System.out.println(spotPointList); //기본 spot, stay 설정 완료---------------------------------

		V = spotPointList.size(); // 정점 개수 -> 선택한 장소 수
		System.out.println("V : " + V);
		dayN = dateArr.size(); // 여행할 날짜 수
		System.out.println("dayN : " + dayN);

//		1. 총 날짜 수로 장소 개수 나누기
		distribute = (int) Math.ceil(((double) V / dayN)); // 하루에 방문할 장소 수
		System.out.println("distribute : " + distribute);
		
		/*
		 * //방문 날짜, 리스트 Map => 전체 일정 정보 담김
		Map<Integer, List<AfterDto>> planMap = new HashMap<>(); 

//		1-1. 만약 날짜 수  = 나눈 장소 수(1) -> 그냥 출-장-도 로 모든 날짜 배치 ====================================
		if (distribute == 1) { // 1인 경우 : 선택한 장소 수 == 날짜 수 
			for (int i = 0; i < dateArr.size(); i++) { // 날짜별 반복문
				List<AfterDto> listForDay = new ArrayList<>();
				//먼저 일마다 장소 추가해놓기
				for(int j = 1; j <3; j++) {  // 출-장-도
					if(i == 1) {
						listForDay.add(new AfterDto(dateArr.get(i).getDate(), 100, startPoint.getAreaCode(),
								j, "", startPoint.getAreaX(), startPoint.getAreaY(), "" ));
					} else if(i == 1) { 
						listForDay.add(new AfterDto(
								dateArr.get(i).getDate(), 
								spotPointList.get(i).getType(),
								spotPointList.get(i).getContentId(),
								j,
								"",
								spotPointList.get(i).getMapx(),
								spotPointList.get(i).getMapy(),
								""));
					} else {
						listForDay.add(new AfterDto(
								dateArr.get(i).getDate(), 
								stayPointList.get(i).getType(),
								stayPointList.get(i).getContentId(),
								j,
								"",
								stayPointList.get(i).getMapx(),
								stayPointList.get(i).getMapy(),
								""));
					}
				}
				planMap.put(i, listForDay);
				//각각 이동시간 구하기
			}
		}
//		1-2. 만약 날짜 수  < 나눈 장소 수(2) -> 출과 가까운걸 먼저 배치
		else if (distribute == 2) { // 2인 경우
			// 1. 선택한 장소 수 == 날짜 수
			// 2. 선택한 장소 수 < 날짜 수 * distribute => 마지막 날에는 나머지로 들어감
		}
//		1-3. 만약 날짜 수  < 나눈 장소 수(3) 
//		      => 출과 가까운거 빼냄 , 도와 가까운 거 빼냄 -> 그대로 배치
		else if (distribute == 3) {
			// 1. 선택한 장소 수 == 날짜 수
			// 2. 선택한 장소 수 < 날짜 수 * distribute => 마지막 날에는 나머지로 들어감
		}
//		1-4. 만약 날짜 수  < 나눈 장소 수(3보다 큼) 
//		      => 출과 가까운거 빼냄 , 도와 가까운 거 빼냄  --> 나머지로 다익스트라
		else {
			// 1. 선택한 장소 수 == 날짜 수
			// 2. 선택한 장소 수 < 날짜 수 * distribute => 마지막 날에는 나머지로 들어감
		}
		*/

		// 출발지에서 가장 가까운 장소 구하기
		int weight[] = new int[V];
		int minIndex = 0;
		int min = 0;
		
		for (int i = 0; i < V; i++) {

			double startx = Double.parseDouble(startPoint.getAreaX());
			double starty = Double.parseDouble(startPoint.getAreaY());
			double endx = Double.parseDouble(spotPointList.get(i).getMapx());
			double endy = Double.parseDouble(spotPointList.get(i).getMapy());

			String durationStr = getduration(startx, starty, endx, endy);
			if (durationStr.equals("길찾기 결과를 찾을 수 없음") || durationStr.equals("")) {
				weight[i] = 10800; // 3시간 //TODO
				System.out.println("!!!!!!!!!!!!!!!!!!");
			} else {
				System.out.println(durationStr);
				weight[i] = Integer.parseInt(durationStr);
			}
		}

		min = weight[0]; // 가장 가까운 곳이 몇번째에 있는지
		for (int i = 0; i < V; i++) {
			if (min > weight[i]) {
				min = weight[i];
				minIndex = i;
			}
		}
		
		int tempIdx = spotPointList.get(minIndex).getOrder();
		spotPointList.get(minIndex).setOrder( spotPointList.get(0).getOrder() );
		spotPointList.get(0).setOrder(tempIdx);
		
		Collections.swap(spotPointList, 0, minIndex); // 위치 변경

		System.out.println(spotPointList.get(0));

		planning();

	}

	public void planning() { // 정점 세팅 및 알고리즘 실행
		for (int i = 0; i < V; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < V; i++) {
			BeforDto start = spotPointList.get(i); // 출발 노드
			double startx = Double.parseDouble(start.getMapx());
			double starty = Double.parseDouble(start.getMapy());
			for (int j = 0; j < V; j++) {
				if (j != i) {
					BeforDto end = spotPointList.get(j); // 도착 노드
					double endx = Double.parseDouble(end.getMapx());
					double endy = Double.parseDouble(end.getMapy());
					System.out.println(startx + " : " + starty + " : " + endx + " : " + endy);

					String durationStr = getduration(startx, starty, endx, endy);
					int weight = 0;
					if (durationStr.equals("길찾기 결과를 찾을 수 없음") || durationStr.equals("")) {
//						errorPointList.add(start);
						weight = 10800; // 3시간 //TODO
						System.out.println("!!!!!!!!!!!!!!!!!!");
					} else {
						System.out.println(durationStr);
						weight = Integer.parseInt(durationStr);
					}
					graph.get(i).add(new Node(end.getOrder(), weight));
					graph.get(j).add(new Node(start.getOrder(), weight));
				}
			}
		}
		Dijkstra(0);
	}

	private static void Dijkstra(int index) {
		Map<Integer, Integer> result = new HashMap<>();
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] distance = new int[V]; // 최단 거리를 저장할 변수

		// distance값 초기화.
		Arrays.fill(distance, Integer.MAX_VALUE);

		// 시작노드값 초기화.
		distance[index] = 0;
		pq.offer(new Node(index, 0));

		while (!pq.isEmpty()) {
			// 큐에서 노드 꺼내기
			Node node = pq.poll();
			int nodeIndex = node.index; // 꺼낸 노드의 인덱스
			int weight = node.weight;
			/**
			 * 큐는 최단거리를 기준으로 오름차순 정렬되고 있습니다. 만약 현재 꺼낸 노드의 거리가 최단거리테이블의 값보다 크다면 해당 노드는 이전에
			 * 방문된 노드이므로, 해당노드와 연결 된 노드를 탐색하지 않고 큐에서 다음 노드를 꺼냅니다.
			 */
			if (weight > distance[nodeIndex]) {
				continue;
			}
			for (Node linkedNode : graph.get(nodeIndex)) {
				System.out.println("=====1");
				if (weight + linkedNode.weight < distance[linkedNode.index]) {
					System.out.println("=====2");
					System.out.println(weight + linkedNode.weight);
					System.out.println(linkedNode.index);
					System.out.println(distance[linkedNode.index]);
					// 최단 테이블 갱신
					distance[linkedNode.index] = weight + linkedNode.weight;
					
					// 갱신 된 노드를 우선순위 큐에 넣어
					pq.offer(new Node(linkedNode.index, distance[linkedNode.index]));
				}
			}

			// 결과값 출력
			for (int i = 0; i < V; ++i) {
				if (distance[i] == Integer.MAX_VALUE)
					System.out.print("∞ ");
				else {
					System.out.print(distance[i] + " ");
				}
			}
			System.out.println();

		}

//		
//		int weight[] = new int[V];
//		int minIndex = 0;
//		int min = 0;
//		min = weight[0];
//		// 가장 가까운 곳이 몇번째에 있는지
//		for (int i = 0; i < V; i++) {
//			if (min < weight[i]) {
//				min = weight[i];
//				minIndex = i;
//			}
//		}
//		
//		
//		for (int i = 0; i < V; i++) { // 시간 적은 순서대로 다시 저장
//			int min =  distance[i];
//			for(int j = i+1; j < V; j++) {
//				if(min < distance[j]) {
//					resultPoinList.add(i, new PlacePointEntity(i, spotPointList.get(i).getContentid(),
//							spotPointList.get(i).getMapx(), spotPointList.get(i).getMapy(), distance[i]));
//				}
//				
//			}
//			
//			
//		}
//		

		// 그냥 넣고 swap, index 다시 붙여주기

		System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(spotPointList));
	}

	@Value("${kakao.map.rest.api}")
	private String apikey;

	// 지점 간 이동시간 구하기
	public String getduration(double startLng, // 시작 경도
			double startLat, // 시작 위도
			double endLng, // 도착 경도
			double endLat // 도착 위도
	) {
		String aurlStr = String.format(
				"https://apis-navi.kakaomobility.com/v1/directions?origin=%f,%f&destination=%f,%f&priority=TIME&summary=true",
				startLng, startLat, endLng, endLat);
		String duration = "";
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
					duration = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONObject("summary")
							.getString("duration");
				}
			} else { // HTTP_OK 제외하고 오류 발생한 상황
				return ">>>> 에러났어요 : " + responseCode;
			}
		} catch (Exception e) {
			System.out.println(" >>>>>>>>>>>>>>>>ERROR 길찾기 결과를 찾을 수 없음");
			duration = "길찾기 결과를 찾을 수 없음";
		}
		return duration;
	}
}
