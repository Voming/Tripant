
$(loadedHandler);
function loadedHandler() {
	//좋아요
	//$(".btn.like").on("click", ClickLikeHandler);
	//조회수
	//$(".btn.view").on("click", ClickViewHandler);
	//조건검색
	//$('.btn-search').on("click",searchHandler);
	
}

var currentPage = 1;
var totalPageCount = null;
var startPageNum = null;
var endPageNum = null;

/* 페이징 이동 함수 */
function goPageHandler() {
			var currentpage = $(this).data("targetpage");
			$.ajax({
				beforeSend : csrfHandler,
				error : ajaxErrorHandler,
				url:contextPath+"admin/like"
				, method : "get"
				, data : {
						pick:pick, search:search ,
						currentpage : currentpage}
				, dataType : "json"
				, success : function(result){
					if(result.search){
						$("[name=search]").val(result.search);
					}
					LikeHandler(like);
				}
			});
	}


//좋아요수 정렬
function ClickLikeHandler(thisElement){
	var pick=$("select[name=option] option:selected").val(); //선택한 option val값 
	var search = $("[name=search]").val();  //input 값
	var targetPage = $(thisElement).data('targetpage');
	$.ajax({
		beforeSend : csrfHandler,
		error : ajaxErrorHandler,
		url: contextPath+"admin/like",
		data:{pick:pick, search:search ,page: targetPage},
		 method:"post",
		 success : function(like) {
			 $('.wrap-list').replaceWith(like);
			}
	});
} 
function LikeHandler(like){
	var htmlVal = '';
	for (var idx in like){
		var memBoard = like[idx];
		htmlVal+=`
			<ul class="col list">
				<li>${memBoard.diaryId}</li>
				<li>${memBoard.diaryTitle}</li>
				<li>${memBoard.memNick}</li>
				<li>${memBoard.diaryDate}</li>
				<li>${memBoard.diaryViews}</li>
				<li>${memBoard.likes}</li>
				<li></li>
			</ul>
			`;
	}
	return htmlVal;
}

//조회수 정렬
function ClickViewHandler(thisElement){
	var pick=$("select[name=option] option:selected").val(); //선택한 option val값 
	var search = $("[name=search]").val();  //input 값
	var targetPage = $(thisElement).data('targetpage');
	$.ajax({
		beforeSend : csrfHandler,
		data:{pick:pick, search:search ,page: targetPage},
		error : ajaxErrorHandler,
		url:contextPath+"admin/view",
		 method:"post",
		 success : function(view) {
			 $('.wrap-list').replaceWith(view);
			}
	});
} 
function ViewHandler(view){
	var htmlVal = '';
	for (var idx in view){
		var memBoard = view[idx];
		htmlVal+=`
			<ul class="col list">
				<li>${memBoard.diaryId}</li>
				<li>${memBoard.diaryTitle}</li>
				<li>${memBoard.memNick}</li>
				<li>${memBoard.diaryDate}</li>
				<li>${memBoard.diaryViews}</li>
				<li>${memBoard.likes}</li>
				<li></li>
			</ul>
			`;
	}
	return htmlVal;
}

/* 페이징 이동 함수 */
function goPageHandler() {
			var currentpage = $(this).data("targetpage");
			$.ajax({
				beforeSend : csrfHandler,
				error : ajaxErrorHandler,
				url:contextPath+"admin/keyword"
				, method : "get"
				, data : {
						pick:pick, search:search ,
						currentpage : currentpage}
				, dataType : "json"
				, success : function(result){
					if(result.search){
						$("[name=search]").val(result.search);
					}
					memListHandler(result);
				}
			});
	}

// 검색
function searchBtnHandler(thisElement){
	var pick=$("select[name=option] option:selected").val(); //선택한 option val값 
	var search = $("[name=search]").val();  //input 값
	var targetPage = $(thisElement).data('targetpage');
	console.log(pick);
	console.log(search);
	console.log(targetPage);
	
	$.ajax({
		beforeSend : csrfHandler,
		error : ajaxErrorHandler,
		url:contextPath+"admin/keyword",
		method:"post",
		data: {pick:pick, search:search , page: targetPage},
		success : function(searchList) {
				$('.wrap-list').replaceWith(searchList);
			}
	});
}

function memListHandler(searchList){
	var htmlVal = '';
	for (var idx in searchList){
		var memBoard = searchList[idx];
		htmlVal+=`
			<ul class="col list">
				<li>${memBoard.diaryId}</li>
				<li>${memBoard.diaryTitle}</li>
				<li>${memBoard.memNick}</li>
				<li>${memBoard.diaryDate}</li>
				<li>${memBoard.diaryViews}</li>
				<li>${memBoard.likes}</li>
				<li></li>
			</ul>
			`;
	}
	return htmlVal;
}