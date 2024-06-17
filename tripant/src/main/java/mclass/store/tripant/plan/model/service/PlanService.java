package mclass.store.tripant.plan.model.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mclass.store.tripant.place.domain.AreaEntity;
import mclass.store.tripant.place.domain.AreaNameEntity;
import mclass.store.tripant.place.domain.SpotEntity;
import mclass.store.tripant.plan.model.repostiory.PlanRepository;

@Service
public class PlanService {
	@Autowired
	private PlanRepository planRepository;
	
	public int selectPlanCount(){
		return planRepository.selectPlanCount();
	}
	
	public int selectMemCount(){
		return planRepository.selectMemCount();
	}
	
	public List<AreaNameEntity> selectAreaNameList(){
		return planRepository.selectAreaNameList();
	}
	
	public List<AreaNameEntity> selectAreaFindList(String findArea){
		return planRepository.selectAreaFindList(findArea);
	}
	
	public List<AreaEntity> selectAreaInfoList(int areaCode){
		return planRepository.selectAreaInfoList(areaCode);
	}
	
	public String selectAreaShortName(int areaCode) {
		return planRepository.selectAreaShortName(areaCode);
	}

	public List<SpotEntity> selectSpotTypeList(int areaCode, int spotType){
		return planRepository.selectSpotTypeList(areaCode, spotType);
	}
}
