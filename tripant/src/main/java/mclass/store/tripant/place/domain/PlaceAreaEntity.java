package mclass.store.tripant.place.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class PlaceAreaEntity {
	private Integer type;
	private Integer contentid;
	private String mapx;
	private String mapy;
	private Integer areacode;
}
