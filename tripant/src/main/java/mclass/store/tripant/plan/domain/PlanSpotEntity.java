package mclass.store.tripant.plan.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class PlanSpotEntity {
//	SPOT_DAY       NOT NULL DATE          
//	SPOT_PLAN_ID   NOT NULL NUMBER        
//	SPOT_TYPE      NOT NULL NUMBER        
//	SPOT_CONTENTID NOT NULL NUMBER        
//	SPOT_ORDER              NUMBER        
//	SPOT_STAY_TIME          VARCHAR2(50)  
//	SPOT_MEMO               VARCHAR2(300) 
	
	private String spotDay;        //방문일            
	private Integer spotPlanId;    //plan id        
	private Integer spotType;      //장소 타입          
	private Integer spotContentid; //장소 id          
	private Integer spotOrder;     //해당 방문 날짜 순서    
	private String spotStayTime;   //머무는 시간         
	private String spotMemo;       //메모             
}
