package mclass.store.tripant.place.domain;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor

public class PlaceMoveTimeEntity {
//	TYPE            NOT NULL NUMBER       
//	CONTENTID_START NOT NULL NUMBER       
//	CONTENTID_END   NOT NULL NUMBER       
//	MOVE_TIME       NOT NULL VARCHAR2(50) 
//	AREACODE        NOT NULL NUMBER 
	
	private Integer type;
	private Integer contentidStart;
	private Integer contentidEnd;
	private String moveTime;
}
