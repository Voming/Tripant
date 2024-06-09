package mclass.store.tripant.admin.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {

	//페이지처리
	 private int pageNum;
	 private int amount;
	 
	 //검색기능
	 private String type;
	 private String  keyword;
	 
public Criteria() {
	this(1, 10);
}	 

public Criteria(int pageNum, int amount) {
	this.pageNum = pageNum;
	this.amount = amount;
 }

public String[] getTypeArr() {
    return type == null? new String[] {}: type.split("");
  }
}
