
let currentPage = 1;
let pick = null;
let search = null;
let sort = null;
let count = 1;

function enterkey() {
	if (window.event.keyCode == 13) {
    	// 엔터키가 눌렸을 때
    	searchBtnHandler();
    }
}

/* 페이징 이동 함수 */
function goPageHandler(thisElement) {
			currentPage = $(thisElement).data("targetpage");
			var sortCount =  count % 2;
			$.ajax({
				beforeSend : csrfHandler,
				error : ajaxErrorHandler,
				url:contextPath+"admin/keyword"
				, method : "post"
				, data : {
					page: currentPage, 
					pick: pick, 
					search: search, 
					sort: sort,
					sortCount:sortCount
				}
			}).done(function(a){
				if(a){
					$(".wrap-list").replaceWith(a);
				}
			});
	}

// 검색
function searchBtnHandler(){
	pick=$("select[name=option] option:selected").val(); //선택한 option val값 
	search = $("[name=search]").val();  //input 값
	var currentPage = $(this).data('targetpage');
	var sortCount =  count % 2;
	$.ajax({
		beforeSend : csrfHandler,
		error : ajaxErrorHandler,
		url:contextPath+"admin/keyword",
		method:"post",
		data: {
			page: currentPage, 
			pick: pick, 
			search: search, 
			sort: sort,
			sortCount:sortCount
		},
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

//좋아요수 정렬
function ClickLikeHandler(){
	sort = 'likes';
	var currentPage = $(this).data('targetpage');
	var sortCount =  count % 2;
	$.ajax({
		beforeSend : csrfHandler,
		error : ajaxErrorHandler,
		url: contextPath+"admin/keyword",
		data:{
			page: currentPage, 
			pick: pick, 
			search: search, 
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
	sort = 'view';
	var currentPage = $().data('targetpage');
	var sortCount =  count % 2;
	$.ajax({
		beforeSend : csrfHandler,
		data:{
			page: currentPage, 
			pick: pick, 
			search: search, 
			sort: sort,
			sortCount:sortCount
		},
		error : ajaxErrorHandler,
		url:contextPath+"admin/keyword",
		 method:"post",
	}).done(function(data){
		if(data){
			$('.wrap-list').replaceWith(data);
			count += 1;
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