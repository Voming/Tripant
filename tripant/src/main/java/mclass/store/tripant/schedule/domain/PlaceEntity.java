package mclass.store.tripant.schedule.domain;

import org.springframework.stereotype.Component;

import lombok.Data;
import mclass.store.tripant.plan.domain.PlanEntity;

@Data
@Component
public class PlaceEntity {
//	TYPE          NOT NULL NUMBER         
//	CONTENTID     NOT NULL NUMBER         
//	CONTENTTYPEID          VARCHAR2(10)   
//	ADD1          NOT NULL VARCHAR2(500)  
//	ADD2                   VARCHAR2(100)  
//	AREACODE      NOT NULL NUMBER         
//	BOOKTOUR               VARCHAR2(10)   
//	CAT1          NOT NULL VARCHAR2(3)    
//	CAT2                   VARCHAR2(5)    
//	CAT3          NOT NULL VARCHAR2(9)    
//	FIRSTIMAGE             VARCHAR2(1000) 
//	FIRSTIMAGE2            VARCHAR2(1000) 
//	CPYRHT_DIV_CD          VARCHAR2(10)   
//	MAPX                   VARCHAR2(50)   
//	MAPY                   VARCHAR2(50)   
//	CREATETIME             DATE           
//	MLEVEL                 CHAR(2)        
//	SIGUNGUCODE            VARCHAR2(2)    
//	TEL                    VARCHAR2(30)   
//	TITLE                  VARCHAR2(500)  
//	MODIFIEDTIME           DATE           
//	GETTIME                DATE    
	
	private Integer type;
    private String gettime;
	
	private Integer contentid;
	private String contenttypeid;
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

}