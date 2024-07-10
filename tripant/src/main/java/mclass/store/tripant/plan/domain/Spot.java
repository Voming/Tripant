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
	
	private Integer spotDayIdx;
	private String spotDay;
	private Integer spotOrder;
}
