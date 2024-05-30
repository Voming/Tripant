package mclass.store.tripant.plan.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class PlaceApiEntity {
	private String add1;
	private String add2;
	private Integer areacode;
	private String booktour;
	private String cat1;
	private String cat2;
	private String cat3;
	private Integer contentid;
	private String contenttypeid;
	private String createtime;
	
	private String firstimage;
	private String firstimage2;
	private String cpyrhtDivCd;
	private String mapx;
	private String mapy;
	private String mlevel;
	private String modifiedtime;
	private String sigungucode;
	private String tel;
	private String title;

	private Integer zipcode;
}