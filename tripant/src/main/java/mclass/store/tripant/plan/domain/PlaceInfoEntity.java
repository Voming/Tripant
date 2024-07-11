package mclass.store.tripant.plan.domain;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class PlaceInfoEntity {
//	TYPE, CONTENTID, AREACODE, MAPX, MAPY, TITLE
	private Integer type;
	private Integer contentid;
	private Integer areacode;
	private String mapx;
	private String mapy;
}
