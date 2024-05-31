package mclass.store.tripant.place.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class PlaceEntity {
//	TYPE          NOT NULL NUMBER         
//	CONTENTID     NOT NULL NUMBER         
//	CONTENTTYPEID NOT NULL VARCHAR2(10)   
//	ADD1          NOT NULL VARCHAR2(500)  
//	ADD2                   VARCHAR2(100)  
//	AREACODE      NOT NULL NUMBER         
//	BOOKTOUR               VARCHAR2(10)   
//	CAT1          NOT NULL VARCHAR2(3)    
//	CAT2          NOT NULL VARCHAR2(5)    
//	CAT3          NOT NULL VARCHAR2(9)    
//	FIRSTIMAGE             VARCHAR2(1000) 
//	FIRSTIMAGE2            VARCHAR2(1000) 
//	CPYRHT_DIV_CD          VARCHAR2(10)   
//	MAPX          NOT NULL VARCHAR2(50)   
//	MAPY          NOT NULL VARCHAR2(50)   
//	CREATETIME    NOT NULL DATE           // VARCHAR2(30)     
//	MLEVEL                 CHAR(2)        
//	SIGUNGUCODE   NOT NULL VARCHAR2(2)    
//	TEL                    VARCHAR2(30)   
//	TITLE         NOT NULL VARCHAR2(500)  
//	MODIFIEDTIME  NOT NULL DATE     // VARCHAR2(30)           
//	GETTIME       NOT NULL DATE  // VARCHAR2(30)     
	
	private Integer type;
    private String gettime;
	
	private Integer contentid;
	private Integer contenttypeid;
	private String add1;         
	private String add2;       
	private Integer areacode;     
    private String booktour;     
    private String cat1;       
    private String cat2;         
    private String cat3; 
    
    private String firstimage;  
    private String firstimage2;  
    private String cpyrhtDivCd;
    private String mapx;         
    private String mapy;         
    private String createtime;   
    private String mlevel;       
    private String sigungucode;  
    private String tel;          
    private String title; 
    
    private String modifiedtime;
    
	public PlaceEntity() {
		super();
	} 

	public PlaceEntity(Integer type, String gettime, Integer contentid, Integer contenttypeid, String add1, String add2,
			Integer areacode, String booktour, String cat1, String cat2, String cat3, String firstimage,
			String firstimage2, String cpyrhtDivCd, String mapx, String mapy, String createtime, String mlevel,
			String sigungucode, String tel, String title, String modifiedtime) {
		super();
		this.type = type;
		this.gettime = gettime;
		this.contentid = contentid;
		this.contenttypeid = contenttypeid;
		this.add1 = add1;
		this.add2 = add2;
		this.areacode = areacode;
		this.booktour = booktour;
		this.cat1 = cat1;
		this.cat2 = cat2;
		this.cat3 = cat3;
		this.firstimage = firstimage;
		this.firstimage2 = firstimage2;
		this.cpyrhtDivCd = cpyrhtDivCd;
		this.mapx = mapx;
		this.mapy = mapy;
		this.createtime = createtime;
		this.mlevel = mlevel;
		this.sigungucode = sigungucode;
		this.tel = tel;
		this.title = title;
		this.modifiedtime = modifiedtime;
	}
}