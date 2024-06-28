package mclass.store.tripant.diary.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mclass.store.tripant.diary.domain.DiaryBoardEntity;
import mclass.store.tripant.diary.domain.LikeEntity;
import mclass.store.tripant.diary.domain.WritePlanTitleEntity;

@Mapper
public interface DiaryRepository {

	// 전체글 목록
	public List<DiaryBoardEntity> selectDiaryList(@Param("areaname") String areaname, @Param("maxNum") int maxNum, @Param("memEmail") String memEmail);
	// 최신순 정렬
	List<DiaryBoardEntity> selectLatest(@Param("areaname") String areaname, @Param("maxNum") int maxNum, @Param("memEmail") String memEmail);
	// 좋아요 정렬
	List<DiaryBoardEntity> selectLikesPopular(@Param("areaname") String areaname, @Param("maxNum") int maxNum, @Param("memEmail") String memEmail);
	// 조회수 정렬
	List<DiaryBoardEntity> selectViewsPopular(@Param("areaname") String areaname, @Param("maxNum") int maxNum, @Param("memEmail") String memEmail);
	
	// 나의 모든 글 조회
	public List<DiaryBoardEntity> selectMyDiaryList(@Param("email") String diaryMemEmail, @Param("maxNum") int maxNum);

	// 여행기 글 등록
	public void insertDiary(DiaryBoardEntity diary);

	// 특정 ID의 다이어리 조회
	DiaryBoardEntity findById(@Param("id") Long id);

	DiaryBoardEntity selectDiaryById(@Param("diaryId") int diaryId);

	List<WritePlanTitleEntity> selectPlanById(String memberEmail);

	// 글 카운트 증가 메서드
	void incrementDiaryViews(@Param("diaryId") int diaryId);
	
	// 한개의 여행기에 여러명의 이메일 계정들이 누른 하트 개수
	int selectDiaryLike(@Param("diaryId") int diaryId);
	
	int insertDiaryLike(@Param("diaryId") int diaryId, @Param("memEmail") String memEmail);

	int deleteDiaryLike(@Param("diaryId") int diaryId, @Param("memEmail") String memEmail);



	
	// 다이어리 삭제
	  int deleteDiaryById(@Param("diaryId") int diaryId , @Param("memEmail") String memEmail);
	

	
	// 이미지 url 받아오기

}
