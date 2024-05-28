package mclass.store.tripant.plan.model.repostiory;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlanRepository {
	public int selectPlanCount();
	public int selectMemCount();
}
