package mclass.store.tripant.plan.model.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mclass.store.tripant.plan.domain.AreaNameEntity;
import mclass.store.tripant.plan.model.repostiory.PlanRepository;

@Service
public class PlanService {
	@Autowired
	private PlanRepository boardRepository;
	
	public int selectPlanCount(){
		return boardRepository.selectPlanCount();
	}
	
	public int selectMemCount(){
		return boardRepository.selectMemCount();
	}
	
	public List<AreaNameEntity> selectAreaNameList(){
		return boardRepository.selectAreaNameList();
	}
}
