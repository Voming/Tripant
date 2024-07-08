package mclass.store.tripant.plan.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceStayDto {
	private Integer index;	     //방문 순서
	private String contentid;    //id
    private String mapx;         // x좌표
    private String mapy;         // y좌표
    private Integer type;        // type
    private String spotStayTime; //장소 머무는 시간
    private Integer weight;      // 전 장소와 걸리는 시간
}
