package mclass.store.tripant.store.domain;

import lombok.Data;

@Data
public class ItemEntity {
	private String itemCode;
	private String itemName;
	private String itemPrice;
	private Integer itemDur;
}
