package mclass.store.tripant.diary.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import mclass.store.tripant.diary.domain.DiaryBoardEntity;

import mclass.store.tripant.diary.domain.WritePlanTitleEntity;


@Mapper
public interface DiaryRepository {

	// 일기 목록을 선택하는 메서드
	public List<DiaryBoardEntity> selectDiaryList(@Param("areaname") String areaname, @Param("maxNum") int maxNum);

	// 여행기 글 등록
	public void insertDiary(DiaryBoardEntity diary);

	// 특정 ID의 다이어리 조회
	DiaryBoardEntity findById(@Param("id") Long id);

	List<WritePlanTitleEntity> selectPlanById(String memberEmail);
    // 좋아요 카운트 증가 메서드
    void incrementLikes(Long diaryId);
    // 글 더보기 클릭시 
    public List<DiaryBoardEntity> selectDiaryListMore();
}
  