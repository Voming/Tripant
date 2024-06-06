package mclass.store.tripant.trip.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import mclass.store.tripant.trip.domain.TripListEntity;

@Mapper
public interface TripListRepository {
	
	// 여행목록 
	public List<TripListEntity>  selectTripList(String memEmail);
	
	//여행삭제(planId -> PK)
//	public int deletePlan(int planId);
}