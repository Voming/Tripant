package mclass.store.tripant.plan.domain;

import lombok.Data;

@Data
public class Spot {
	private String id;
	private String title;
	private String mapx;
	private String mapy;
	private Integer spotTime;
	private Integer type;
	
	private Integer weight;
	
	private Integer spotDayIdx;  // 일차 -> 0부터 시작
	private String spotDay;      // 날짜
	private Integer spotOrder;   // 날짜별 방문순서
}
