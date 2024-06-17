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
	
	public Integer adminMemRole(Map<String, Object> map);
	
	//전체게시글
	public List<AdminBoardEntity> boardList();
	
	//총 페이지 수가 결정됨
	//public int selectTotalPageCount(); 
	//페이지 당 나오는 회원정보 수 뽑기
	//public List<AdminMemEntity> selectPage();
	
	//회원검색
	public List<AdminMemEntity> search(String memNick);
	
	//신고게시글
	public List<AdminBoardEntity> complainList();
}
