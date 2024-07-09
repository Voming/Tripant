package mclass.store.tripant.plan.domain;

import lombok.Data;

@Data
public class PlanDate {
	private String date; // 20240606
	private String smalldate; // 06/06
	private String day; // 목요일
	private String startTime; // 10:00
	private String endTime; // 22:00
	private String dateTimeRange; // 하루 사용 가능한 초
	private Stay stay;
}
