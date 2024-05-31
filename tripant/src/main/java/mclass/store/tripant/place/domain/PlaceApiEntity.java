package mclass.store.tripant.place.domain;


import org.springframework.stereotype.Component;

import lombok.Data;
import mclass.store.tripant.plan.domain.PlanEntity;

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

	public PlaceApiEntity() {
		super();
	}
	
	public PlaceApiEntity(String add1, String add2, Integer areacode, String booktour, String cat1, String cat2,
			String cat3, Integer contentid, String contenttypeid, String createtime, String firstimage,
			String firstimage2, String cpyrhtDivCd, String mapx, String mapy, String mlevel, String modifiedtime,
			String sigungucode, String tel, String title, Integer zipcode) {
		super();
		this.add1 = add1;
		this.add2 = add2;
		this.areacode = areacode;
		this.booktour = booktour;
		this.cat1 = cat1;
		this.cat2 = cat2;
		this.cat3 = cat3;
		this.contentid = contentid;
		this.contenttypeid = contenttypeid;
		this.createtime = createtime;
		this.firstimage = firstimage;
		this.firstimage2 = firstimage2;
		this.cpyrhtDivCd = cpyrhtDivCd;
		this.mapx = mapx;
		this.mapy = mapy;
		this.mlevel = mlevel;
		this.modifiedtime = modifiedtime;
		this.sigungucode = sigungucode;
		this.tel = tel;
		this.title = title;
		this.zipcode = zipcode;
	}
}