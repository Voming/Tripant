package mclass.store.tripant.plan.model.repostiory;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import mclass.store.tripant.place.domain.PlaceAreaEntity;

@Mapper
public interface TimeRepository {
	public int deleteAllPlaceMoveTime();
	public List<PlaceAreaEntity> selectAreaCodeList(int areacode);
	public int selectAreaCodeCount(int areacode);
	
}
