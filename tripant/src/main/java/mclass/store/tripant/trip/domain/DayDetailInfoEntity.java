package mclass.store.tripant.trip.domain;

import java.sql.Date;

import lombok.Data;

//@Data
public class DayDetailInfoEntity {
	private Integer type;
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
