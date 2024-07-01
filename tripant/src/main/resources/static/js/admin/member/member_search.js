$(loaededHandler);
function loaededHandler(){
	//$(showAllMember);
	//$('.btn-search').on("click",searchBtnHandler);
}

var currentPage = 1;
var totalPageCount = null;
var startPageNum = null;
var endPageNum = null;

/*처음 보이는 화면*/
/*function showAllMember(){
	$.ajax({
		url: "/admin/member",
		method: "get",
		data: {
			currentPage: currentPage
		},
		 success : function(searchList) {
			 $('#list').html(memListHandler(searchList));
				},
	 error : function(request, status, error) {
				alert("code: " + request.status + "\n"
						+ "message: " + request.responseText + "\n"
						+ "error: " + error);
			}
	});
}*/


/* 페이지 이동 함수 */
function goPageHandler() {
			var currentpage = $(this).data("targetpage");
			$.ajax({
				url:"/admin/member/search"
				, method : "get"
				, data : {
						seachMem : seachMem,
						currentpage : currentpage}
				, dataType : "json"
				, error : ajaxErrorHandler
				, success : function(result){
					if(result.seachMem){
						$("[name=search]").val(result.seachMem);
					}
					memListHandler(result);
					displayPageNum(result);
				}
			});
	}

/*검색+페이징1*/
function searchBtnHandler(thisElement){
	var targetPage = $(thisElement).data('targetpage');
	console.log('타겟페이지==============' + targetPage);
	var searchMem = $("[name=search]").val().trim();
	$.ajax({
		url:"/admin/member/search",
		 method:"post",
		 data: {
			searchMem:searchMem
		 , page: targetPage
		 },
		 success : function(searchList) {
//			 $('#list').html(memListHandler(searchList));
			$('.wrap-list').replaceWith(searchList);
		},
	 error : function(request, status, error) {
				alert("code: " + request.status + "\n"
						+ "message: " + request.responseText + "\n"
						+ "error: " + error);
		}
	});
}

/*function searchHandler(){
	var targetPage = $(this).data('targetpage');
	console.log('타겟페이지==============' + targetPage);
	
	var searchMem = $("[name=search]").val().trim();
	$.ajax({
		
	})
}*/

function memListHandler(searchList){
	var htmlVal = '';
	for (var idx in searchList){
		var memList = searchList[idx];
		htmlVal+=`
			<ul class="col list">
				<li>${memList.memNick}</li>
				<li>${memList.memEmail}</li>
				<li>${memList.memJoinDate}</li>
				<li>${memList.memQuitDate}</li>
				<li>${memList.memRole}</li>
				<li><button type="button" class="btn">편집</button></li>
			</ul>
			`;
	}
	return htmlVal;
} 

/*function displayPageNum(searchList){
	var htmlVal = '';
	for (var idx in pageList){
		var memList = pageList[idx];
		htmlVal+=`
			<div th:if="${reviewDetailDto}">
	<div th:if="${reviewDetailDto.totalReviewCount > 0}">
		<div style="text-align: center;">
			<div class="paging_wrap">
				<div th:if="${reviewDetailDto.startPageNum > 1}" class="goprepage">
		            <button type="button" class="btn-gopage" th:data-targetpage="${reviewDetailDto.startPageNum - 1}">&lt;</button>
		        </div>
		        
		        <div th:each="page :  ${#numbers.sequence(reviewDetailDto.startPageNum, reviewDetailDto.endPageNum)}">
			        <div th:if="${reviewDetailDto.currentPage == page}" class="current_page">
			            <span th:text="${page}"></span>
			        </div>
			        <div th:if="${reviewDetailDto.currentPage != page}" class="gopage">
			            <button type="button" class="btn-gopage" th:data-targetpage="${page}" th:text="${page}"></button>
			        </div>
		        </div>
		        
		        <div th:if="${reviewDetailDto.endPageNum < reviewDetailDto.totalReviewCount}">
		            <button type="button" th:data-targetpage="${reviewDetailDto.endPageNum + 1}" class="btn-gopage">&gt;</button>
		        </div>			
			</div>
		</div>
	</div>
			</div>
			`;
	}
	return htmlVal;
}*/