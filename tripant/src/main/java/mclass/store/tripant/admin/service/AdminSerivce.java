package mclass.store.tripant.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mclass.store.tripant.admin.domain.AdminBoardEntity;
import mclass.store.tripant.admin.domain.AdminMemEntity;
import mclass.store.tripant.admin.domain.AdminStoreEntity;
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
	
	//활성화 여부
	public Integer adminMemActive(Map<String, Object> map) {
			return admindao.adminMemActive(map);
	}
	
	//회원검색
	public List<AdminMemEntity> search(String memNick){
		return admindao.search(memNick);
	}
	
	//게시글리스트
	public List<AdminBoardEntity> boardList(){
		return admindao.boardList();
	}
	
	//게시글 검색(select)
	public List<AdminBoardEntity> keywordsearch(Map<String, Object> map){
		return admindao.keywordsearch(map);
	}

	//좋아요 정렬
	public List<AdminBoardEntity> boardLikes() {
		
		return admindao.boardLike();
	}
	//조회수 정렬
	public List<AdminBoardEntity> boardView() {
			
		return admindao.boardView();
	}
		
	//신고게시글
	public List<AdminBoardEntity> complainList(){
		return admindao.complainList();
	}
	
	//신고게시글 검색
	public List<AdminBoardEntity> complainsearch(String memNick){
		return admindao.complainsearch(memNick);
	}
	
	//신고수 초기화
	public Integer complainReset(Integer diaryId) {
		return admindao.complainReset(diaryId);
	}
	
	//신고수 정렬
	public List<AdminBoardEntity> boardReport(){
		return admindao.boardReport();
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
	
	//결제취소 회원 검색
	public List<AdminStoreEntity> cancelSearch(String memNick){
		return admindao.cancelSearch(memNick);
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
	//상품 삭제
	public int itemDelete(String itemCode) {
		return admindao.itemDelete(itemCode);
	}
	//상품검색
	public List<AdminStoreEntity> itemsearch(String itemCode){
		return admindao.itemsearch(itemCode);
	}
	
	//페이징처리
	public List<Map<String,Object>> list(Map<String , Object> map){
		int curPage=0;
		int pageScale=0;
		
		if(map.isEmpty()) {
			curPage=1;
			pageScale=10;
		}else {
			curPage=Integer.parseInt(map.get("curPage").toString());
			pageScale=Integer.parseInt(map.get("pageScale").toString());
		}
		int pageBegin=(curPage-1)*pageScale+1;
		int pageEnd=pageBegin+pageScale-1;
		
		map.put("pageBegin", pageBegin);
		map.put("pageEnd", pageEnd);
	
		return admindao.page(map);
	}
}
