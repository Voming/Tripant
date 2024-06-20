
$(loadedHandler);
function loadedHandler() {
	//좋아요
	$(".btn.like").on("click", ClickLikeHandler);
	//검색
	$('.btn-search').on("click",searchHandler);
	//옵션 선택 
	$("select[name=option]").on("change",pickHandler);
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

//option 선택값  
function pickHandler(){
	//선택한 option text값 가져오기
	//var pick=$("select[name=option] option:selected").text();
	//value로 가져오기
	//var pick=$(this).val(); 
	//var write=$('[name=search]').val().tirm();
	//console.log(write);
	
	/*$.ajax({
		url:"/admin/keyword" ,
		method:"post" ,
		data:{pick:pick , write:write} ,
		success:function(result){
			memListHandler(result)
			console.log(result);
		}, 
		 error : function(request, status, error) {
				alert("code: " + request.status + "\n"
						+ "message: " + request.responseText + "\n"
						+ "error: " + error);
			}
	});*/
}



// 검색
function searchHandler(){
	var pick=$(this).val(); 
	var write = $("[name=search]").val().trim();
	$.ajax({
		url:"/admin/keyword",
		 method:"post",
		 data: {pick:pick, write:write},
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