package mclass.store.tripant.plan.model.repostiory;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import mclass.store.tripant.place.domain.PlaceMapEntity;


@Mapper
public interface TimeRepository {
	public int deleteAllPlaceMoveTime();
	public List<PlaceMapEntity> selectPlaceMapList(int areacode);
}
