package mclass.store.tripant.schedule.model.repostiory;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScheduleRepository {
	public int insertPlace();
}
