package mclass.store.tripant.trip.domain;

import java.sql.Date;
import java.util.List;

import lombok.Data;

//@Data
public class DayEntity {
	//중복되는 값과 중복되지 않는 데이터 Entity 구분후 연결
	private Date travelDate;
	private String scheduleStart;
	private String scheduleEnd;
	private List<DayDetailInfoEntity> dayDetailInfoEntity;
}
