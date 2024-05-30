package mclass.store.tripant.plan.domain;

import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class AreaNameEntity {
	// 지역명만 가져오기
	private Integer areaCode;
	private String areaName;
	private String areaShortName;
	private String areaEngName;
	private String areaFileName;
}
