package mclass.store.tripant.admin.domain;




import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AdminPageEntity {

	// 페이징 처리용
		private int startPage;
		private int lastPage;
		private boolean prev,next; // 이전, 다음 페이지 존재유무
		private int total;
		private Criteria cri; //현재페이지 , 페이지 당 게시물 표시수 / 정보 현제 패이지 변수값을 얻기 위해 선언
	
		//생성자
//public AdminPageEntity(Criteria cri, int total) {
//	this.cri=cri;
//	this.total=total;
//	
//	//마지막 페이지
//	this.lastPage=(int)(Math.ceil(cri.getPageNum()/5.0))*5;
//	//시작 페이지
//	this.startPage=this.lastPage-4;
//	//전체 마지막 페이지
//	int realEnd=(int)(Math.ceil((total*1.0)/cri.getAmount()));
//	
//	if(realEnd<this.lastPage){
//		this.lastPage=realEnd;
//	}
//	
//	this.prev=this.startPage>1; //시작 페이지 갑이 1보다 크면 true
//	this.next=this.lastPage<realEnd; //마지막 페이지 갑이 1보다 크면 true
//}
}
