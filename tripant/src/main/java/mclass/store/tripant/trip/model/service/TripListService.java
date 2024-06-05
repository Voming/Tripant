package mclass.store.tripant.trip.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mclass.store.tripant.trip.domain.TripListEntity;
import mclass.store.tripant.trip.model.repository.TripListRepository;

@Service
@RequiredArgsConstructor
public class TripListService {
	
	private final TripListRepository repository;
	
	//여행목록
	public List<TripListEntity> selectTripList(String memEmail){
		return repository.selectTripList(memEmail);
	}
	//여행삭제(planId -> PK)
//	public int deletePlan(int planId) {
//		return repository.deletePlan(planId);
//	}
}
