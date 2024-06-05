package mclass.store.tripant.trip.domain;

import lombok.Data;

@Data
public class TripListEntity {
	private Integer planId ;
	private Integer planAreaCode ;
	private String planTitle ;
	private String planStartDay ;
	private String planEndDay ;
	private String planMakeDay ;
	private String planDeleteDay ;
}
