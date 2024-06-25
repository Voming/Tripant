package mclass.store.tripant.diary.service;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mclass.store.tripant.diary.domain.DiaryBoardEntity;

import mclass.store.tripant.diary.domain.WritePlanTitleEntity;
import mclass.store.tripant.diary.model.repository.DiaryRepository;

@Service
public class DiaryService {
	@Autowired
	private DiaryRepository diaryRepository;

	// 전체글 리스트 가져오기
	public List<DiaryBoardEntity> selectDiaryList(String areaname, int maxNum) {
		return diaryRepository.selectDiaryList(areaname, maxNum);
	}

	// mydiary 모든 내가 쓴 글()조회해서 가져오기
	public List<DiaryBoardEntity> selectMyDiaryList(String email, int maxNum) {
		return diaryRepository.selectMyDiaryList(email, maxNum);
	}

	// 특정 ID의 다이어리 가져오기
	public DiaryBoardEntity findById(Long id) {
		return diaryRepository.findById(id);
	}

	// 다이어리 등록하기
	public DiaryBoardEntity save(DiaryBoardEntity diary) {
		diaryRepository.insertDiary(diary);
		return diary;
	}

	// 회원의 모든 여행 계획 가져오기
	public List<WritePlanTitleEntity> getAllPlans(String memberEmail) {
		return diaryRepository.selectPlanById(memberEmail);
	}

	// 다이어리 좋아요 수 증가하기
	public boolean isDiaryLikedByUser(int diaryId, String memEmail) {
		return diaryRepository.selectDiaryLike(diaryId, memEmail) > 0;
	}

	public void likeDiary(int diaryId, String memEmail) {
		if (!isDiaryLikedByUser(diaryId, memEmail)) {
			diaryRepository.insertDiaryLike(diaryId, memEmail);
		}
	}

	public void unlikeDiary(int diaryId, String memEmail) {
		if (isDiaryLikedByUser(diaryId, memEmail)) {
			diaryRepository.deleteDiaryLike(diaryId, memEmail);
		}
	}

	// 최신순 정렬
	public List<DiaryBoardEntity> getLatestDiaries() {
		return diaryRepository.selectLatest();
	}

	// 좋아요순 정렬
	public List<DiaryBoardEntity> getPopularDiaries() {
		return diaryRepository.selectPopular();
	}

	// 조회수 증가
	public DiaryBoardEntity getDiaryById(int diaryId) {
		diaryRepository.incrementDiaryViews(diaryId);
		return diaryRepository.selectDiaryById(diaryId);

	}

	// 다이어리 삭제
	public int deleteDiary(int diaryId) {
        try {
            // DiaryRepository를 통해 삭제 메서드 호출
            diaryRepository.deleteDiaryById(diaryId);
            return 1; // 삭제 성공
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // 삭제 실패
        }
}
}