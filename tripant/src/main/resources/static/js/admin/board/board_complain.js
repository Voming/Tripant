
$(loadedHandler);
function loadedHandler(){
	 //체크박스
	$('input[name=check]').on('click',CheckSetHandler);
	//초기화버튼
	$('.btn-reset').on('click',ClickSetHandler);  
	//검색
	$('.btn-search').on("click",searchHandler);
	
}
/* 체크표시가 되면 버튼 활성화 */
function CheckSetHandler(){
	$('input[name=check]').each(function(){
		if($('input[name=check]').is(":checked")==true){
			$('.btn-reset').prop('disabled',false).css('background-color','black').css('color','white').css('border','solid black 1px');
		}else{
			$('.btn-reset').prop('disabled',true).css('background-color','white').css('color','black').css('border','solid black 1px');
		}
	})
}

/* 초기화버튼 누르면 체크박스 체크된 게시글 신고수 0으로 */
function ClickSetHandler(){
	 //배열선언
	var diaryId=new Array();
	$('input[name=check]:checked').each(function(){ //체크된 체크박스의 data값 가져오기
		diaryId.push($(this).data('diary-id'));
	})
	$.ajax({
		url:"/admin/reset",
		 method:"post",
		 data: {diaryId:diaryId},
		 success : function(result) {
			 	location.reload();
				},
	 error : function(request, status, error) {
				alert("code: " + request.status + "\n"
						+ "message: " + request.responseText + "\n"
						+ "error: " + error);
			}
	});
} 

//검색
function searchHandler(){
	var searchBoard = $("[name=search]").val().trim();

	$.ajax({
		url:"/admin/complain/search",
		 method:"post",
		 data: {searchBoard:searchBoard},
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

function memListHandler(complainList){
	var htmlVal = '';
	for (var idx in complainList){
		var complainBoard = complainList[idx];
		htmlVal+=`
			<ul class="col list">
				<li><input type="checkbox" class="check" name="check"  th:data-diary-id="${complainBoard.diaryId}" th:data-reports="${complainBoard.reports}"></li>
				<li th:text="${complainBoard.diaryId}"></li>
				<li th:text="${complainBoard.diaryTitle}"></li>
				<li th:text="${complainBoard.memNick}"></li>
				<li th:text="${complainBoard.diaryDate}"></li>
				<li th:text="${complainBoard.reports}"></li>
				<li></li>
			</ul>
			`;
	}
} 