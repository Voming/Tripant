package mclass.store.tripant.admin.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AdminBoardEntity {

	private String diaryTitle;
	private String diaryMemEmail;
	private String diaryDate;
	private Integer diaryViews;
}
