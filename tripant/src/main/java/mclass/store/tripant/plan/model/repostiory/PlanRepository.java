package mclass.store.tripant.plan.model.repostiory;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import mclass.store.tripant.plan.domain.AreaNameEntity;

@Mapper
public interface PlanRepository {
	public int selectPlanCount();
	public int selectMemCount();
	public List<AreaNameEntity> selectAreaNameList();
}
