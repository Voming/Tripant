package mclass.store.tripant.trip.domain;

import java.sql.Date;

import lombok.Data;

//@Data
public class DayDetailInfoEntity {
	private Integer type;
	private Integer travelOrder;
	private String stayTime;
	private String spotMemo;
	private Integer contentid;
	private String title;
	private String address;
	private String firstimage;
	private String lng;
	private String lat;
}
