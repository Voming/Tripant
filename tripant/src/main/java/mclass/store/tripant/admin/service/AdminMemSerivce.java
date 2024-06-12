package mclass.store.tripant.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mclass.store.tripant.admin.domain.AdminBoardEntity;
import mclass.store.tripant.admin.domain.AdminMemEntity;
import mclass.store.tripant.admin.model.repository.AdminDao;

@Service
public class AdminMemSerivce {
	
	@Autowired
	private AdminDao admindao;
	
	public List<AdminMemEntity> selectMemList(){
		return admindao.selectMemList();
	}
	
	public Integer adminMemRole(Map<String, Object> map) {
		
		return admindao.adminMemRole(map);
	}
	
	public List<AdminBoardEntity> boardList(){
		return admindao.boardList();
	}
	
	
	

}
