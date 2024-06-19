package mclass.store.tripant.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mclass.store.tripant.admin.domain.AdminBoardEntity;
import mclass.store.tripant.admin.domain.AdminMemEntity;
import mclass.store.tripant.admin.model.repository.AdminDao;

@Service
public class AdminSerivce {
	
	@Autowired
	private AdminDao admindao;
	
	//회원리스트
	public List<AdminMemEntity> selectMemList(){
		return admindao.selectMemList();
	}
	
	//등급변경
	public Integer adminMemRole(Map<String, Object> map) {
		
		return admindao.adminMemRole(map);
	}
	
	//회원검색
	public List<AdminMemEntity> search(String memNick){
		return admindao.search(memNick);
	}
	
	//게시글리스트
	public List<AdminBoardEntity> boardList(){
		return admindao.boardList();
	}

	//좋아요 정렬
	public String boardLikes() {
		
		return admindao.boardLike();
	}
	
	//신고게시글
	public List<AdminBoardEntity> complainList(){
		return admindao.complainList();
	}
	
	//신고게시글 검색
	public List<AdminBoardEntity> boardSearch(String memNick){
		return admindao.boardsearch(memNick);
	}
	
	//신고수 초기화
	public Integer complainReset(Integer diaryId) {
		return admindao.complainReset(diaryId);
	}
	
	// 결제 취소 페이지
	// 결제 목록
	public List<Map<String, Object>> payList(){
		return admindao.payList();
	}

	// 결제 취소
	public Integer payCancel(Map<String, Object> map) {
		return admindao.payCancel(map);
	}
}
