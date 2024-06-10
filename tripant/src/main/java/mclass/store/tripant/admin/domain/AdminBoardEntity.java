package mclass.store.tripant.admin.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AdminBoardEntity {

	//TODO view 만들고 수정
	private int diaryId;
	private String diaryTitle;
	private String diaryMemEmail;
	private String diaryDate;
	private Integer diaryViews;
}
