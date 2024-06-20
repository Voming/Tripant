package mclass.store.tripant.admin.model.repository;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import mclass.store.tripant.admin.domain.AdminBoardEntity;
import mclass.store.tripant.admin.domain.AdminMemEntity;


@Mapper
public interface AdminDao {
	
	//회원정보 한페이지에 나열
	public List<AdminMemEntity> selectMemList();
	
	//회원 등급 변경
	public Integer adminMemRole(Map<String, Object> map);
	
	//회원검색
	public List<AdminMemEntity> search(String memNick);
	
	//전체게시글
	public List<AdminBoardEntity> boardList();
	
	//좋아요 많은 순으로 정렬
	public String boardLike();
	
	//신고게시글
	public List<AdminBoardEntity> complainList();
	
	//신고게시글 검색
	public List<AdminBoardEntity> boardsearch(String memNick);
	
	//신고수 초기화
	public Integer complainReset(Integer diaryId);

	// 결제 취소 페이지
	// 결제 목록
	public List<Map<String, Object>> payList();

	// 결제 취소
	public int payCancel(Map<String, Object> map);
	
	// 상품 관리 페이지
	// 상품추가
	public int itemInsert(Map<String, Object> map); 
	
	//총 페이지 수가 결정됨
	//public int selectTotalPageCount(); 
	//페이지 당 나오는 회원정보 수 뽑기
	//public List<AdminMemEntity> selectPage();
	
}
