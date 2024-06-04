package mclass.store.tripant.place.domain;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PlaceMapEntity {
	private Integer type;
	private Integer contentid;
	private String mapx; //경도
	private String mapy; //위도
}
