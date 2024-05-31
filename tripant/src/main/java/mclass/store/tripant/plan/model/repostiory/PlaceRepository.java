package mclass.store.tripant.plan.model.repostiory;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.scheduling.annotation.Async;

import mclass.store.tripant.place.domain.PlaceEntity;

@Mapper
public interface PlaceRepository {

	public int insertPlace(List<PlaceEntity> dtolist);

}
