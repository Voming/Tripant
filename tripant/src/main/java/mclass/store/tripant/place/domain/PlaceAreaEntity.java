package mclass.store.tripant.place.domain;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class PlaceAreaEntity { //지역 2개씩 묶을때 사용
	private Integer type;
	private Integer contentid;
	private String mapx;
	private String mapy;
	private Integer areacode;
}
