package mclass.store.tripant.diary.domain;

import org.springframework.stereotype.Component;
import lombok.Data;

@Data@Component
public class BuyThemeEntity {

	private String itemCode;
	private String itemName;
	private String itemColor;
	private String memEmail;
}
