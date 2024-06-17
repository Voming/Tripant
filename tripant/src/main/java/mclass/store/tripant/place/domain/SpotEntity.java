package mclass.store.tripant.place.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class SpotEntity {
//	TYPE          NOT NULL NUMBER         
//	CONTENTID     NOT NULL NUMBER         
//	ADD1          NOT NULL VARCHAR2(500)  
//	AREACODE      NOT NULL NUMBER         
//	FIRSTIMAGE             VARCHAR2(1000) 
//	MAPX          NOT NULL VARCHAR2(50)   
//	MAPY          NOT NULL VARCHAR2(50)   
//	MLEVEL                 CHAR(2)        
//	TEL                    NVARCHAR2(40)  
//	TITLE         NOT NULL VARCHAR2(500)  
	private Integer type;
	private Integer contentid;
	private String add1;
	private Integer areacode;
	private String firstimage;
	private String mapx;
	private String mapy;
	private String mlevel;
	private String tel;
	private String title;
}