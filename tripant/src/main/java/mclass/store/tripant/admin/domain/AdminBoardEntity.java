package mclass.store.tripant.admin.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AdminBoardEntity {

	private Integer diaryId;
	private String diaryTitle;
	private String diaryDate;
	private Integer diaryViews;
	private String likes;
	private String memNick;
}
