package mclass.store.tripant.diary.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import mclass.store.tripant.diary.domain.DiaryBoardEntity;
import mclass.store.tripant.diary.domain.DiaryPostEntity;
import mclass.store.tripant.plan.domain.PlanEntity;

@Mapper
public interface DiaryRepository {
	
	    // 일기 목록을 선택하는 메서드
	    public List<DiaryBoardEntity> selectDiaryList();
	    
	    // 여행기 글 등록
	    public void insertDiary(DiaryBoardEntity diary);
	    // 특정 ID의 다이어리 조회
	    DiaryBoardEntity findById(@Param("id") Long id);
	  
	 // PlanEntity 조회
	 public PlanEntity selectPlanById(@Param("planId") Long planId);

}