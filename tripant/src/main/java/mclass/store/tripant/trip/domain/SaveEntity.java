package mclass.store.tripant.trip.domain;

import lombok.Data;

@Data
public class SaveEntity {
	public String planDate;
	public String planId;
	public String planType;
	public String contentId;
	public String planOrder;
	public String stayTime;
	public String memo;
}
