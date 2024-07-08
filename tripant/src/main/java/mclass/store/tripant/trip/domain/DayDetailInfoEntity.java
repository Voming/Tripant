package mclass.store.tripant.trip.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class DayDetailInfoEntity {
	private String placeType;
	private Integer contentid;
	private Integer travelOrder;
	private String stayTime;
	private String memo;
	private String title;
	private String address;
	private String firstimage;
	private String lng;
	private String lat;
}
