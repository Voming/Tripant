package mclass.store.tripant.plan.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mclass.store.tripant.plan.domain.CalendarPlanEntity;
import mclass.store.tripant.plan.domain.PlacePointEntity;
import mclass.store.tripant.plan.domain.PlanDate;
import mclass.store.tripant.plan.domain.Spot;
import mclass.store.tripant.plan.domain.Stay;

@Service
public class PlanningAlgorithm {
	// 그래프를 표현 할 List
	static List<List<Node>> graph = new ArrayList<>();
	static int V;

	static List<PlacePointEntity> stayPointArr = new ArrayList<>();

	static List<PlacePointEntity> spotPointArr = new ArrayList<>();

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

	public void planJsonParse(String jsonString) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		CalendarPlanEntity calendarPlan = gson.fromJson(jsonString, CalendarPlanEntity.class);

		List<PlanDate> dateArr = calendarPlan.getDateArr();
		for (int i = 0; i < dateArr.size(); i++) {
			Stay stay = dateArr.get(i).getStay();
			stayPointArr.add(new PlacePointEntity(i, stay.getId(), stay.getMapx(), stay.getMapy()));
		}
		System.out.println(stayPointArr);

		List<Spot> spotArr = calendarPlan.getSpotArr();
		for (int i = 0; i < spotArr.size(); i++) {
			spotPointArr.add(new PlacePointEntity(i, spotArr.get(i).getId(), spotArr.get(i).getMapx(),
					spotArr.get(i).getMapy()));
		}
		System.out.println(spotPointArr);
		planning();
	}

	public void planning() { // 정점 세팅 및 알고리즘 실행
		V = spotPointArr.size(); // 정점 개수
		System.out.println("V " + V);
		int E = V * (V - 1); // 간선 개수

		for (int i = 0; i < V; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V - 1; j++) {
				if(j != i) {
					PlacePointEntity start = spotPointArr.get(i); // 출발 노드
					PlacePointEntity end = spotPointArr.get(j);// 출발 노드

					double startx = Double.parseDouble(start.getMapx());
					double starty = Double.parseDouble(start.getMapy());
					double endx = Double.parseDouble(end.getMapx());
					double endy = Double.parseDouble(end.getMapy());

					System.out.println(startx + " : " + starty + " : " + endx + " : " + endy);

					String durationStr = getduration(startx, starty, endx, endy);
					System.out.println(durationStr);
//					int weight = Integer.parseInt(durationStr);
//					System.out.println("start" + weight);

//					graph.get(i).add(new Node(end.getIndex(), weight));
//					graph.get(j).add(new Node(start.getIndex(), weight));
				}
			}
		}
		// Dijkstra(0);
	}

	private static void Dijkstra(int index) {
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
				if (weight + linkedNode.weight < distance[linkedNode.index]) {
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
				else
					System.out.print(distance[i] + " ");
			}
			System.out.println();
		}
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

				// JSON 파싱하여 duration 값만 추출
				JSONObject jsonObject = new JSONObject(response.toString());
				duration = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONObject("summary")
						.getString("duration");
			} else {
				return ">>>> 에러났어요 : " + responseCode;
			}
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println(" >>>>>>>>>>>>>>>>ERROR 길찾기 결과를 찾을 수 없음");
			duration = "길찾기 결과를 찾을 수 없음";
		}
		return duration;
	}

}
