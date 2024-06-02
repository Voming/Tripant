package mclass.store.tripant.place.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
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
	private Integer areacode;
	
	public PlaceMoveTimeEntity() {
		super();
	}
	
	public PlaceMoveTimeEntity(Integer type, Integer contentidStart, Integer contentidEnd, String moveTime,
			Integer areacode) {
		super();
		this.type = type;
		this.contentidStart = contentidStart;
		this.contentidEnd = contentidEnd;
		this.moveTime = moveTime;
		this.areacode = areacode;
	}
	
}
