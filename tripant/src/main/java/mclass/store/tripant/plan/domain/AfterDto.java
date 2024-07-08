package mclass.store.tripant.plan.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AfterDto {
	private String onDate;       //방문 날짜    -> 나중에 채워짐
	private Integer type;     	 //장소 타입    
	private Integer contentId;	 //장소 ID     
	private Integer order;     	 //일별 장소 방문순서  -> 나중에 채워짐
	private String holdTime;   	 //머무는 시간 초단위 (기본2시간)  
	private String mapx;   		 //x좌표
	private String mapy;   		 //y좌표
	private String weight;       //이동시간   -> 나중에 채워짐
}
