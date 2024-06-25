package mclass.store.tripant.diary.domain;


import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;


@Data
@Component
public class DiaryBoardEntity {

//	DIARY_ID        NOT NULL NUMBER         
//	DIARY_MEM_EMAIL NOT NULL VARCHAR2(100)  
//	DIARY_PLAN_ID   NOT NULL NUMBER         
//	DIARY_TITLE     NOT NULL VARCHAR2(100)  
//	DIARY_CONTENT   NOT NULL VARCHAR2(4000) 
//	DIARY_DATE      NOT NULL DATE           
//	DIARY_OPEN      NOT NULL CHAR(1)        
//	DIARY_VIEWS              NUMBER         
//	DIARY_THEME              VARCHAR2(50)
	private Integer diaryId;
	private String diaryMemEmail;
	private Integer diaryPlanId;
	private String diaryTitle;
	private String diaryContent;
	private List<String> diaryContentArrList;
	private String diaryDate;
	private String diaryOpen;
	private Integer diaryViews;
	private String diaryTheme;
	private String memNick;

	public void setDiaryContent(String diaryContent) {
		this.diaryContent = diaryContent;

		// 추가로 UTF-8 - 4000byte List<String> diaryContentArrList
		//this.diaryContentArrList = StringLengthApi.getMaxByteStringList(diaryContent, 4000, 1000);
//		for (String item : diaryContentArrList) {
//			System.out.println("---- : "+ item.getBytes().length);
//			System.out.println(item);
//		}
	}
}
