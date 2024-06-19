
$(loadedHandler);
function loadedHandler() {
	$(".btn.like").on("click", ClickLikeHandler);
	$('.btn-search').on("click",searchHandler);
}

//좋아요수 정렬
function ClickLikeHandler(){
	$.ajax({
		url:"/admin/like",
		 method:"post",
		 success : function(result) {
			 LikeHandler(result)
				},
	 error : function(request, status, error) {
				alert("code: " + request.status + "\n"
						+ "message: " + request.responseText + "\n"
						+ "error: " + error);
			}
	});
} 
function LikeHandler(like){
	var htmlVal = '';
	for (var idx in like){
		var memBoard = like[idx];
		htmlVal+=`
			<ul class="col list">
			<li th:text="${memBoard.diaryId}"></li>
			<li th:text="${memBoard.diaryTitle}"></li>
			<li th:text="${memBoard.memNick}"></li>
			<li th:text="${memBoard.diaryDate}"></li>
			<li th:text="${memBoard.diaryViews}"></li>
			<li th:text="${memBoard.likes}"></li>
			<li></li>
		</ul>
			`;
	}
}

// 검색
function searchHandler(){
	var searchNick = $("[name=search]").val().trim();
	$.ajax({
		url:"",
		 method:"post",
		 data: {searchNick:searchNick},
		 success : function(result) {
			 memListHandler(result)
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
		var memBoard = searchList[idx];
		htmlVal+=`
			<ul class="col list">
				<li th:text="${memBoard.diaryId}"></li>
				<li th:text="${memBoard.diaryTitle}"></li>
				<li th:text="${memBoard.memNick}"></li>
				<li th:text="${memBoard.diaryDate}"></li>
				<li th:text="${memBoard.diaryViews}"></li>
				<li th:text="${memBoard.likes}"></li>
				<li></li>
			</ul>
			`;
	}
} 