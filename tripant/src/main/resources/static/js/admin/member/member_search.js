$(loaededHandler);
function loaededHandler(){
	$(showAllMember);
	$('.btn-search').on("click",searchHandler);
}

var currentPage = 1;
var totalPageCount = null;
var startPageNum = null;
var endPageNum = null;

/*처음 보이는 화면*/
function showAllMember(){
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
}

/* 페이지 이동 함수 */
function goPageHandler(event) {
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


function searchHandler(){
	
	var targetPage = $(this).data('targetpage');
	console.log('타겟페이지==============' + targetPage);
	
	var searchMem = $("[name=search]").val().trim();
	$.ajax({
		url:"/admin/member/search",
		 method:"post",
		 data: {searchMem:searchMem, currentPage: targetPage},
		 success : function(searchList) {
			 $('#list').html(memListHandler(searchList));
				},
	 error : function(request, status, error) {
				alert("code: " + request.status + "\n"
						+ "message: " + request.responseText + "\n"
						+ "error: " + error);
			}
		
	});
}

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
