package mclass.store.tripant.plan.domain;

import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class AreaNameEntity {
	private Integer areaCode;
	private String areaName;
	private String areaShortName;
	private String areaEngName;
	private String areaFileName;
}
