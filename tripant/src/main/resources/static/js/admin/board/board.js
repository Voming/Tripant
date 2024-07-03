
$(loadedHandler);
function loadedHandler() {
	//좋아요
	$(".btn.like").on("click", ClickLikeHandler);
	//조회수
	$(".btn.view").on("click", ClickViewHandler);
	//조건검색
	$('.btn-search').on("click",searchHandler);
	
}

//좋아요수 정렬
function ClickLikeHandler(){
	$.ajax({
		url:"/admin/like",
		 method:"post",
		 success : function(like) {
			 $('#list').html(LikeHandler(like));
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
function ClickViewHandler(){
	$.ajax({
		url:"/admin/view",
		 method:"post",
		 success : function(view) {
			 $('#list').html(ViewHandler(view));
				},
	 error : function(request, status, error) {
				alert("code: " + request.status + "\n"
						+ "message: " + request.responseText + "\n"
						+ "error: " + error);
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


// 검색
function searchHandler(){
	var pick=$("select[name=option] option:selected").val(); //선택한 option val값 
	var write = $("[name=search]").val().trim();  //input 값
	console.log(write);
	console.log(pick);
	$.ajax({
		url:"/admin/keyword",
		method:"post",
		data: {pick:pick, write:write},
		success : function(searchList) {
				console.log(searchList);
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