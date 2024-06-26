
$(loadedHandler);
function loadedHandler() {
	//좋아요
	$(".btn.like").on("click", ClickLikeHandler);
	//조회수
	$(".btn.view").on("click", ClickViewHandler);
	//조건검색
	$('.btn-search').on("click",searchHandler);
	//옵션 선택 
	//$("select[name=option]").on("change",pickHandler);
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
//option 선택값  
function pickHandler(){
	/*//선택한 option text값 가져오기
	var pick=$("select[name=option] option:selected").text();
	//value로 가져오기
	var pick=$(this).val(); 
	var write=$('[name=search]').val().tirm();
	console.log(write);*/
	
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
	var pick=$("select[name=option] option:selected").val(); 
	var write = $("[name=search]").val().trim();
	console.log(write);
	console.log(pick);
	$.ajax({
		url:"/admin/keyword",
		 method:"post",
		 data: {pick:pick, write:write},
		 success : function(searchList) {
			if(pick='nick'){
				console.log(searchList);
				 $('#list').html(memListHandler(searchList));
				}
			 
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