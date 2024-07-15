function enterkey() {
	if (window.event.keyCode == 13) {
    	// 엔터키가 눌렸을 때
    	searchBtnHandler();
    }
}
/* 체크표시가 되면 버튼 활성화 */
function checkSetHandler(){
	$('input[name=check]').each(function(){
		if($('input[name=check]').is(":checked")==true){
			$('.btn-reset').prop('disabled',false).css('background-color','black').css('color','white').css('border','solid black 1px');
		}else{
			$('.btn-reset').prop('disabled',true).css('background-color','white').css('color','black').css('border','solid black 1px');
		}
	})
}

/* 초기화버튼 누르면 체크박스 체크된 게시글 신고수 0으로 */
function cickSetHandler(){
	 //배열선언
	var diaryId=new Array();
	$('input[name=check]:checked').each(function(){ //체크된 체크박스의 data값 가져오기
		diaryId.push($(this).data('diary-id'));
	})
	$.ajax({
		beforeSend : csrfHandler,
		error : ajaxErrorHandler,
		url: contextPath+"admin/reset",
		 method:"post",
		 data: {diaryId:diaryId},
		 success : function(result) {
			 	location.reload();
				}
	});
} 
let currentPage = 1;
let sort = null;
let searchMem = null;
let count = 1;

/* 페이징 이동 함수 */
function goPageHandler(thisElement) {
			var currentPage = $(thisElement).data("targetpage");
			var sortCount =  count % 2;
			$.ajax({
				beforeSend : csrfHandler,
				error : ajaxErrorHandler,
				url:contextPath+"admin/complain/search"
				, method : "post"
				, data : {
						searchMem : searchMem,
						currentPage : currentPage,
						sort: sort,
						sortCount:sortCount
						}
				}).done(function(a){
					if(a){
						$(".wrap-list").replaceWith(a);
						count += 1;
				}
			});
	}

//검색
function searchBtnHandler(){
	var searchMem = $("[name=search]").val().trim();
	var currentPage = $(this).data("targetpage");
	var sortCount =  count % 2;
	$.ajax({
		beforeSend : csrfHandler,
		error : ajaxErrorHandler,
		url: contextPath+"admin/complain/search",
		 method:"post",
		 data: {searchMem:searchMem,currentPage: currentPage, 
		 sort:sort,sortCount:sortCount},
		 success : function(complainList) {
			 $('.wrap-list').replaceWith(complainList);
			 count += 1;
			}
	});
}

function memListHandler(complainList){
	var htmlVal = '';
	for (var idx in complainList){
		var complainBoard = complainList[idx];
		htmlVal+=`
			<ul class="col list">
				<li><input type="checkbox" class="check" name="check" th:data-diary-id="${complainBoard.diaryId}" th:data-reports="${complainBoard.reports}" th:data-mem-nick="${complainBoard.memNick}"></li>
				<li>${complainBoard.diaryId}</li>
				<li>${complainBoard.diaryTitle}</li>
				<li>${complainBoard.memNick}</li>
				<li>${complainBoard.diaryDate}</li>
				<li>${complainBoard.reports}</li>
				<li></li>
			</ul>
			`;
	}
	return htmlVal;
} 



//신고수 정렬
function clickReportHandler(){
	sort='reports'
	var searchMem = $("[name=search]").val().trim();
	var currentPage = $(this).data("targetpage");
	var sortCount =  count % 2;
	$.ajax({
		beforeSend : csrfHandler,
		error : ajaxErrorHandler,
		url: contextPath+"admin/complain/search",
		data:{
			currentPage: currentPage, 
			searchMem:searchMem, 
			sort: sort,
			sortCount:sortCount
		},
		 	method:"post",
		 }).done(function(data){
		if(data){
			$('.wrap-list').replaceWith(data);
			count += 1;
		}
	});
} 

function ReportHandler(report){
	var htmlVal = '';
	for (var idx in report){
		var complainBoard = report[idx];
		htmlVal+=`
			<ul class="col list">
			<li><input type="checkbox" class="check" name="check"></li>
				<li>${complainBoard.diaryId}</li>
				<li>${complainBoard.diaryTitle}</li>
				<li>${complainBoard.memNick}</li>
				<li>${complainBoard.diaryDate}</li>
				<li>${complainBoard.reports}</li>
				<li></li>
			</ul>
			`;
	}
	return htmlVal;
}