package mclass.store.tripant.diary.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import mclass.store.tripant.diary.domain.DiaryBoardEntity;

import mclass.store.tripant.diary.domain.WritePlanTitleEntity;


@Mapper
public interface DiaryRepository {

	// 전체글 목록
	public List<DiaryBoardEntity> selectDiaryList(@Param("areaname") String areaname, @Param("maxNum") int maxNum);
    //나의 모든 글 조회
	public List<DiaryBoardEntity> selectMyDiaryList(@Param("email")String diaryMemEmail, @Param("maxNum") int maxNum);
	
	// 여행기 글 등록
	public void insertDiary(DiaryBoardEntity diary);

	// 특정 ID의 다이어리 조회
	DiaryBoardEntity findById(@Param("id") Long id);

	List<WritePlanTitleEntity> selectPlanById(String memberEmail);
    // 좋아요 카운트 증가 메서드
    void incrementLikes(Long diaryId);
    



   
}
  