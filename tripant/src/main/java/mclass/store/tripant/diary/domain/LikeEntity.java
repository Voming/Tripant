package mclass.store.tripant.diary.domain;

import org.springframework.stereotype.Component;
import lombok.Data;

@Data@Component
public class LikeEntity {

//	DIARY_ID  NOT NULL NUMBER        
//	MEM_EMAIL NOT NULL VARCHAR2(100)
	private int diaryId;
	private String memEmail; //좋아요 수
}
