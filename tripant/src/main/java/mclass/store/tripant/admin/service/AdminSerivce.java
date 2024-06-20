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
	
	//게시글 검색(키워드 선택)
	public List<AdminBoardEntity> keywordsearch(String memNick, String title){
		return admindao.keywordsearch(memNick,title);
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
	public int payCancel(Map<String, Object> map) {
		return admindao.payCancel(map);
	}
	
	// 상품 관리 페이지
	// 상품목록
	public List<Map<String, Object>> itemList(){
		return admindao.itemList();
	}
	// 상품정보
	public Map<String, Object> itemInfo(String itemCode){
		return admindao.itemInfo(itemCode);
	}
	// 상품추가
	public int itemInsert(Map<String, Object> map) {
		return admindao.itemInsert(map);
	}
	// 상품수정
	public int itemUpdate(Map<String, Object> map) {
		return admindao.itemUpdate(map);
	}
}
