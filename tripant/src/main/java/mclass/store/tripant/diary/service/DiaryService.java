package mclass.store.tripant.diary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mclass.store.tripant.diary.domain.DiaryBoardEntity;
import mclass.store.tripant.diary.domain.DiaryPostEntity;
import mclass.store.tripant.diary.model.repository.DiaryRepository;
import mclass.store.tripant.plan.domain.PlanEntity;

@Service
public class DiaryService {
	@Autowired
	private DiaryRepository diaryRepository;

// 여행기, 나만의 여행기 글 리스트 가져오기	
	public List<DiaryBoardEntity> selectDiaryList() {
		return diaryRepository.selectDiaryList();
	}

	// 특정 ID의 다이어리 가져오기
	public DiaryBoardEntity findById(Long id) {
		return diaryRepository.findById(id);
	}

	// 게시글 등록하기
	public DiaryBoardEntity save(DiaryBoardEntity diary) {
		diaryRepository.insertDiary(diary);

		// 공개 설정
		diary.setDiaryOpen("0");

		// 비공개 설정
		diary.setDiaryOpen("1");

		diaryRepository.insertDiary(diary);
		return diary;
	}

	// planId로 PLAN 조회
	public PlanEntity selectPlanById(Long planId) {
		return diaryRepository.selectPlanById(planId);
	}

}