package mclass.store.tripant.admin.domain;





import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminPageEntity {


			//페이징처리
			private int page; //현재 페이지 
			private int pageSize; //한페이지 당 글 갯수
			private int pageBlockSize; //하단에 나타낼 페이지 수
			private int totalCount; //전체글 갯수
	
			private int startNo;
			private int endNo;
			private int startPage;
			private int endPage;
			private int totalPage;
			
			private String searchSubject; //검색어
			
			//검색필터
			private String type;  //조건
			private String keyword; //검색어
			
	public AdminPageEntity(int page, int pageSize, int totalCount) {
		this.pageSize=pageSize;
		this.totalCount=totalCount;
		
		//페이지 개수
		totalPage=(totalCount-1)/pageSize+1;
		
		//현재 페이지가 잘못된 값이 전달될 때는 1보다 작은 값이거나 총페이지 수 보다 큰 값일 때
		this.page=(page<1)? 1:page;
		this.page=(page>totalPage)? totalPage:page;
		
		startNo=(this.page-1)*pageSize+1;
		endNo=startNo+(pageSize-1); //totalCount 이하의 값이여야 함.
		
		this.endNo=this.endNo>totalCount? totalCount:this.endNo;
		startPage=(this.page-1)/10*10+1; //현재페이지가 11-20일 때 startpage는 11
		
		endPage=startPage+9; //totalPage 이하이 값이여야함.
		this.endPage=this.endPage>totalPage? totalPage:this.endPage;
		
	}
	public int getOffset() {
		return (page-1)*pageSize;
	}
	
}
